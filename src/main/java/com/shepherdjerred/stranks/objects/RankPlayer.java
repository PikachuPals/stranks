package com.shepherdjerred.stranks.objects;

import java.util.UUID;

public class RankPlayer {

    private final UUID uuid;
    private int rank;
    private long lastRankUp;

    public RankPlayer(UUID uuid, int rank, long lastRankUp) {
        this.uuid = uuid;
        this.rank = rank;
        this.lastRankUp = lastRankUp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getLastRankUp() {
        return lastRankUp;
    }

    public void setLastRankUp(long lastRankUp) {
        this.lastRankUp = lastRankUp;
    }
}
