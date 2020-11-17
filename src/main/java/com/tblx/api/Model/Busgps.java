package com.tblx.api.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "busgps")
public class Busgps
{
    //@id?
    private String timestamp;
    private String lineID;
    private String direction;
    private String journeyPatternID;
    private String timeframe;//devia ser date..
    private String vehicleJourneyID;
    private String operator; // minlength: 2, maxlength: 2
    private String congestion;
    private String lon;
    private String lat;
    private String delay;
    private String blockID;
    private String vehicleID;
    private String stopID;
    private String atStop;

//    public Busgps() {
//        super();
//    }

    public Busgps(String timestamp, String lineID, String direction, String journeyPatternID, String timeframe, String vehicleJourneyID, String operator, String congestion, String lon, String lat, String delay, String blockID, String vehicleID, String stopID, String atStop) {
//        super();
        this.timestamp = timestamp;
        this.lineID = lineID;
        this.direction = direction;
        this.journeyPatternID = journeyPatternID;
        this.timeframe = timeframe;
        this.vehicleJourneyID = vehicleJourneyID;
        this.operator = operator;
        this.congestion = congestion;
        this.lon = lon;
        this.lat = lat;
        this.delay = delay;
        this.blockID = blockID;
        this.vehicleID = vehicleID;
        this.stopID = stopID;
        this.atStop = atStop;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getJourneyPatternID() {
        return journeyPatternID;
    }

    public void setJourneyPatternID(String journeyPatternID) {
        this.journeyPatternID = journeyPatternID;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public String getVehicleJourneyID() {
        return vehicleJourneyID;
    }

    public void setVehicleJourneyID(String vehicleJourneyID) {
        this.vehicleJourneyID = vehicleJourneyID;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String isCongestion() {
        return congestion;
    }

    public void setCongestion(String congestion) {
        this.congestion = congestion;
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

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String isAtStop() {
        return atStop;
    }

    public void setAtStop(String atStop) {
        this.atStop = atStop;
    }
}
