package org.lockermanagementsystem.managers;

import org.lockermanagementsystem.LockerRepository;
import org.lockermanagementsystem.models.*;
import org.lockermanagementsystem.models.Package;
import org.lockermanagementsystem.strategy.ILockerAssignmentStrategy;
import org.lockermanagementsystem.strategy.ILockerSelectionStrategy;
import org.lockermanagementsystem.strategy.RandomLockerSelectionStrategy;
import org.lockermanagementsystem.strategy.StrictLockerAssignmentStrategy;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.locks.Lock;

public class LockerManager {
    private final LockerRepository lockerRepository;
    private Map<LockerSize, List<Locker>> sizeToLockersMap;
    private final ILockerSelectionStrategy lockerSelectionStrategy;
    private final ILockerAssignmentStrategy lockerAssignmentStrategy;
    private final List<Locker> lockersInUse;

    public LockerManager(LockerRepository repository) {
        this.lockerRepository = repository;
        this.sizeToLockersMap = new HashMap<>();

        for (LockerSize size : LockerSize.values()) {
            sizeToLockersMap.put(size, this.lockerRepository.getLockers(size));
        }

        lockerSelectionStrategy = new RandomLockerSelectionStrategy();
        lockerAssignmentStrategy = new StrictLockerAssignmentStrategy();
        lockersInUse = new ArrayList<>();
    }

    public Locker assignLocker(Package item) throws Exception {
        List<Locker> availableLockers = lockerAssignmentStrategy.getSelectableLockers(sizeToLockersMap, item.getSize());
        Optional<Locker> selectedLocker = lockerSelectionStrategy.selectLocker(availableLockers);

        if (selectedLocker.isEmpty()) {
            //write custom exception classes
            throw new Exception();
        }

        selectedLocker.ifPresent(locker -> {
            locker.setState(LockerState.ASSIGNED);
            locker.setAssignedTime(new Date());
            locker.setOneTimeCode(new OneTimeCode());
            lockersInUse.add(locker);
            removeLockerFromAvailableLockers(locker);
        });

        return selectedLocker.get();
    }

    private void removeLockerFromAvailableLockers(Locker lockerToRemove) {
        for (Map.Entry<LockerSize, List<Locker>> entry : sizeToLockersMap.entrySet()) {
            entry.getValue().removeIf(locker -> lockerToRemove.getId().equals(locker.getId()));
        }
    }


    public List<Locker> getLockersInUse(Duration duration) {
        lockersInUse.stream().filter(locker -> locker.getAssignedTime().)
    }

}
