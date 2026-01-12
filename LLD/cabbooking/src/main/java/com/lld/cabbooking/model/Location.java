package com.lld.cabbooking.model;

public class Location {
    private double x;
    private double y;

    public Location(int i, int i1) {
        this.x = i;
        this.y = i1;
    }

    public double distance(Location fromLocation) {
        return Math.sqrt(Math.pow(fromLocation.x - x, 2) + Math.pow(fromLocation.y - y, 2));
    }
}
