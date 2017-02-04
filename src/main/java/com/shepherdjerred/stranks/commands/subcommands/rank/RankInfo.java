package com.shepherdjerred.stranks.commands.subcommands.rank;

import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;
import com.shepherdjerred.stranks.commands.subcommands.rank.registers.RankCommandRegister;
import com.shepherdjerred.stranks.objects.Rank;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

public class RankInfo extends AbstractRankCommand {

    public RankInfo(RankCommandRegister register) {
        super(register, new CommandInfo(
                "info",
                "stRanks.info",
                "Shows detailed information about your current rank",
                "/rank info [id]",
                0,
                false
        ));
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] args) {
        int rankId;
        Player player = sender.getPlayer();

        if (args.length > 0) {
            if (StringUtils.isNumeric(args[0])) {
                rankId = Integer.valueOf(args[0]);
            } else {
                // Invalid arg
                return;
            }
        } else {
            rankId = rankPlayers.getPlayer(player.getUniqueId()).getRank();
        }

        Rank rank = ranks.getRank(rankId);

        sender.sendMessage(String.valueOf(rank.getId()));
        sender.sendMessage(rank.getDescription());
        sender.sendMessage(String.valueOf(rank.getCost()));

    }

}
