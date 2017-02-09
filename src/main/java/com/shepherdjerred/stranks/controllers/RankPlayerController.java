package com.shepherdjerred.stranks.controllers;

import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.economy.Economy;
import com.shepherdjerred.stranks.exceptions.RankException;
import com.shepherdjerred.stranks.messages.Parser;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;
import com.shepherdjerred.stranks.permissions.Permission;
import com.shepherdjerred.stranks.util.TimeUtils;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class RankPlayerController {

    private final long MILLISECONDS_IN_DAY = TimeUnit.DAYS.toMillis(1);

    private Ranks ranks;
    private RankPlayers rankPlayers;
    private Economy economy;
    private RankPlayerDAO rankPlayerDAO;
    private Permission permission;
    private Parser parser;

    public RankPlayerController(Parser parser, Ranks ranks, RankPlayers rankPlayers, Economy economy, RankPlayerDAO rankPlayerDAO, Permission permission) {
        this.parser = parser;
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
            throw new RankException("No rank exists after the current rank",
                    parser.colorString(true, "buy.atLastRank"));
        }

        if (!economy.hasEnough(player, nextRank.getCost())) {
            throw new RankException("Not enough money",
                    parser.colorString(true, "buy.notEnoughMoney", nextRank.getCost()));
        }

        if (rankPlayer.getTimeInMillisSinceLastRankUp() + MILLISECONDS_IN_DAY > System.currentTimeMillis()) {
            throw new RankException("Can't rank up more than once per day",
                    parser.colorString(true, "buy.tooSoonToRankUp", TimeUtils.convertTimeInMillisToReadableString(TimeUtils.calculateRemainingCooldown(rankPlayer.getTimeInMillisSinceLastRankUp()))));
        }

        economy.charge(player, nextRank.getCost());

        rankPlayer.setRank(nextRank.getId());
        rankPlayer.setTimeInMillisSinceLastRankUp(System.currentTimeMillis());

        permission.givePermission(player, nextRank.getPermission());

        new Thread(() -> {
            rankPlayerDAO.setRank(rankPlayer);
            rankPlayerDAO.setLastRankUp(rankPlayer);
        });

    }

}
