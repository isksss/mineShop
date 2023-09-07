package net.isksss.mc.mineshop;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineShop extends JavaPlugin {

    public Plugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
