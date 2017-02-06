package com.shepherdjerred.stranks.controllers;

import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.economy.Economy;
import com.shepherdjerred.stranks.exceptions.RankException;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;
import com.shepherdjerred.stranks.permissions.Permission;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class RankPlayerController {

    private final long MILLISECONDS_IN_DAY = TimeUnit.DAYS.toMillis(1);

    private Ranks ranks;
    private RankPlayers rankPlayers;
    private Economy economy;
    private RankPlayerDAO rankPlayerDAO;
    private Permission permission;

    public RankPlayerController(Ranks ranks, RankPlayers rankPlayers, Economy economy, RankPlayerDAO rankPlayerDAO, Permission permission) {
        this.ranks = ranks;
        this.rankPlayers = rankPlayers;
        this.economy = economy;
        this.rankPlayerDAO = rankPlayerDAO;
        this.permission = permission;
    }

    public void rankUpPlayer(Player player) throws RankException {

        RankPlayer rankPlayer = rankPlayers.getPlayer(player.getUniqueId());
        Rank currentRank = ranks.getRank(rankPlayer.getRank());
        Rank nextRank = ranks.getRank(currentRank.getId() + 1);

        if (nextRank == null) {
            throw new RankException("No rank exists after the players current rank");
        }

        if (!economy.hasEnough(player, nextRank.getCost())) {
            throw new RankException("Player doesn't have enough money");
        }

        if (rankPlayer.getLastRankUp() + MILLISECONDS_IN_DAY < System.currentTimeMillis()) {
            throw new RankException("Player can't rank up more than once per day");
        }

        economy.charge(player, nextRank.getCost());

        rankPlayer.setRank(nextRank.getId());
        rankPlayer.setLastRankUp(System.currentTimeMillis());

        permission.givePermission(player, nextRank.getPermission());

        rankPlayerDAO.setRank(rankPlayer);
        rankPlayerDAO.setLastRankUp(rankPlayer);

    }

}
