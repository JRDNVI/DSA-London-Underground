package com.example.assign2_dsa2_dbp_jc;

import com.opencsv.bean.CsvBindByName;

public class Zone1Station {

    @CsvBindByName(column = "line_name")
    private String lineName;

    @CsvBindByName(column = "station_name")
    private String stationName;

    @CsvBindByName(column = "color")
    private String colour;

    @CsvBindByName(column = "x")
    private int xCoord;

    @CsvBindByName(column = "y")
    private int yCoord;

    public Zone1Station(String lineName, String stationName, String colour, int xCoord, int yCoord) {
        this.lineName = lineName;
        this.stationName = stationName;
        this.colour = colour;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Zone1Station() {

    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return "Zone1Station{" +
                "lineName='" + lineName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", colour='" + colour + '\'' +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}
