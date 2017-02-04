package com.shepherdjerred.stranks.controllers;

import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.economy.Economy;
import com.shepherdjerred.stranks.exceptions.RankException;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;
import org.bukkit.entity.Player;

public class RankPlayerController {

    private final long MILLISECONDS_IN_DAY = 3600000000000L;

    private Ranks ranks;
    private RankPlayers rankPlayers;
    private Economy economy;
    private RankPlayerDAO rankPlayerDAO;

    public void rankUpPlayer(Player player) throws RankException {

        RankPlayer rankPlayer = rankPlayers.getPlayer(player.getUniqueId());
        Rank currentRank = ranks.getRank(rankPlayer.getRank());
        Rank nextRank = ranks.getRank(rankPlayer.getRank() + 1);

        if (nextRank == null) {
            throw new RankException("No rank exists after the players current rank");
        }

        if (!economy.hasEnough(player, nextRank.getCost())) {
            throw new RankException("Player doesn't have enough money");
        }

        if (rankPlayer.getLastRankUp() - System.currentTimeMillis() < MILLISECONDS_IN_DAY) {
            throw new RankException("Player can't rank up more than once per day");
        }

        economy.charge(player, nextRank.getCost());

        rankPlayer.setRank(nextRank.getId());
        rankPlayer.setLastRankUp(System.currentTimeMillis());

        rankPlayerDAO.setRank(rankPlayer);
        rankPlayerDAO.setLastRankUp(rankPlayer);

    }

}
