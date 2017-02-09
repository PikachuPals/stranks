package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;
import com.shepherdjerred.stranks.exceptions.RankException;
import com.shepherdjerred.stranks.objects.RankPlayer;

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
        RankPlayer rankPlayer = rankPlayers.getPlayer(sender);
        if (!rankPlayer.hasBeenLoaded()) {
            sender.sendMessage("Your information hasn't finished loading, please wait a few seconds and try again");
            return;
        }
        try {
            rankPlayerController.rankUpPlayer(sender.getPlayer());
        } catch (RankException e) {
            e.printStackTrace();
            sender.sendMessage(e.getPlayerMessage());
        }
    }
}
