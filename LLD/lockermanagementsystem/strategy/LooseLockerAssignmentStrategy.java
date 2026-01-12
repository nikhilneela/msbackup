package org.lockermanagementsystem.strategy;

import org.lockermanagementsystem.models.Locker;
import org.lockermanagementsystem.models.LockerSize;
import org.lockermanagementsystem.models.PackageSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LooseLockerAssignmentStrategy implements ILockerAssignmentStrategy {
    @Override
    public List<Locker> getSelectableLockers(Map<LockerSize, List<Locker>> sizeToLockersMap, PackageSize packageSize) {
        List<Locker> selectableLockers = new ArrayList<>();

        if (packageSize == PackageSize.LARGE) {
            // Add all large lockers
            List<Locker> largeLockers = sizeToLockersMap.get(LockerSize.LARGE);
            if (largeLockers != null) {
                selectableLockers.addAll(largeLockers);
            }
        } else if (packageSize == PackageSize.MEDIUM) {
            // Add all medium and large lockers
            List<Locker> mediumLockers = sizeToLockersMap.get(LockerSize.MEDIUM);
            if (mediumLockers != null) {
                selectableLockers.addAll(mediumLockers);
            }

            List<Locker> largeLockers = sizeToLockersMap.get(LockerSize.LARGE);
            if (largeLockers != null) {
                selectableLockers.addAll(largeLockers);
            }
        } else if (packageSize == PackageSize.SMALL) {
            // Add all small, medium, and large lockers
            List<Locker> smallLockers = sizeToLockersMap.get(LockerSize.SMALL);
            if (smallLockers != null) {
                selectableLockers.addAll(smallLockers);
            }

            List<Locker> mediumLockers = sizeToLockersMap.get(LockerSize.MEDIUM);
            if (mediumLockers != null) {
                selectableLockers.addAll(mediumLockers);
            }

            List<Locker> largeLockers = sizeToLockersMap.get(LockerSize.LARGE);
            if (largeLockers != null) {
                selectableLockers.addAll(largeLockers);
            }
        }

        return selectableLockers;
    }
}
