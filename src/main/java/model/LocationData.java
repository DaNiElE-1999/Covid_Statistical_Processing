package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LocationData {

    private int id;
    private String iso_code;              //1 (COD) /t1
    private String continent;            //2 (CNT) /t2
    private String location;            //3 (LOC) /t3
    private String stringency_index;                //14 (SI) /t48
    private String population;                        //15 (POP) /t49
    private String median_age;                   //16 (MA) /t51


}
