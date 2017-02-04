package com.shepherdjerred.stranks.economy;

import com.shepherdjerred.riotbase.SpigotServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault implements Economy {

    private final SpigotServer spigotServer;
    private net.milkbowl.vault.economy.Economy vaultEconomy;

    public Vault(SpigotServer spigotServer) {
        this.spigotServer = spigotServer;
    }

    public void charge(Player player, Double amount) {
        vaultEconomy.withdrawPlayer(player, amount);
    }

    public boolean hasEnough(Player player, Double amount) {
        return vaultEconomy.has(player, amount);
    }

    public boolean setupEconomy() {
        if (spigotServer.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = spigotServer.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            return false;
        }
        vaultEconomy = rsp.getProvider();
        return vaultEconomy != null;
    }

}
