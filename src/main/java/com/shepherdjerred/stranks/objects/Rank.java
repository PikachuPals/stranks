package com.shepherdjerred.stranks.objects;

public class Rank {

    private final int id;
    private final String permission;
    private final long cost;
    private final String description;

    public Rank(int id, String permission, long cost, String description) {
        this.id = id;
        this.permission = permission;
        this.cost = cost;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public long getCost() {
        return cost;
    }
}
