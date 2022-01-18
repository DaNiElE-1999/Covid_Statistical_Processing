package model;

import java.util.*;
import java.util.Objects;

public class Data {

    private String iso_code;              //1 (COD) /t1
    private String continent;            //2 (CNT) /t2
    private String location;            //3 (LOC) /t3
    private String date;               //4 (DT) --> May be modified, yyyy-mm-dd /t4
    private int total_cases;          //5 (TC) /t5
    private int new_cases;                      //6 (NC) /t6
    private double new_cases_smoothed;         //7 (NCS) /t7
    private int total_deaths;                 //8 (TD) /t8
    private int new_deaths;                  //9 (ND) /t9
    private double new_deaths_smoothed;     //10 (NDS) /t10
    private double reproduction_rate;      //11 (RR) /t17
    private int new_tests;                            //12 (NT) /t26
    private int total_tests;                         //13 (TT) /t27
    private double stringency_index;                //14 (SI) /t48
    private int population;                        //15 (POP) /t49
    private double median_age;                    //16 (MA) /t51
    private int new_deaths_per_case;             //17 (NDPC) --> Not in the CSV

    public Data() {

    }

    public Data(String iso_code, String continent, String location, String date, int total_cases, int new_cases, double new_cases_smoothed, int total_deaths, int new_deaths, double new_deaths_smoothed, double reproduction_rate, int new_tests, int total_tests, double stringency_index, int population, double median_age, int new_deaths_per_case) {
        this.iso_code = iso_code;
        this.continent = continent;
        this.location = location;
        this.date = date;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.new_cases_smoothed = new_cases_smoothed;
        this.total_deaths = total_deaths;
        this.new_deaths = new_deaths;
        this.new_deaths_smoothed = new_deaths_smoothed;
        this.reproduction_rate = reproduction_rate;
        this.new_tests = new_tests;
        this.total_tests = total_tests;
        this.stringency_index = stringency_index;
        this.population = population;
        this.median_age = median_age;
        this.new_deaths_per_case = new_deaths_per_case;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public int getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(int new_cases) {
        this.new_cases = new_cases;
    }

    public double getNew_cases_smoothed() {
        return new_cases_smoothed;
    }

    public void setNew_cases_smoothed(double new_cases_smoothed) {
        this.new_cases_smoothed = new_cases_smoothed;
    }

    public int getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(int total_deaths) {
        this.total_deaths = total_deaths;
    }

    public int getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(int new_deaths) {
        this.new_deaths = new_deaths;
    }

    public double getNew_deaths_smoothed() {
        return new_deaths_smoothed;
    }

    public void setNew_deaths_smoothed(double new_deaths_smoothed) {
        this.new_deaths_smoothed = new_deaths_smoothed;
    }

    public double getReproduction_rate() {
        return reproduction_rate;
    }

    public void setReproduction_rate(double reproduction_rate) {
        this.reproduction_rate = reproduction_rate;
    }

    public int getNew_tests() {
        return new_tests;
    }

    public void setNew_tests(int new_tests) {
        this.new_tests = new_tests;
    }

    public int getTotal_tests() {
        return total_tests;
    }

    public void setTotal_tests(int total_tests) {
        this.total_tests = total_tests;
    }

    public double getStringency_index() {
        return stringency_index;
    }

    public void setStringency_index(double stringency_index) {
        this.stringency_index = stringency_index;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getMedian_age() {
        return median_age;
    }

    public void setMedian_age(double median_age) {
        this.median_age = median_age;
    }

    public int getNew_deaths_per_case() {
        return new_deaths/new_cases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return total_cases == data.total_cases && new_cases == data.new_cases && Double.compare(data.new_cases_smoothed, new_cases_smoothed) == 0 && total_deaths == data.total_deaths && new_deaths == data.new_deaths && Double.compare(data.new_deaths_smoothed, new_deaths_smoothed) == 0 && Double.compare(data.reproduction_rate, reproduction_rate) == 0 && new_tests == data.new_tests && total_tests == data.total_tests && Double.compare(data.stringency_index, stringency_index) == 0 && population == data.population && Double.compare(data.median_age, median_age) == 0 && new_deaths_per_case == data.new_deaths_per_case && Objects.equals(iso_code, data.iso_code) && Objects.equals(continent, data.continent) && Objects.equals(location, data.location) && Objects.equals(date, data.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso_code, continent, location, date, total_cases, new_cases, new_cases_smoothed, total_deaths, new_deaths, new_deaths_smoothed, reproduction_rate, new_tests, total_tests, stringency_index, population, median_age, new_deaths_per_case);
    }

    @Override
    public String toString() {
        return "Data{" +
                "iso_code='" + iso_code + '\'' +
                ", continent='" + continent + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", total_cases=" + total_cases +
                ", new_cases=" + new_cases +
                ", new_cases_smoothed=" + new_cases_smoothed +
                ", total_deaths=" + total_deaths +
                ", new_deaths=" + new_deaths +
                ", new_deaths_smoothed=" + new_deaths_smoothed +
                ", reproduction_rate=" + reproduction_rate +
                ", new_tests=" + new_tests +
                ", total_tests=" + total_tests +
                ", stringency_index=" + stringency_index +
                ", population=" + population +
                ", median_age=" + median_age +
                ", new_deaths_per_case=" + new_deaths_per_case +
                '}';
    }
}

