package com.shepherdjerred.stranks.commands;

import com.shepherdjerred.riotbase.commands.AbstractCommand;
import com.shepherdjerred.riotbase.commands.CommandInfo;
import com.shepherdjerred.riotbase.commands.CommandRegister;
import com.shepherdjerred.riotbase.commands.SpigotCommandSource;

public class RankExecutor extends AbstractCommand {

    public RankExecutor(CommandRegister register, CommandInfo commandInfo) {
        super(register, new CommandInfo(
                "rank",
                "stRanks.rank",
                "Main command for stRanks",
                "/stranks <info, list, buy>",
                1,
                false
        ));
    }

    @Override
    public void execute(SpigotCommandSource commandSource, String[] args) {
        getChild("help").execute(commandSource, new String[]{});
    }

}
