package com.lld.cabbooking.managers;

import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class CabsManager {
    HashMap<String, Cab> cabs = new HashMap<>();

    public CabsManager() {

    }

    public void registerCab(Cab newCab)
    {
        if (cabs.containsKey(newCab.getId())) {
            //todo : comeup with proper exception classes
            throw new RuntimeException();
        }
        cabs.put(newCab.getId(), newCab);
    }

    public void updateCabAvailability(String cabId, Boolean isAvailable) {
        if (!cabs.containsKey(cabId)) {
            throw new RuntimeException();
        }
        cabs.get(cabId).setAvailable(isAvailable);
    }

    public void updateCabLocation(String cabId, Location newLocation) {
        if (!cabs.containsKey(cabId)) {
            throw new RuntimeException();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public List<Cab> getCabs(Location fromPoint, int distance) {
        List<Cab> result = new ArrayList<>();
        for (Cab cab : cabs.values()) {
            if (cab.isAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
                result.add(cab);
            }
        }
        return result;
    }
}
