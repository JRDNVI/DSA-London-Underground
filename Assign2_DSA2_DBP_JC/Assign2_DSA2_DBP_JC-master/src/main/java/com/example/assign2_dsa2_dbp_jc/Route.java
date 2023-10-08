package com.example.assign2_dsa2_dbp_jc;

import com.opencsv.bean.CsvBindByName;

public class Route {

    @CsvBindByName(column = "line")
    public int line;

    @CsvBindByName(column = "name")
    public String name;

    @CsvBindByName(column = "colour")
    public String colour;

    @CsvBindByName(column = "stripe")
    public String stripe;

    public Route(int line, String name, String colour, String stripe) {
        this.line = line;
        this.name = name;
        this.colour = colour;
        this.stripe = stripe;
    }

    public Route() {

    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getNames() {
        return name;
    }

    public void setNames(String names) {
        this.name = names;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getStripe() {
        return stripe;
    }

    public void setStripe(String stripe) {
        this.stripe = stripe;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "line=" + line +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", stripe='" + stripe + '\'' +
                '}';
    }
}
