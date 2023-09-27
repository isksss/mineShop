package net.isksss.mc.mineshop.events;

import net.isksss.mc.mineshop.database.ChestDao;
import net.isksss.mc.mineshop.model.Chest;
import net.isksss.mc.mineshop.model.Product;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class RegisterChest implements Listener {
    ChestDao cdao = new ChestDao();
    @EventHandler
    public void onRegisterChest(PlayerInteractEvent e){
        Block b = e.getClickedBlock(); //クリックしたブロック

        // クリックしたブロックがNullまたはチェストでなければ処理終了
        if (b == null || b.getType() != Material.CHEST){
            return;
        }

        Player p = e.getPlayer();
        Set<String> tags = p.getScoreboardTags();
        String addTag = "SHOP_ADD_COMMAND";
        String setTag = "SHOP_SET_COMMAND";
        String openTag = "SHOP_CHEST_TAG";


        Location loc = b.getLocation();
        Chest c = new Chest(0, loc);

        Chest cc = cdao.getChest(c);

        //チェストが登録されていない
        if(cc == null){
            // 追加処理
            if(tags.contains(addTag)){
                e.setCancelled(true);
                //ここにチェストを追加する処理
                cdao.registerChest(c);
                //登録されたか確認
                Chest kakunin = cdao.getChest(c);
                if (kakunin != null){
                    //登録されている
                    p.sendMessage("Done! chestId:"+kakunin.getChestId());
                    p.removeScoreboardTag(addTag);
                }else{
                    //登録されていない
                    p.sendMessage("Not.");
                }
            }
        }else {
            //チェストが登録されている。
//            e.setCancelled(true);
            //チェストを明けているタグ
            p.addScoreboardTag(openTag);
//            Inventory inv = makeInventory(cc);
//            p.openInventory(inv);
        }
    }

    private Inventory makeInventory(Chest c){
        String shopName = "shop";
        Inventory inv = Bukkit.createInventory(null,9*3,shopName);
        // chestIdからすで商品が登録してあるか確認する
        int chestId = c.getChestId();

        // CHEST_ID
        ItemStack chestIdItem = new ItemStack(Material.CHEST);
        ItemMeta meta = chestIdItem.getItemMeta();
        meta.displayName(Component.text("CHEST_ID:"+String.valueOf(c.getChestId())));
        chestIdItem.setItemMeta(meta);
        inv.setItem(0,chestIdItem);

        //チェストindex0-26までの商品を取得
//        for(int i=1;i<27;i++){
//            Product p = cdao.getProduct(chestId, i);
//            if (p != null){
//
////                inv.setItem(i,p.getItem());
//            }
//        }

        return inv;
    }


}
