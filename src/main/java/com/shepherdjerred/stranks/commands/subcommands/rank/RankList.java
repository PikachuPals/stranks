package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.NodeInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankNodeRegister;

public class RankList extends AbstractRankNode {

    public RankList(RankNodeRegister register) {
        super(register, new NodeInfo(
                "list",
                "stRanks.list",
                "Shows a list of all available ranks",
                "/rank list",
                0,
                true
        ));
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] strings) {
        sender.sendMessage(parser.colorString(false, "list.header"));
        ranks.getRanksAsList().forEach(rank -> sender.sendMessage(parser.colorString(false, "list.item", rank.getId(), rank.getDescription())));
    }

}
