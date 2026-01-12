package com.lld.cabbooking.controllers;

import com.lld.cabbooking.managers.CabsManager;
import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabsController {

    private CabsManager cabsManager;

    @Autowired
    public CabsController(CabsManager cabsManager) {
        this.cabsManager = cabsManager;
    }

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity registerCab(String cabId, String driverName) {
        cabsManager.registerCab(new Cab(cabId, driverName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/nearest/cabs", method = RequestMethod.POST)
    public ResponseEntity getCabs(HttpServletRequest request, @RequestBody Location location) {
        System.out.println(location.distance(new Location(1, 1)));
        return ResponseEntity.ok("");
    }
}
