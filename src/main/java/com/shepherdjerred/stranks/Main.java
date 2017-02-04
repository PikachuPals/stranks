package com.shepherdjerred.stranks;

import com.shepherdjerred.riotbase.RiotBase;
import com.shepherdjerred.riotbase.Server;
import com.shepherdjerred.riotbase.SpigotServer;
import com.shepherdjerred.stranks.config.RankConfig;
import com.shepherdjerred.stranks.config.RankConfigImpl;
import com.shepherdjerred.stranks.controllers.RankPlayerController;
import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.listeners.RankPlayerListener;
import com.shepherdjerred.stranks.messages.Parser;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.flywaydb.core.Flyway;

import java.util.ResourceBundle;

public class Main extends RiotBase {

    private Parser parser;
    private RankConfig rankConfig;
    private Server server;
    private RankPlayers rankPlayers;
    private RankPlayerDAO rankPlayerDAO;

    private RankPlayerController rankPlayerController;

    private HikariDataSource hikariDataSource;
    private FluentJdbc fluentJdbc;

    @Override
    public void onEnable() {
        createObjects();
        setupConfigs();
        setupDatabase();

        // TODO Load from plugin dir
        parser = new Parser(ResourceBundle.getBundle("messages"));

        registerCommands();
        registerListeners();

        startMetrics();
    }

    private void createObjects() {
        rankPlayers = new RankPlayers();
        server = new SpigotServer();
        rankPlayerDAO = new RankPlayerDAO(fluentJdbc, rankPlayers);
    }

    private void setupConfigs() {
        copyFile(getResource("config.yml"), getDataFolder().getAbsolutePath() + "/config.yml");
        copyFile(getResource("messages.properties"), getDataFolder().getAbsolutePath() + "/messages.properties");

        rankConfig = new RankConfigImpl(getConfig());
    }

    private void setupDatabase() {
        copyFile(getResource("hikari.properties"), getDataFolder().getAbsolutePath() + "/hikari.properties");
        copyFile(getResource("db/migration/V1__Initial.sql"), getDataFolder().getAbsolutePath() + "/db/migration/V1__Initial.sql");

        HikariConfig hikariConfig = new HikariConfig(getDataFolder().getAbsolutePath() + "/hikari.properties");
        hikariDataSource = new HikariDataSource(hikariConfig);

        fluentJdbc = new FluentJdbcBuilder().connectionProvider(hikariDataSource).build();

        Flyway flyway = new Flyway();
        flyway.setLocations("filesystem:" + getDataFolder().getAbsolutePath() + "/db/migration/");
        flyway.setDataSource(hikariDataSource);
        flyway.migrate();
    }

    private void registerCommands() {

    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new RankPlayerListener(server, rankPlayers), this);
    }

    /**
     * Loop through online players, loading their data. This should only be called when reloading the plugin
     */
    private void checkOnlinePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            // TODO Load the players from the database
        }
    }

    public RankPlayerController getRankPlayerController() {
        return rankPlayerController;
    }
}
