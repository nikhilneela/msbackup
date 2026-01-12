package org.example.filter.strategy;

import org.example.filter.filterdata.TimeSlotFilterData;
import org.example.model.FilterType;
import org.example.model.InputFilter;
import org.example.model.TimeSlot;
import org.example.model.Vehicle;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

public class TimeSlotFilterStrategy implements IFilterStrategy {
    @Override
    public boolean doesSupport(InputFilter filter) {
        return filter.getType() == FilterType.TIMESLOT;
    }

    @Override
    public List<Vehicle> apply(InputFilter filter, List<Vehicle> vehicles) {
        if (!(filter.getFilterData() instanceof TimeSlotFilterData)) {
            throw new RuntimeException("Invalid InputFilterData");
        }
        TimeSlotFilterData slotFilterData = (TimeSlotFilterData) filter.getFilterData();

        //from booking service, find the free slots for each of the vehicle and check if a vehicle is NOT booked
        //for this timeslot

        return List.of();
    }

    @Override
    public InputFilter parse(String json) {
        JSONObject object = new JSONObject(json);
        LocalDateTime startTime = LocalDateTime.parse(object.getString("start"));
        LocalDateTime endTime = LocalDateTime.parse(object.getString("end"));
        return new InputFilter(FilterType.TIMESLOT, new TimeSlotFilterData(new TimeSlot(startTime, endTime)));
    }
}
