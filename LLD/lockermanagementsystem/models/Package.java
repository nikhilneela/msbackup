package org.lockermanagementsystem.models;

public class Package {
    private String id;
    private String userId;

    private PackageSize size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PackageSize getSize() {
        return size;
    }

    public void setSize(PackageSize size) {
        this.size = size;
    }
}
