package impl;

import model.CovidStatisticsData;
import model.LocationData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProcessCSVFile {

    public static <T> void go() throws Exception {

        Collection<LocationData> locationDataList = new ArrayList<>();
        Collection<CovidStatisticsData> covidStatisticsDataList = new ArrayList<>();
        //
        String path = getPath();; // path to CSV file
        String sPath = getPathS(); // path to sample CSV file
        final var fileUserDir= System.getProperty("user.dir") + sPath;
        final var csvPath= System.getProperty("user.dir") + path;

               List <String> lines = Files.readAllLines(Paths.get(fileUserDir))
                       .stream()
                       .skip(1)
                       .collect(Collectors.toList()); // from csv file to list

        //populate list of LocationData and CovidStatisticsData
        lines.stream()
                .map(line -> line.split(","))
                .forEach(line -> {
                    LocationData locationData = new LocationData(
                            Integer.parseInt(createID()), // id
                            line[3], //date
                            line[0], //iso_code
                            line[2], //continent
                            line[1], //country
                            Double.parseDouble(ifNull(line[48])), //stringency_index
                            Double.parseDouble(ifNull(line[50])), //population
                            Double.parseDouble(ifNull(line[47]))); //median_age
                            locationDataList.add(locationData);
                    CovidStatisticsData covidStatisticsData = new CovidStatisticsData(
                            Integer.parseInt(createID1()), // id
                            Double.parseDouble(ifNull(line[4])), //total_cases
                            Double.parseDouble(ifNull(line[5])), //new_cases
                            Double.parseDouble(ifNull(line[6])), //new_cases_smoothed
                            Double.parseDouble(ifNull(line[7])), //total_deaths
                            Double.parseDouble(ifNull(line[8])), //new_deaths
                            Double.parseDouble(ifNull(line[9])), //new_deaths_smoothed
                            Double.parseDouble(ifNull(line[16])), //reproduction_rate
                            Double.parseDouble(ifNull(line[25])), //new_tests
                            Double.parseDouble(ifNull(line[26]))); //total_tests
                    covidStatisticsDataList.add(covidStatisticsData);
                        }
                );



       // return id of the max value of new cases
         Collection<Integer> id = covidStatisticsDataList.stream()
                .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases).reversed())
                 .map(CovidStatisticsData::getId)
                 .limit(100)
                 .collect(Collectors.toList());

         //id.stream().forEach(System.out::println);

         locationDataList.stream()
                 .filter(locationData -> id.contains(locationData.getId()))
                 .map(LocationData::getDate)
                 .forEach(System.out::println);

    }

    @Contract(pure = true)
    private static @NotNull String ifNull(@NotNull String s) {
        if(s.equals("")) {
            return "-1";
        }
        return s;
    }

    public static @Nullable String getPathS() {

        try (InputStream input = new FileInputStream("src/main/resources/persistence.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            String sPath;
            // get the property value and print it out
           return prop.getProperty("sPath");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @org.jetbrains.annotations.Nullable
    public static String getPath() {

        try (InputStream input = new FileInputStream("src/main/resources/persistence.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            String path;
            // get the property value and print it out
            return prop.getProperty("path");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static AtomicLong idCounter = new AtomicLong();
    private static AtomicLong idCounter1 = new AtomicLong();

    public static @NotNull String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }
    public static @NotNull String createID1()
    {
        return String.valueOf(idCounter1.getAndIncrement());
    }

    public static <T> @NotNull ArrayList menu() throws InputMismatchException{

        Scanner myObj = new Scanner(System.in);
        ArrayList<T> menu = new ArrayList<>();
        String status; //max or min
        Integer limit; // 1 - 100
        String by; // NC, NCS, ND, NDS, NT, NDPC
        String display; // DATE, COUNTRY, CONTINENT

        System.out.println("stat");// -stat --> max or min
        status = myObj.nextLine();
        System.out.println("limit");// -limit --> 0 < X < 100
        limit = myObj.nextInt();
        System.out.println("by");// -by --> max(by) or min(by)  NC, NCS, ND, NDS, NT, NDPC
        by = myObj.nextLine();
        System.out.println("display");// -display --> DATE, COUNTRY, CONTINENT
        display = myObj.nextLine();

        //limit = l;
        menu.add((T) status);
        menu.add((T) limit);
        menu.add((T) by);
        menu.add((T) display);
        return menu;
    }
}
