package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.NodeInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankNodeRegister;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;

public class RankNext extends AbstractRankNode {

    public RankNext(RankNodeRegister register) {
        super(register, new NodeInfo(
                "next",
                "stRanks.next",
                "Shows information about the next rank you can obtain",
                "/rank list",
                0,
                true
        ));
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] strings) {
        RankPlayer rankPlayer = rankPlayers.getPlayer(sender);
        if (!rankPlayer.hasBeenLoaded()) {
            sender.sendMessage("Your information hasn't finished loading, please wait a few seconds and try again");
            return;
        }

        int rankId = rankPlayer.getRank() + 1;
        Rank rank = ranks.getRank(rankId);

        sender.sendMessage(parser.colorString(false, "info.header", String.valueOf(rank.getId())));
        sender.sendMessage(parser.colorString(false, "info.cost", String.valueOf(rank.getCost())));
        sender.sendMessage(parser.colorString(false, "info.description", rank.getDescription()));
    }

}
