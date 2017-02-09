package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;
import com.shepherdjerred.stranks.objects.Rank;
import org.bukkit.entity.Player;

public class RankNext extends AbstractRankCommand {

    public RankNext(RankCommandRegister register) {
        super(register, new CommandInfo(
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
        Player player = sender.getPlayer();
        int rankId = rankPlayers.getPlayer(player.getUniqueId()).getRank() + 1;
        Rank rank = ranks.getRank(rankId);

        sender.sendMessage(parser.colorString(false, "info.header", String.valueOf(rank.getId())));
        sender.sendMessage(parser.colorString(false, "info.cost", String.valueOf(rank.getCost())));
        sender.sendMessage(parser.colorString(false, "info.description", rank.getDescription()));
    }

}
