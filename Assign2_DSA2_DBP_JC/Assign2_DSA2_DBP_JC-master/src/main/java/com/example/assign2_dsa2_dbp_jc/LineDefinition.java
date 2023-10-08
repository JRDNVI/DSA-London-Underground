package com.example.assign2_dsa2_dbp_jc;

import com.opencsv.bean.CsvBindByName;

public class LineDefinition {
    @CsvBindByName(column = "station1")
    public int stationOneID;

    @CsvBindByName(column = "station2")
    public int stationTwoID;

    @CsvBindByName(column = "line")
    public int line;

    public LineDefinition(int stationOneID, int stationTwoID, int line) {
        this.stationOneID = stationOneID;
        this.stationTwoID = stationTwoID;
        this.line = line;
    }

    public LineDefinition() {

    }

    public int getStationOneID() {
        return stationOneID;
    }

    public void setStationOneID(int stationOneID) {
        this.stationOneID = stationOneID;
    }

    public int getStationTwoID() {
        return stationTwoID;
    }

    public void setStationTwoID(int stationTwoID) {
        this.stationTwoID = stationTwoID;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "LineDefinition{" +
                "stationOneID=" + stationOneID +
                ", stationTwoID=" + stationTwoID +
                ", line=" + line +
                '}';
    }
}
