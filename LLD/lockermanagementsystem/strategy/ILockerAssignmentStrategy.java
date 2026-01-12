package org.lockermanagementsystem.strategy;

import org.lockermanagementsystem.models.Locker;
import org.lockermanagementsystem.models.LockerSize;
import org.lockermanagementsystem.models.PackageSize;

import java.util.List;
import java.util.Map;

public interface ILockerAssignmentStrategy {
    List<Locker> getSelectableLockers(Map<LockerSize, List<Locker>> sizeToLockersMap, PackageSize packageSize);
}
