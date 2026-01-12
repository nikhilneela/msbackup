package org.lockermanagementsystem.models;

import java.util.Date;

public class Locker {
    private final String id;
    private LockerSize size;
    private LockerState state;
    private OneTimeCode oneTimeCode;
    private Date assignedTime;

    public Locker(String id, LockerSize size) {
        this.id = id;
        this.size = size;
        this.state = LockerState.VACANT;
    }

    public String getId() {
        return id;
    }

    public LockerSize getSize() {
        return size;
    }

    public void setSize(LockerSize size) {
        this.size = size;
    }

    public LockerState getState() {
        return state;
    }

    public void setState(LockerState state) {
        this.state = state;
    }

    public OneTimeCode getOneTimeCode() {
        return oneTimeCode;
    }

    public void setOneTimeCode(OneTimeCode oneTimeCode) {
        this.oneTimeCode = oneTimeCode;
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }
}
