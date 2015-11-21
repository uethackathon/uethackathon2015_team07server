package com.uet.quantity.uethackathon2015_team7.model;

/**
 * Created by luongnguyen on 11/21/15.
 */
public class HistoryItem {

    private int id;
    private String year;
    private String day_month;
    private String content;
    private boolean main_event;

    public HistoryItem(int id, String year, String day_month, String content, boolean main_event) {

        this.id = id;
        this.year = year;
        this.day_month = day_month;
        this.content = content;
        this.main_event = main_event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay_month() {
        return day_month;
    }

    public void setDay_month(String day_month) {
        this.day_month = day_month;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMain_event() {
        return main_event;
    }

    public void setMain_event(boolean main_event) {
        this.main_event = main_event;
    }
}
