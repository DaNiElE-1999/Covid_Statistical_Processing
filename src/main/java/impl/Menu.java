package impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {

    private String status; //max or min
    private Integer limit; // 1 - 100
    private String by; // NC, NCS, ND, NDS, NT, NDPC
    private String display;
    private String path;
    // DATE, COUNTRY, CONTINENT
    public  Menu() {}

    public Menu(String status, Integer limit, String by, String display, String path) {
        this.status = status;
        this.limit = limit;
        this.by = by;
        this.display = display;
        this.path = path;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "-file " + path + " -status " + status + " -limit " + limit + " -by " + by + " -display " + display;
    }
}
