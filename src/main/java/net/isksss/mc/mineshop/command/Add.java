package net.isksss.mc.mineshop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Add implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)){
            sender.sendMessage("This command is only used player.");
            return false;
        }

        if (!(command.getName().equalsIgnoreCase("shop"))){
            return false;
        }

        if((args.length == 0)){
            p.sendMessage("Args is not enough.");
            return false;
        }

        if(!p.isOp()){
            p.sendMessage("not op");
            return false;
        }

        String subCmd = args[0];
        Set<String> UserTags = p.getScoreboardTags();

        //add mode
        String addTag = "SHOP_ADD_COMMAND";
        String setTag = "SHOP_SET_COMMAND";
        if(subCmd.equalsIgnoreCase("add")){
            // チェストを登録する
            if(UserTags.contains(addTag)){
                p.removeScoreboardTag(addTag);
                p.sendMessage("Disable shop add mode.");
                return true;
            }
            p.addScoreboardTag(addTag);
        } else if (subCmd.equalsIgnoreCase("set") ){
            // チェストの中身を設定する
            if(UserTags.contains(setTag)){
                p.removeScoreboardTag(setTag);
                p.sendMessage("Disable shop set mode.");
                return true;
            }
            p.addScoreboardTag(setTag);
        }


        
        return false;
    }
}
