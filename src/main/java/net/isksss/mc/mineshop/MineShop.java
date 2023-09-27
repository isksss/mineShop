package net.isksss.mc.mineshop;

import net.isksss.mc.mineshop.command.Add;
import net.isksss.mc.mineshop.config.Config;
import net.isksss.mc.mineshop.database.DatabaseManager;
import net.isksss.mc.mineshop.events.ClickItem;
import net.isksss.mc.mineshop.events.CloseChest;
import net.isksss.mc.mineshop.events.RegisterChest;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MineShop extends JavaPlugin {

    public Plugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        // Load Config
        Config config = new Config(this.plugin);

        // データベース初期化
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.InitDatabase();

        //　イベント
        getServer().getPluginManager().registerEvents(new ClickItem(), this);
        getServer().getPluginManager().registerEvents(new CloseChest(), this);
        getServer().getPluginManager().registerEvents(new RegisterChest(), this);

        // コマンド
        Objects.requireNonNull(getCommand("shop")).setExecutor(new Add());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
