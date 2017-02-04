package com.shepherdjerred.stranks.objects;

public class Rank {

    private final int id;
    private final String permission;
    private final long cost;

    public Rank(int id, String permission, long cost) {
        this.id = id;
        this.permission = permission;
        this.cost = cost;
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
