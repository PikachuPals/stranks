package com.shepherdjerred.stranks.economy;

import org.bukkit.entity.Player;

public interface Economy {

    public void charge(Player player, Double amount);

    public boolean hasEnough(Player player, Double amount);

}
