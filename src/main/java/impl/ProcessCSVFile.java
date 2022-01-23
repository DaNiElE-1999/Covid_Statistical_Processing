package impl;

import model.CovidStatisticsData;
import model.LocationData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProcessCSVFile {


    private static AtomicLong idCounter = new AtomicLong();
    private static AtomicLong idCounter1 = new AtomicLong();

    public static void go() throws Exception {

        System.out.println("Please enter the command line parameters\n");
        System.out.println("Path must be relative to the Assignment Folder\n");
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String[] paramCheck =
                Arrays.stream(s.split(" -"))
                        .map(String::trim)
                        .toArray(String[]::new);
        if (paramCheck.length != 5) {
            System.out.println("Wrong number of parameters!");
        }

        String[] input =
                Arrays.stream(s.split(" "))
                        .map(String::trim)
                        .toArray(String[]::new);
        if (input.length != 10) {
            System.out.println("Wrong format!");
        }

        String[] parameters =
                Arrays.stream(s.split(" "))
                        //.skip(s.indexOf(1))
                        .filter(x -> x.startsWith("-"))
                        .map(String::trim)
                        .toArray(String[]::new);

        List<String> list = new ArrayList<String>(Arrays.asList(parameters));
        if (parameters.length != 5)  {
            System.out.println("Wrong number of parameters or incorrect input!");
        }

        HashMap<String, String> map = new HashMap<>();
        map.put(list.get(0), input[1]);
        map.put(list.get(1), input[3]);
        map.put(list.get(2), input[5]);
        map.put(list.get(3), input[7]);
        map.put(list.get(4), input[9]);


        Menu menu = new Menu();
        map.forEach((k, v) ->
        {
            if (k.equals("-file")) {
                menu.setPath(v);
            }
            if (k.equals("-stat")) {
                menu.setStatus(v);
            }
            if (k.contains("-limit")) {
                try {

                    menu.setLimit(Integer.parseInt(v) );
                }
                catch (NumberFormatException e) {
                    System.out.println("Limit must be an integer!");
                    try {
                        //test();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if(Integer.parseInt(v) > 100 || Integer.parseInt(v) <0) {
                    System.out.println("Limit must be between 0 and 100!");
                    try {
                        //test();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (k.equals("-by")) {
                menu.setBy(v);
            }
            if (k.equals("-display")) {
                menu.setDisplay(v);
            }
        });


        System.out.println("\n");
        //String dirPath = getPath();
        final var csvPath= System.getProperty("user.dir") + menu.getPath();

        Collection<LocationData> locationDataList = new ArrayList<>();
        Collection<CovidStatisticsData> covidStatisticsDataList = new ArrayList<>();
        //
        List<String> lines = null;
        try {
            lines =
                    Files.readAllLines(Paths.get(csvPath)) //<- gets the path to the CSV file
                            .stream()
                            .skip(1)
                            .collect(Collectors.toList()); // from csv file to list

        }
        catch (IOException e) {
            System.out.println("File not found!");
        }

        //populate list of LocationData and CovidStatisticsData
        try {
            lines.stream()
                    .map(line -> line.split(",", -1))
                    .forEach(line -> {
                                if (line.length!=67) {
                                    System.out.println("Error in line: " + line.length);
                                }
                                LocationData locationData = new LocationData(
                                        Integer.parseInt(createID()), // id
                                        line[3], //date
                                        line[0], //iso_code
                                        line[1], //continent
                                        line[2], //country
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
        }
        catch (NullPointerException e) {
            System.out.println("No values: " + e.getMessage());
        }


        if (menu.getStatus().toLowerCase().equals("min")) {
            Collection<Integer> id;
            if (menu.getBy().toUpperCase().equals("NC")) {
                // return id of the min value of new cases
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases))
                        .filter(c -> c.getNew_cases() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NCS")) {
                // return id of the min value of new cases smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases_smoothed))
                        .filter(c -> c.getNew_cases_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("ND")) {
                // return id of the min value of new deaths
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths))
                        .filter(c -> c.getNew_deaths() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NDS")) {
                // return id of the min value of new deaths smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_smoothed))
                        .filter(c -> c.getNew_deaths_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NT")) {
                // return id of the min value of new tests
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_tests))
                        .filter(c -> c.getNew_tests() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NDPC")) {
                // return id of the min value of new deaths per case
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_per_case))
                        .filter(c -> c.getNew_deaths_per_case() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else {
                System.out.println("Invalid input");
                id = null;
            }

            if (menu.getDisplay().toUpperCase().equals("DATE:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
                        .map(LocationData::getDate)
                        .forEach(System.out::println);
            }
            else if (menu.getDisplay().toUpperCase().equals("COUNTRY:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
                        .map(LocationData::getLocation)
                        //.map(LocationData::getId)
                        .forEach(System.out::println);
            }
            else if (menu.getDisplay().toUpperCase().equals("CONTINENT:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
                        .map(LocationData::getContinent)
                        .forEach(System.out::println);
            }
            else {
                System.out.println("Invalid input");
            }

        }

        else if (menu.getStatus().toLowerCase().equals("max")) {
            // return id of the max value of new cases
            Collection<Integer> id;
            if (menu.getBy().toUpperCase().equals("NC")) {
                // return id of the max value of new cases
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases).reversed())
                        .filter(c -> c.getNew_cases() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NCS")) {
                // return id of the max value of new cases smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_cases_smoothed).reversed())
                        .filter(c -> c.getNew_cases_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("ND")) {
                // return id of the max value of new deaths
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths).reversed())
                        .filter(c -> c.getNew_deaths() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NDS")) {
                // return id of the max value of new deaths smoothed
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_smoothed).reversed())
                        .filter(c -> c.getNew_deaths_smoothed() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NT")) {
                // return id of the max value of new tests
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_tests).reversed())
                        .filter(c -> c.getNew_tests() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else if (menu.getBy().toUpperCase().equals("NDPC")) {
                // return id of the max value of new deaths per case
                id = covidStatisticsDataList.stream()
                        .sorted(Comparator.comparing(CovidStatisticsData::getNew_deaths_per_case).reversed())
                        .filter(c -> c.getNew_deaths_per_case() > 0)
                        .map(CovidStatisticsData::getId)
                        .limit(menu.getLimit())
                        .collect(Collectors.toList());
            }
            else {
                System.out.println("Invalid input");
                id = null;
            }

            if (menu.getDisplay().toUpperCase().equals("DATE:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
                        .map(LocationData::getDate)
                        .forEach(System.out::println);
            }
            else if (menu.getDisplay().toUpperCase().equals("COUNTRY:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
                        .map(LocationData::getLocation)
                        .forEach(System.out::println);
            }
            else if (menu.getDisplay().toUpperCase().equals("CONTINENT:")) {
                locationDataList.stream()
                        .filter(locationData -> {
                            assert id != null;
                            return id.contains(locationData.getId());
                        })
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


    }//go

    @Contract(pure = true)
    private static @NotNull String ifNull(@NotNull String s) {
        if(s.equals("")) {
            return "-1";
        }
        return s;
    }

    @org.jetbrains.annotations.Nullable
    public static String getPath() {

        try (InputStream input = new FileInputStream("src/main/resources/persistence.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);


            // get the property value and print it out
            return prop.getProperty("user.dir");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static @NotNull String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }
    public static @NotNull String createID1()
    {
        return String.valueOf(idCounter1.getAndIncrement());
    }

}
