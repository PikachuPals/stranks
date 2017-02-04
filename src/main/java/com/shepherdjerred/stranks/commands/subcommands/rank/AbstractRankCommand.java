package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.AbstractCommand;
import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;
import com.shepherdjerred.stranks.controllers.RankPlayerController;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import com.shepherdjerred.stranks.objects.trackers.Ranks;

public abstract class AbstractRankCommand extends AbstractCommand {

    protected final Ranks ranks;
    protected final RankPlayerController rankPlayerController;
    protected final RankPlayers rankPlayers;

    public AbstractRankCommand(RankCommandRegister register, CommandInfo commandInfo) {
        super(register, commandInfo);
        ranks = register.getRanks();
        rankPlayerController = register.getRankPlayerController();
        rankPlayers = register.getRankPlayers();
    }

}
