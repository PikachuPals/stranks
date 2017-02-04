package com.shepherdjerred.stranks.objects.trackers;

import com.shepherdjerred.stranks.objects.Rank;

import java.util.HashMap;

public class Ranks {

    private final HashMap<Integer, Rank> rankPlayers = new HashMap<>();

    public Rank getRank(int rankId) {
        return rankPlayers.get(rankId);
    }

}
