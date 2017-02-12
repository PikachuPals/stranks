package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.NodeInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankNodeRegister;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.RankPlayer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

public class RankInfo extends AbstractRankNode {

    public RankInfo(RankNodeRegister register) {
        super(register, new NodeInfo(
                "info",
                "stRanks.info",
                "Shows detailed information about a rank",
                "/rank info [id]",
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

        int rankId;
        Player player = sender.getPlayer();

        if (args.length > 0) {
            if (StringUtils.isNumeric(args[0])) {
                rankId = Integer.valueOf(args[0]);
            } else {
                return;
            }
        } else {
            rankId = rankPlayer.getRank();
        }

        Rank rank = ranks.getRank(rankId);

        sender.sendMessage(parser.colorString(false, "info.header", String.valueOf(rank.getId())));
        sender.sendMessage(parser.colorString(false, "info.cost", String.valueOf(rank.getCost())));
        sender.sendMessage(parser.colorString(false, "info.description", rank.getDescription()));
    }

}
