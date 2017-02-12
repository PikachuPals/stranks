package com.shepherdjerred.stranks.permissions;

import com.shepherdjerred.riotbase.SpigotServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPermission implements Permission {

    private final SpigotServer spigotServer;
    private net.milkbowl.vault.permission.Permission permission;

    public VaultPermission(SpigotServer spigotServer) {
        this.spigotServer = spigotServer;
    }

    public void givePermission(Player player, String permission) {
        this.permission.playerAdd(null, player, permission);
    }

    public boolean setupPermissions() {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> rsp = spigotServer.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        permission = rsp.getProvider();
        return permission != null;
    }

}
