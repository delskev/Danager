package be.perzival.danager.utils;


/**
 * Created by Perzival on 06/08/2017.
 */
public class Timer {

    private Integer time;
    private String description;

    public Timer() {
        this.time = -1;
        this.description = "";
    }

    public Timer(Integer time, String description) {
        this.time = time;
        this.description = description;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
