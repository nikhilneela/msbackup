package com.lld.cabbooking.model;

enum TRIP_STATUS {
    INPROGRESS,
    COMPLETED
}

public class Trip {
    int id;
    Rider rider;
    Cab cab;
    double price;
    Location startLocation;
    Location endLocation;
    TRIP_STATUS status;
}
