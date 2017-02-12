package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandNode;
import com.shepherdjerred.riotbase.commands.NodeInfo;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankNodeRegister;
import com.shepherdjerred.stranks.controllers.RankPlayerController;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;

public abstract class AbstractRankNode extends CommandNode {

    protected final Ranks ranks;
    protected final RankPlayerController rankPlayerController;
    protected final RankPlayers rankPlayers;

    public AbstractRankNode(RankNodeRegister register, NodeInfo nodeInfo) {
        super(register, nodeInfo);
        ranks = register.getRanks();
        rankPlayerController = register.getRankPlayerController();
        rankPlayers = register.getRankPlayers();
    }

}
