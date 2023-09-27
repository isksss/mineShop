package net.isksss.mc.mineshop.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class ClickItem implements Listener {

    @EventHandler
    public void ClickItem(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();
        Set<String> tags = p.getScoreboardTags();
        String openTag = "SHOP_CHEST_TAG";
        String setTag = "SHOP_SET_COMMAND";

        // 登録されているチェストか確認
        if(!tags.contains(openTag)){
            return;
        }

        // 管理権限を持ってるか確認。
        if(tags.contains(setTag)){
            //管理する処理
            p.sendMessage("");
            return;
        }else{
            //通常購入処理
            e.setCancelled(true);
            int slotNo = e.getSlot();
            if(slotNo == 0){
                return;
            }
            int exp = p.getLevel();

            ItemStack item = e.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            int price = Integer.parseInt(meta.getDisplayName());

            if(exp >= price){
                //購入
                int afterExp = exp - price;
                p.setLevel(afterExp);

                p.getInventory().addItem(new ItemStack(item.getType(),item.getAmount()));
            }else{
                //購入できない
                p.sendMessage("level tarinai.");
            }
        }



    }
}
