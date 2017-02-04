package com.shepherdjerred.stranks.objects.trackers;

import com.shepherdjerred.stranks.objects.RankPlayer;

import java.util.HashMap;
import java.util.UUID;

public class RankPlayers {

    private final HashMap<UUID, RankPlayer> rankPlayers = new HashMap<>();

    public RankPlayer getPlayer(UUID rankPlayerUuid) {
        return rankPlayers.get(rankPlayerUuid);
    }

    public void addPlayer(RankPlayer rankPlayer) {
        rankPlayers.put(rankPlayer.getUuid(), rankPlayer);
    }

    public boolean contains(RankPlayer rankPlayer) {
        return rankPlayers.containsKey(rankPlayer.getUuid());
    }

    public void removePlayer(RankPlayer rankPlayer) {
        rankPlayers.remove(rankPlayer.getUuid());
    }

    public void removePlayer(UUID rankPlayerUuid) {
        rankPlayers.remove(rankPlayerUuid);
    }


}
