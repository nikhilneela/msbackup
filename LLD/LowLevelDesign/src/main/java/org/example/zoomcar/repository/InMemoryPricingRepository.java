package org.example.zoomcar.repository;

import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.Price;
import org.example.zoomcar.model.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryPricingRepository implements IPricingRepository {
    private final List<Price> prices;

    public InMemoryPricingRepository() {
        this.prices = new ArrayList<>();
    }

    @Override
    public void addPrice(Branch branch, VehicleType type, double price) {
        Price _price = new Price(UUID.randomUUID().toString(), branch.getId(), type, price);
        prices.add(_price);
    }

    @Override
    public double getPrice(Branch branch, VehicleType type) {
        return prices.stream().filter(price -> price.getBranchId().equalsIgnoreCase(branch.getId()) && price.getVehicleType().equals(type)).findFirst().orElseThrow().getPrice();
    }
}
