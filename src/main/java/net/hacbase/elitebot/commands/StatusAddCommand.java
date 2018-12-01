package net.hacbase.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteUser;
import net.hacbase.elitebot.save.DefaultEliteSimpleData;

import java.util.List;

public class StatusAddCommand extends AdminCommand{

    private final EliteBot bot;

    public StatusAddCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if(args.length < 1) {
            admin.sendMessage("追加する「状態」を指定してください");
            return;
        }
        List<Role> r = bot.getJDA().getRolesByName(args[0], true);
        if(r.size() == 0) {
            admin.sendMessage("その「状態」は存在しません: " + args[0]);
            return;
        }
        bot.getEliteStatusProvider().addEliteStatus(new DefaultEliteSimpleData(r.get(0).getName(), r.get(0).getId()));
        admin.sendMessage("「状態」を追加しました");
    }

    @Override
    public String getPrefix() {
        return "?stadd";
    }
}