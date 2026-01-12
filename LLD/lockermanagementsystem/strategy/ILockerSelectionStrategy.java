package org.lockermanagementsystem.strategy;

import org.lockermanagementsystem.models.Locker;

import java.util.List;
import java.util.Optional;

public interface ILockerSelectionStrategy {
    Optional<Locker> selectLocker(List<Locker> lockers);
}
