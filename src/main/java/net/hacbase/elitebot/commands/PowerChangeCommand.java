package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.ElitePower;
import net.hacbase.elitebot.discord.EliteUser;

public class PowerChangeCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public PowerChangeCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "pp";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            user.sendMessage("変更する勢力を指定してください");
            return;
        }
        String powerName = link(args, 0, args.length);
        ElitePower power = bot.getElitePowerProvider().getElitePowerByName(powerName);
        if (power == null) {
            user.sendMessage("その勢力は存在しません: " + powerName);
            return;
        }
        user.setElitePower(power);
        user.sendMessage("勢力を変更しました");
    }

    @Override
    public String getDescription() {
        return "勢力を変更します";
    }
}
