package com.shepherdjerred.stranks.objects.trackers;

import com.shepherdjerred.stranks.objects.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ranks {

    private final HashMap<Integer, Rank> ranks = new HashMap<>();

    public void addRank(Rank rank) {
        ranks.put(rank.getId(), rank);
    }

    public Rank getRank(int rankId) {
        return ranks.get(rankId);
    }

    public List<Rank> getRanksAsList() {
        return new ArrayList<>(ranks.values());
    }

}
