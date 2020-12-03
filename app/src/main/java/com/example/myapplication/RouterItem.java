package com.example.myapplication;

public class RouterItem  {

    private String name = "Router";
    private String routerId = "0000";
    private String status = "on";
    private String device1 = "on";
    private String device2 = "on";
    private String device3 = "on";
    private String device4 = "on";

    public RouterItem(){


    }

    public RouterItem(String name, String routerId, String status){
        this.name = name;
        this.routerId = routerId;
        this.status = status;

    }

    public String getName() {
        return name;
    }

    public String getRouterId() {
        return routerId;
    }

    public String getStatus() {
        return status;
    }

    public String getDevice1() {
        return device1;
    }

    public String getDevice2() {
        return device2;
    }

    public String getDevice3() {
        return device3;
    }

    public String getDevice4() {
        return device4;
    }
}
