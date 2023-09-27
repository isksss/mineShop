package net.isksss.mc.mineshop.events;

import net.isksss.mc.mineshop.database.ChestDao;
import net.isksss.mc.mineshop.model.Product;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloseChest implements Listener {

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        Set<String> tags = p.getScoreboardTags();
        String openTag = "SHOP_CHEST_TAG";
        String setTag = "SHOP_SET_COMMAND";

//        if(tags.contains(openTag) && tags.contains(setTag)){
//            p.sendMessage(Component.text(""));
//            //インベントリを保存
//            Inventory inv = e.getInventory();
//            String chestIdStr = extractID(inv.getItem(0).getItemMeta().displayName().toString());
//            int chest_id = Integer.parseInt(chestIdStr);
//
//            ChestDao c = new ChestDao();
//
//            for(int i=1;i<27;i++){
//                ItemStack item = inv.getItem(i);
//                int amount = item.getAmount();
//                ItemMeta meta = item.getItemMeta();
//                TextComponent price_compo = (TextComponent)meta.displayName();
//                String price_str = price_compo.content();
//                int price = Integer.parseInt(price_str);
//                Product product = new Product(chest_id,i,item.getType().toString(),amount,price);
//
//                c.AddProduct(product);
//                p.sendMessage("ADD_ITEM: NAME->"+item.getType().toString()+", price->"+price);
//            }
//        }

        // タグを削除
        if(tags.contains(openTag)){
            p.removeScoreboardTag(openTag);
            p.sendMessage("Close shop.");
        }

        if(tags.contains(setTag)){
            p.removeScoreboardTag(setTag);
            p.sendMessage("Disable set mode.");
        }

    }

    public static String extractID(String text) {
        String pattern = "CHEST_ID:(\\d+)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; // マッチするIDが見つからない場合
        }
    }
}
