package model;

import java.util.Objects;

public class Data {

    private String iso_code;              //1 (COD)
    private String continent;            //2 (CNT)
    private String location;            //3 (LOC)
    private String date;               //4 (DT)
    private String total_cases;       //5 (TC)
    private String new_cases;                   //6 (NC)
    private String new_cases_smoothed;         //7 (NCS)
    private String total_deaths;              //8 (TD)
    private String new_deaths;               //9 (ND)
    private String new_deaths_smoothed;     //10 (NDS)
    private String reproduction_rate;      //11 (RR)
    private String new_tests;                         //12 (NT)
    private String total_tests;                      //13 (TT)
    private String stringency_index;                //14 (SI)
    private String population;                     //15 (POP)
    private String median_age;                    //16 (MA)

    public Data() {

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

    public String getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(String total_cases) {
        this.total_cases = total_cases;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(String new_cases) {
        this.new_cases = new_cases;
    }

    public String getNew_cases_smoothed() {
        return new_cases_smoothed;
    }

    public void setNew_cases_smoothed(String new_cases_smoothed) {
        this.new_cases_smoothed = new_cases_smoothed;
    }

    public String getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(String total_deaths) {
        this.total_deaths = total_deaths;
    }

    public String getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(String new_deaths) {
        this.new_deaths = new_deaths;
    }

    public String getNew_deaths_smoothed() {
        return new_deaths_smoothed;
    }

    public void setNew_deaths_smoothed(String new_deaths_smoothed) {
        this.new_deaths_smoothed = new_deaths_smoothed;
    }

    public String getReproduction_rate() {
        return reproduction_rate;
    }

    public void setReproduction_rate(String reproduction_rate) {
        this.reproduction_rate = reproduction_rate;
    }

    public String getNew_tests() {
        return new_tests;
    }

    public void setNew_tests(String new_tests) {
        this.new_tests = new_tests;
    }

    public String getTotal_tests() {
        return total_tests;
    }

    public void setTotal_tests(String total_tests) {
        this.total_tests = total_tests;
    }

    public String getStringency_index() {
        return stringency_index;
    }

    public void setStringency_index(String stringency_index) {
        this.stringency_index = stringency_index;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getMedian_age() {
        return median_age;
    }

    public void setMedian_age(String median_age) {
        this.median_age = median_age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(iso_code, data.iso_code) && Objects.equals(continent, data.continent) && Objects.equals(location, data.location) && Objects.equals(date, data.date) && Objects.equals(total_cases, data.total_cases) && Objects.equals(new_cases, data.new_cases) && Objects.equals(new_cases_smoothed, data.new_cases_smoothed) && Objects.equals(total_deaths, data.total_deaths) && Objects.equals(new_deaths, data.new_deaths) && Objects.equals(new_deaths_smoothed, data.new_deaths_smoothed) && Objects.equals(reproduction_rate, data.reproduction_rate) && Objects.equals(new_tests, data.new_tests) && Objects.equals(total_tests, data.total_tests) && Objects.equals(stringency_index, data.stringency_index) && Objects.equals(population, data.population) && Objects.equals(median_age, data.median_age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso_code, continent, location, date, total_cases, new_cases, new_cases_smoothed, total_deaths, new_deaths, new_deaths_smoothed, reproduction_rate, new_tests, total_tests, stringency_index, population, median_age);
    }

    public Data(String iso_code, String continent, String location, String date, String total_cases,
                String new_cases, String new_cases_smoothed, String total_deaths, String new_deaths,
                String new_deaths_smoothed, String reproduction_rate, String new_tests, String total_tests,
                String stringency_index, String population, String median_age) {
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
    }

}
