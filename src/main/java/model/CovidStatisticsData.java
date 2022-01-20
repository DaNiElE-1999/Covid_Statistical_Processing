package model;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
//@Data
public class CovidStatisticsData {

    private int id;
    private String Date;              //1 (COD) /t1
    private double total_cases;          //5 (TC) /t5
    private double new_cases;                      //6 (NC) /t6
    private double new_cases_smoothed;         //7 (NCS) /t7
    private double total_deaths;                 //8 (TD) /t8
    private double new_deaths;                  //9 (ND) /t9
    private double new_deaths_smoothed;     //10 (NDS) /t10
    private double reproduction_rate;      //11 (RR) /t17
    private double new_tests;                            //12 (NT) /t26
    private double total_tests;                         //13 (TT) /t27
    private double new_deaths_per_case;             //17 (NDPC) --> Not in the CSV

    public CovidStatisticsData(int id, String date, double total_cases, double new_cases, double new_cases_smoothed, double total_deaths, double new_deaths, double new_deaths_smoothed, double reproduction_rate, double new_tests, double total_tests) {
        this.id = id;
        Date = date;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.new_cases_smoothed = new_cases_smoothed;
        this.total_deaths = total_deaths;
        this.new_deaths = new_deaths;
        this.new_deaths_smoothed = new_deaths_smoothed;
        this.reproduction_rate = reproduction_rate;
        this.new_tests = new_tests;
        this.total_tests = total_tests;
    }

    public double getNew_deaths_per_case() {
        if (new_cases == 0) {
            return 0;
        }
            return new_deaths / new_cases;
    }

}
