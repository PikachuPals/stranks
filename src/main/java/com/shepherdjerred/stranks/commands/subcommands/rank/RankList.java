package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;

public class RankList extends AbstractRankCommand {

    public RankList(RankCommandRegister register) {
        super(register, new CommandInfo(
                "list",
                "stRanks.list",
                "Shows a list of all ranks on the server",
                "/rank list",
                0,
                true
        ));
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] strings) {
        ranks.getRanksAsList().forEach(rank -> sender.sendMessage(rank.getId() + ": " + rank.getDescription()));
    }

}
