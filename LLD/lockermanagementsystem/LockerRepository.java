package org.lockermanagementsystem;

import org.lockermanagementsystem.models.Locker;
import org.lockermanagementsystem.models.LockerSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

public class LockerRepository {
    private List<Locker> lockers;

    public LockerRepository() {
        lockers = new ArrayList<>();
    }

    public void addLocker(Locker locker) {
        lockers.add(locker);
    }

    public void removeLocker(String id) {
        this.lockers.removeIf(locker -> locker.getId().equals(id));
    }

    public List<Locker> getLockers(LockerSize lockerSize) {
        return this.lockers.stream().filter(locker -> lockerSize == locker.getSize()).collect(Collectors.toList());
    }
}
