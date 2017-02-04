package com.shepherdjerred.stranks.listeners;

import com.shepherdjerred.riotbase.Server;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankPlayerListener implements Listener {

    private final Server server;
    private final RankPlayers rankPlayers;

    public RankPlayerListener(Server server, RankPlayers rankPlayers) {
        this.server = server;
        this.rankPlayers = rankPlayers;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Check if player exists in DB
        // If they do, load
        // Else, create
        // Then put into RankPlayers
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        rankPlayers.removePlayer(event.getPlayer().getUniqueId());
    }

}
