package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;

public class RankBuy extends AbstractRankCommand {

    public RankBuy(RankCommandRegister register) {
        super(register, new CommandInfo(
                "buy",
                "stRanks.buy",
                "Buy the next rank",
                "/rank buy",
                0,
                false
        ));
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] args) {

    }
}
