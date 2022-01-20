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
    private String total_cases;          //5 (TC) /t5
    private String new_cases;                      //6 (NC) /t6
    private String new_cases_smoothed;         //7 (NCS) /t7
    private String total_deaths;                 //8 (TD) /t8
    private String new_deaths;                  //9 (ND) /t9
    private String new_deaths_smoothed;     //10 (NDS) /t10
    private String reproduction_rate;      //11 (RR) /t17
    private String new_tests;                            //12 (NT) /t26
    private String total_tests;                         //13 (TT) /t27
    private String new_deaths_per_case;             //17 (NDPC) --> Not in the CSV

    public int getNew_deaths_per_case() {
        if ((new_cases.equals("") && new_deaths.equals("")) || new_deaths.equals("0")) {
            return 0;
        }
        else {
            return 0;
                //    Integer.parseInt(new_deaths) / Integer.parseInt(new_cases);
        }
    }

}
