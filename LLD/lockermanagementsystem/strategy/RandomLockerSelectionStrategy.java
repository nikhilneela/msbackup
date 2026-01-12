package org.lockermanagementsystem.strategy;

import org.lockermanagementsystem.models.Locker;
import org.lockermanagementsystem.models.LockerState;

import java.util.List;
import java.util.Optional;

public class RandomLockerSelectionStrategy implements ILockerSelectionStrategy {
    @Override
    public Optional<Locker> selectLocker(List<Locker> lockers) {
        return lockers.stream().filter(locker -> locker.getState() == LockerState.VACANT).findAny();
    }
}
