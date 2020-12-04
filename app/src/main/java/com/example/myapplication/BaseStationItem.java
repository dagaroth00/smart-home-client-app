package com.example.myapplication;

public class BaseStationItem {
    private String baseStationName ="";
    private String baseStationId = "";
    private String userId = "";

    public String getUserId() {
        return userId;
    }

    public BaseStationItem() {

    }

    public BaseStationItem(String baseStationName, String baseStationId, String userId) {
        this.baseStationName = baseStationName;
        this.baseStationId = baseStationId;
        this.userId = userId;
    }

    public String getBaseStationName() {
        return baseStationName;
    }

    public String getBaseStationId() {
        return baseStationId;
    }
}
