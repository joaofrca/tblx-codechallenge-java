package com.tblx.api.Model;

public class VehicleTrace
{

    private String timestamp;
    private String lon;
    private String lat;

    public VehicleTrace(String timestamp, String lon, String lat) {
        this.timestamp = timestamp;
        this.lon = lon;
        this.lat = lat;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

}
