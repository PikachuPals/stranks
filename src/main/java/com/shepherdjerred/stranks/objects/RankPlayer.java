package com.shepherdjerred.stranks.objects;

import java.util.UUID;

public class RankPlayer {

    private final UUID uuid;
    private int rank;
    private long timeInMillisSinceLastRankUp;
    private boolean hasBeenLoaded;

    public RankPlayer(UUID uuid, int rank, long timeInMillisSinceLastRankUp) {
        this.uuid = uuid;
        this.rank = rank;
        this.timeInMillisSinceLastRankUp = timeInMillisSinceLastRankUp;
        hasBeenLoaded = true;
    }

    public RankPlayer(UUID uuid) {
        this.uuid = uuid;
        hasBeenLoaded = false;
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

    public long getTimeInMillisSinceLastRankUp() {
        return timeInMillisSinceLastRankUp;
    }

    public void setTimeInMillisSinceLastRankUp(long timeInMillisSinceLastRankUp) {
        this.timeInMillisSinceLastRankUp = timeInMillisSinceLastRankUp;
    }

    public boolean hasBeenLoaded() {
        return hasBeenLoaded;
    }

    @Override
    public String toString() {
        return "RankPlayer{" +
                "uuid=" + uuid +
                ", rank=" + rank +
                ", timeInMillisSinceLastRankUp=" + timeInMillisSinceLastRankUp +
                '}';
    }
}
