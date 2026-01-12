package org.lockermanagementsystem.strategy;

import org.lockermanagementsystem.models.Locker;
import org.lockermanagementsystem.models.LockerSize;
import org.lockermanagementsystem.models.PackageSize;

import java.util.List;
import java.util.Map;

public class StrictLockerAssignmentStrategy implements ILockerAssignmentStrategy {
    @Override
    public List<Locker> getSelectableLockers(Map<LockerSize, List<Locker>> sizeToLockersMap, PackageSize packageSize) {

        return switch (packageSize) {
            case SMALL -> sizeToLockersMap.get(LockerSize.SMALL);
            case MEDIUM -> sizeToLockersMap.get(LockerSize.MEDIUM);
            case LARGE -> sizeToLockersMap.get(LockerSize.LARGE);
        };
    }
}
