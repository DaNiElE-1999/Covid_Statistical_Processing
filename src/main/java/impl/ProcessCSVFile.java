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
                       .collect(Collectors.toList());

        lines.stream()
                .map(line -> line.split(","))
                .forEach(line -> {
                    LocationData locationData = new LocationData(
                            Integer.parseInt(createID()),
                            line[0],
                            line[2],
                            line[1],
                            Double.parseDouble(ifNull(line[48])),
                            Double.parseDouble(ifNull(line[50])),
                            Double.parseDouble(ifNull(line[47])));
                            locationDataList.add(locationData);
                    CovidStatisticsData covidStatisticsData = new CovidStatisticsData(Integer.parseInt(createID1()),
                            line[3],
                            Double.parseDouble(ifNull(line[4])),
                            Double.parseDouble(ifNull(line[5])),
                            Double.parseDouble(ifNull(line[6])),
                            Double.parseDouble(ifNull(line[7])),
                            Double.parseDouble(ifNull(line[8])),
                            Double.parseDouble(ifNull(line[9])),
                            Double.parseDouble(ifNull(line[16])),
                            Double.parseDouble(ifNull(line[25])),
                            Double.parseDouble(ifNull(line[26])));
                    covidStatisticsDataList.add(covidStatisticsData);
                        }
                );



        //locationDataList.stream().forEach(System.out::println);
        //covidStatisticsDataList.stream().forEach(System.out::println);

    }

    @Contract(pure = true)
    private static @NotNull String ifNull(@NotNull String s) {
        if(s.equals("")) {
            return "0";
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
