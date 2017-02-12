package com.shepherdjerred.stranks.commands.subcommands.rank.registers;

import com.shepherdjerred.riotbase.commands.NodeRegister;
import com.shepherdjerred.riotbase.messages.AbstractParser;
import com.shepherdjerred.stranks.controllers.RankPlayerController;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;

public class RankNodeRegister extends NodeRegister {

    private final Ranks ranks;
    private final RankPlayerController rankPlayerController;
    private final RankPlayers rankPlayers;

    public RankNodeRegister(AbstractParser parser, Ranks ranks, RankPlayerController rankPlayerController, RankPlayers rankPlayers) {
        super(parser);
        this.ranks = ranks;
        this.rankPlayerController = rankPlayerController;
        this.rankPlayers = rankPlayers;
    }

    public Ranks getRanks() {
        return ranks;
    }

    public RankPlayerController getRankPlayerController() {
        return rankPlayerController;
    }

    public RankPlayers getRankPlayers() {
        return rankPlayers;
    }
}
