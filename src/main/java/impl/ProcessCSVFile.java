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

        Collection<LocationData> locationDataList = new ArrayList<>();
        Collection<CovidStatisticsData> covidStatisticsDataList = new ArrayList<>();
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



        locationDataList.stream().forEach(System.out::println);
        covidStatisticsDataList.stream().forEach(System.out::println);

    }

    private static String ifNull(String s) {
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
}
