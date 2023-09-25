package net.isksss.mc.mineshop.events;

import net.isksss.mc.mineshop.database.ChestDao;
import net.isksss.mc.mineshop.model.Chest;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class RegisterChest implements Listener {

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


        Location loc = b.getLocation();
        Chest c = new Chest(0, loc);

        ChestDao cdao = new ChestDao();

        Chest cc = cdao.getChest(c);

        //チェストが登録されていない
        if(cc == null){
            // 追加処理
            if(tags.contains(addTag)){
                //ここにチェストを追加する処理
                return;
            }
        }else {
            //チェストが登録されている。
            e.setCancelled(true);
            // チェストの中身を変更する権限
            if(tags.contains(setTag)){
                //チェストのインベントリを操作
            }else{
                //通常の売買処理
            }
        }
    }

}
