package com.shepherdjerred.stranks.listeners;

import com.shepherdjerred.riotbase.Server;
import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.objects.RankPlayer;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class RankPlayerListener implements Listener {

    private final Server server;
    private final RankPlayers rankPlayers;
    private final RankPlayerDAO rankPlayerDAO;

    public RankPlayerListener(Server server, RankPlayers rankPlayers, RankPlayerDAO rankPlayerDAO) {
        this.server = server;
        this.rankPlayers = rankPlayers;
        this.rankPlayerDAO = rankPlayerDAO;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID playerUuid = event.getPlayer().getUniqueId();

        // Create an unloaded RankPlayer
        RankPlayer unloadedRankPlayer = new RankPlayer(playerUuid);
        rankPlayers.addPlayer(unloadedRankPlayer);

        // Load from database
        new Thread(() -> {
            RankPlayer rankPlayer = rankPlayerDAO.load(playerUuid);
            if (rankPlayer == null) {
                rankPlayer = new RankPlayer(playerUuid, 1, 0);
                rankPlayerDAO.insert(rankPlayer);
                rankPlayers.addPlayer(rankPlayer);
            }
        });

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        rankPlayers.removePlayer(event.getPlayer().getUniqueId());
    }

}
