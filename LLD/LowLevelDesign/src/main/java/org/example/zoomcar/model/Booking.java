package org.example.zoomcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Booking {
    private String id;
    private TimeSlot slot;
    private VehicleType type;
    private String branch;
    private double price;
    private String vehicleRegNumber;

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", slot=" + slot +
                ", type=" + type +
                ", branch='" + branch + '\'' +
                ", price=" + price +
                ", vehicleRegNumber='" + vehicleRegNumber + '\'' +
                '}';
    }
}
