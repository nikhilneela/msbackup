package com.lld.cabbooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cab {
    String id;
    String driverName;
    @Setter
    Location currentLocation;
    @Setter
    boolean isAvailable;

    public Cab(String cabId, String driverName) {
        this.id = cabId;
        this.driverName = driverName;
        this.isAvailable = true;
    }
}
