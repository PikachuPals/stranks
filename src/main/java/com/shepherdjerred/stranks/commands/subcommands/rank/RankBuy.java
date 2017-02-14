package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.NodeInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankNodeRegister;
import com.shepherdjerred.stranks.exceptions.RankException;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;

public class RankBuy extends AbstractRankNode {

    public RankBuy(RankNodeRegister register) {
        super(register, new NodeInfo(
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
        RankPlayer rankPlayer = rankPlayers.getPlayer(sender);
        if (!rankPlayer.hasBeenLoaded()) {
            sender.sendMessage("Your information hasn't finished loading yet, please wait a few seconds and try again");
            return;
        }
        try {
            rankPlayerController.rankUpPlayer(sender.getPlayer());
            Rank rank = ranks.getRank(rankPlayer.getRank());
            sender.sendMessage(parser.colorString(true, "buy.success", rank.getId(), rank.getDescription()));
        } catch (RankException e) {
            sender.sendMessage(e.getPlayerMessage());
        }
    }
}
