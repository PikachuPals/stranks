package com.shepherdjerred.stranks.commands;

import com.shepherdjerred.riotbase.commands.AbstractCommand;
import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.RankBuy;
import com.shepherdjerred.stranks.commands.subcommands.rank.RankInfo;
import com.shepherdjerred.stranks.commands.subcommands.rank.RankList;
import com.shepherdjerred.stranks.commands.subcommands.rank.RankNext;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;

public class RankExecutor extends AbstractCommand {

    public RankExecutor(RankCommandRegister register) {
        super(register, new CommandInfo(
                "rank",
                "stRanks.rank",
                "Main command for stRanks",
                "/stranks <buy, info, list, next>",
                1,
                false
        ));
        addChildren(
                new RankInfo(register),
                new RankList(register),
                new RankBuy(register),
                new RankNext(register)
        );
    }

    @Override
    public void execute(SpigotCommandSource commandSource, String[] args) {
        getChild("help").execute(commandSource, new String[]{});
    }

}
