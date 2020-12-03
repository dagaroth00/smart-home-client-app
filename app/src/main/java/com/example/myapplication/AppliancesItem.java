package com.example.myapplication;

public class AppliancesItem {
    private String baseStationId = "0000";
    private String routerId = "0000";
    private String deviceName  = "name";
    private String status = "on";

    public AppliancesItem() {

    }

    public AppliancesItem(String baseStationId, String routerId, String deviceName, String status) {
        this.baseStationId = baseStationId;
        this.routerId = routerId;
        this.deviceName = deviceName;
        this.status = status;
    }

    public String getBaseStationId() {
        return baseStationId;
    }

    public String getRouterId() {
        return routerId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getStatus() {
        return status;
    }
}