package impl;

import model.CovidStatisticsData;
import model.LocationData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProcessCSVFile {

    public static void go() throws Exception {

        CovidStatisticsData covidStatisticsData = new CovidStatisticsData();
        Collection<LocationData> locationDataList = new ArrayList<>();
        LocationData locationData = new LocationData();
        ArrayList<CovidStatisticsData> covidStatisticsDataList = new ArrayList<>();
        //
        boolean status; //max or min
        int limit; // 1 - 100
        String by; // NC, NCS, ND, NDS, NT, NDPC
        String display; // DATE, COUNTRY, CONTINENT
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
                            locationData.setId(Integer.parseInt(createID()));
                            locationData.setIso_code(line[0]);
                            locationData.setLocation(line[2]);
                            locationData.setContinent(line[1]);
                            locationData.setPopulation(line[48]);
                            locationData.setMedian_age(line[50]);
                            locationData.setStringency_index(line[47]);
                            //locationDataList.add(locationData);
                    System.out.println(locationData);
                            covidStatisticsData.setId(Integer.parseInt(createID1()));
                            covidStatisticsData.setDate(line[3]);
                            covidStatisticsData.setTotal_cases(line[4]);
                            covidStatisticsData.setNew_cases(line[5]);
                            covidStatisticsData.setNew_cases_smoothed(line[6]);
                            covidStatisticsData.setTotal_deaths(line[7]);
                            covidStatisticsData.setNew_deaths(line[8]);
                            covidStatisticsData.setNew_deaths_smoothed(line[9]);
                            covidStatisticsData.setReproduction_rate(line[16]);
                            covidStatisticsData.setNew_tests(line[25]);
                            covidStatisticsData.setTotal_tests(line[26]);
                            //covidStatisticsDataList.add(covidStatisticsData);
                    System.out.println(covidStatisticsData);
                        }
                );


        locationDataList.stream().forEach(System.out::println);
        //covidStatisticsDataList.stream().forEach(System.out::println);

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
}
