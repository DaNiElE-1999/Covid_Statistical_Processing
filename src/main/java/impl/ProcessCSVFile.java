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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
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

               List<String> lines =
                       Files.readAllLines(Paths.get(csvPath)) //<- Lexon pathin
                       .stream()
                       .skip(1)
                       //.forEach(System.out::println);
                       .collect(Collectors.toList()); // from csv file to list

        //populate list of LocationData and CovidStatisticsData
        lines.stream()
                .map(line -> line.split(","))
                .forEach(line -> {
                    LocationData locationData = new LocationData(
                            Integer.parseInt(createID()), // id
                            line[3], //date
                            line[0], //iso_code
                            line[1], //continent
                            line[2], //country
                            Double.parseDouble(ifNull(line[48])), //stringency_index
                            Double.parseDouble(ifNull(line[50])), //population
                            Double.parseDouble(ifNull(line[47]))); //median_age
                            //locationDataList.add(locationData);
                           // System.out.println(locationData);
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
                   // covidStatisticsDataList.add(covidStatisticsData);
                        }
                );

/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the statistics to be computed (min or max): ");
        String statistics = scanner.nextLine().toLowerCase();
        System.out.println("Enter the number of records you want to see: (0->100) ");
        int numberOfRecords = scanner.nextInt();
        System.out.println("Enter the type of records you want to see (display-> Date, Country, or Continent):");
        String typeOfRecords = scanner.next().toUpperCase();
        System.out.println("Enter the field on which the statistics will be computed: NC, NCS, ND, NDS, NT, or NDPC");
        String by = scanner.next().toUpperCase();

        Menu menu = new Menu(statistics, numberOfRecords, by, typeOfRecords, csvPath);
        System.out.println(menu);

        if (statistics.equals("min")) {
            Collection<Integer> id;
            if (by.equals("NC")) {
                // return id of the min value of new cases
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases))
                        .filter(c -> c.getNew_cases() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NCS")) {
                // return id of the min value of new cases smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases_smoothed))
                        .filter(c -> c.getNew_cases_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("ND")) {
                // return id of the min value of new deaths
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths))
                        .filter(c -> c.getNew_deaths() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NDS")) {
                // return id of the min value of new deaths smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_smoothed))
                        .filter(c -> c.getNew_deaths_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NT")) {
                // return id of the min value of new tests
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_tests))
                        .filter(c -> c.getNew_tests() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NDPC")) {
                // return id of the min value of new deaths per case
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_per_case))
                        .filter(c -> c.getNew_deaths_per_case() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else {
                id = null;
            }

            if (typeOfRecords.equals("DATE")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getDate)
                        .forEach(System.out::println);
            }
            else if (typeOfRecords.equals("COUNTRY")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getLocation)
                        //.map(LocationData::getId)
                        .forEach(System.out::println);
            }
            else if (typeOfRecords.equals("CONTINENT")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getContinent)
                        .forEach(System.out::println);
            }
            else {
                System.out.println("Invalid input");
            }

        }

        else if (statistics.equals("max")) {
            // return id of the max value of new cases
            Collection<Integer> id;
            if (by.equals("NC")) {
                // return id of the max value of new cases
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases).reversed())
                        .filter(c -> c.getNew_cases() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NCS")) {
                // return id of the max value of new cases smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases_smoothed).reversed())
                        .filter(c -> c.getNew_cases_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("ND")) {
                // return id of the max value of new deaths
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths).reversed())
                        .filter(c -> c.getNew_deaths() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NDS")) {
                // return id of the max value of new deaths smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_smoothed).reversed())
                        .filter(c -> c.getNew_deaths_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NT")) {
                // return id of the max value of new tests
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_tests).reversed())
                        .filter(c -> c.getNew_tests() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (by.equals("NDPC")) {
                // return id of the max value of new deaths per case
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_per_case).reversed())
                        .filter(c -> c.getNew_deaths_per_case() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else {
                id = null;
            }

            if (typeOfRecords.equals("DATE")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getDate)
                        .forEach(System.out::println);
            }
            else if (typeOfRecords.equals("COUNTRY")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getLocation)
                        .forEach(System.out::println);
            }
            else if (typeOfRecords.equals("CONTINENT")) {
                locationDataList.stream()
                        .filter(locationData -> id.contains(locationData.getId()))
                        .map(LocationData::getContinent)
                        .forEach(System.out::println);
            }
            else {
                System.out.println("Invalid input");
            }
        }
        else {
            System.out.println("Invalid input");
        }
*/

    }//go

    @Contract(pure = true)
    private static @NotNull String ifNull(@NotNull String s) {
        if(s.equals("")) {
            return "-1";
        }
        return s;
    }

    private static Double ifNegative(@NotNull Double d) {
        if(d < 0) {
            return 999999999999999999.0;
        }
        return d;
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
