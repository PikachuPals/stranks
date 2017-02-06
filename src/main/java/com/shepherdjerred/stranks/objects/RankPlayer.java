package com.shepherdjerred.stranks.objects;

import java.util.UUID;

public class RankPlayer {

    private final UUID uuid;
    private int rank;
    private long timeInMillisSinceLastRankUp;

    public RankPlayer(UUID uuid, int rank, long timeInMillisSinceLastRankUp) {
        this.uuid = uuid;
        this.rank = rank;
        this.timeInMillisSinceLastRankUp = timeInMillisSinceLastRankUp;
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

    @Override
    public String toString() {
        return "RankPlayer{" +
                "uuid=" + uuid +
                ", rank=" + rank +
                ", timeInMillisSinceLastRankUp=" + timeInMillisSinceLastRankUp +
                '}';
    }
}
