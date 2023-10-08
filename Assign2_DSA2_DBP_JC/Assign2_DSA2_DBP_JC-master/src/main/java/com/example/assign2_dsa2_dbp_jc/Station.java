package com.example.assign2_dsa2_dbp_jc;

import com.opencsv.bean.CsvBindByName;

public class Station {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "latitude")
    private double latitude;

    @CsvBindByName(column = "longitude")
    private double longitude;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "display_name")
    private String displayName;

    @CsvBindByName(column = "zone")
    private String zone;

    @CsvBindByName(column = "total_lines")
    private int totalLines;

    @CsvBindByName(column = "rail")
    private int rail;

    private int xCoord;
    private int yCoord;

    public Station(int id, double latitude, double longitude, String name, String displayName, String zone, int totalLines, int rail) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.displayName = displayName;
        this.zone = zone;
        this.totalLines = totalLines;
        this.rail = rail;
    }

    public Station() {

    }

    public Station(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    public int getRail() {
        return rail;
    }

    public void setRail(int rail) {
        this.rail = rail;
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
        return "\nStation{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", zone='" + zone + '\'' +
                ", totalLines=" + totalLines +
                ", rail=" + rail +
                '}';
    }

    public String listViewString() {
        return "StationID: " + id +
                "   Name: " + name +
                "   Zone: " + zone;
    }
}
