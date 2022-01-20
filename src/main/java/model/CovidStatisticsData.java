package model;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CovidStatisticsData {

    private int id;
    private String iso_code;              //1 (COD) /t1
    private int total_cases;          //5 (TC) /t5
    private int new_cases;                      //6 (NC) /t6
    private double new_cases_smoothed;         //7 (NCS) /t7
    private int total_deaths;                 //8 (TD) /t8
    private int new_deaths;                  //9 (ND) /t9
    private double new_deaths_smoothed;     //10 (NDS) /t10
    private double reproduction_rate;      //11 (RR) /t17
    private int new_tests;                            //12 (NT) /t26
    private int total_tests;                         //13 (TT) /t27
    private int new_deaths_per_case;             //17 (NDPC) --> Not in the CSV

    public int getNew_deaths_per_case() {
        return new_deaths/new_cases;
    }

}
