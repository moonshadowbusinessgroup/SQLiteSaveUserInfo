package com.example.sqlitenote;

public class Model {
    private long order_No;
    private long status;
    private String user_Name;
    private String user_Location;
    private String phone_Number;

    Model(long order_No, long status, String user_Name, String user_Location, String phone_Number) {
        this.order_No = order_No;
        this.status = status;
        this.user_Name = user_Name;
        this.user_Location = user_Location;
        this.phone_Number = phone_Number;
    }

    Model(long status, String user_Name, String user_Location, String phone_Number) {
        this.status = status;
        this.user_Name = user_Name;
        this.user_Location = user_Location;
        this.phone_Number = phone_Number;
    }

    Model() {
        // empty constructor
    }

    public long getOrder_No() {
        return order_No;
    }

    public void setOrder_No(long order_No) {
        this.order_No = order_No;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Location() {
        return user_Location;
    }

    public void setUser_Location(String user_Location) {
        this.user_Location = user_Location;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }
}
