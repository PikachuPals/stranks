package com.shepherdjerred.stranks;

import com.shepherdjerred.riotbase.RiotBase;
import com.shepherdjerred.riotbase.SpigotServer;
import com.shepherdjerred.stranks.commands.RankExecutor;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;
import com.shepherdjerred.stranks.config.RankConfig;
import com.shepherdjerred.stranks.config.RankConfigImpl;
import com.shepherdjerred.stranks.config.RankLoader;
import com.shepherdjerred.stranks.controllers.RankPlayerController;
import com.shepherdjerred.stranks.database.RankPlayerDAO;
import com.shepherdjerred.stranks.economy.VaultEconomy;
import com.shepherdjerred.stranks.listeners.RankPlayerListener;
import com.shepherdjerred.stranks.messages.Parser;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;
import com.shepherdjerred.stranks.permissions.VaultPermission;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.flywaydb.core.Flyway;

import java.io.File;
import java.util.ResourceBundle;

public class Main extends RiotBase {

    private Parser parser;
    private RankConfig rankConfig;
    private SpigotServer server;
    private RankPlayers rankPlayers;
    private Ranks ranks;
    private RankPlayerDAO rankPlayerDAO;

    private RankPlayerController rankPlayerController;

    private HikariDataSource hikariDataSource;
    private FluentJdbc fluentJdbc;

    private VaultEconomy economy;
    private VaultPermission permission;

    @Override
    public void onEnable() {
        createObjects();
        setupConfigs();
        setupDatabase();
        setupEconomy();
        setupPermissions();

        // TODO Load from plugin dir
        parser = new Parser(ResourceBundle.getBundle("messages"));

        rankPlayerDAO = new RankPlayerDAO(fluentJdbc, rankPlayers);
        rankPlayerController = new RankPlayerController(parser, ranks, rankPlayers, economy, rankPlayerDAO, permission);

        registerCommands();
        registerListeners();

        startMetrics();
    }

    private void createObjects() {
        rankPlayers = new RankPlayers();
        ranks = new Ranks();
        server = new SpigotServer();
    }

    private void setupConfigs() {
        copyFile(getResource("config.yml"), getDataFolder().getAbsolutePath() + "/config.yml");
        copyFile(getResource("messages.properties"), getDataFolder().getAbsolutePath() + "/messages.properties");
        copyFile(getResource("ranks.json"), getDataFolder().getAbsolutePath() + "/ranks.json");


        rankConfig = new RankConfigImpl(getConfig());
        new RankLoader(ranks).loadRanks(new File(getDataFolder().getAbsolutePath() + "/ranks.json"));
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

    private void setupEconomy() {
        economy = new VaultEconomy(server);
        if (!economy.setupEconomy()) {
            getLogger().info("Error enabling economy support. Disabling stRanks.");
            getPluginLoader().disablePlugin(this);
        }
    }

    private void setupPermissions() {
        permission = new VaultPermission(server);
        permission.setupPermissions();
    }

    private void registerCommands() {
        RankCommandRegister rcr = new RankCommandRegister(parser, ranks, rankPlayerController, rankPlayers);
        rcr.addCommand(new RankExecutor(rcr));
        rcr.register(this);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new RankPlayerListener(server, rankPlayers, rankPlayerDAO), this);
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
