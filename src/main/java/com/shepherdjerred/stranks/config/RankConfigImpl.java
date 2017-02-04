package com.shepherdjerred.stranks.config;

import org.bukkit.configuration.file.FileConfiguration;

public class RankConfigImpl implements RankConfig {

    private final FileConfiguration config;

    public RankConfigImpl(FileConfiguration config) {
        this.config = config;
    }

}
