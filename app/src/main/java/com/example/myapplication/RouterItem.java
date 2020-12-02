package com.example.myapplication;

public class RouterItem  {

    private String name = "Router";
    private String routerId = "0000";
    private String status = "on";


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
}
