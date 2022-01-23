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
    private String Date;                   //1 (COD) /t1
    private String iso_code;              //1 (COD) /t1
    private String continent;            //2 (CNT) /t2
    private String location;            //3 (LOC) /t3
    private double stringency_index;                //14 (SI) /t48
    private double population;                     //15 (POP) /t49
    private double median_age;                   //16 (MA) /t51

    public LocationData(int id, String Date, String iso_code, String continent, String location, double stringency_index, double population, double median_age) {
        this.id = id;
        this.iso_code = iso_code;
        this.continent = continent;
        this.location = location;
        this.stringency_index = stringency_index;
        this.population = population;
        this.median_age = median_age;
        this.Date = Date;
    }
}
