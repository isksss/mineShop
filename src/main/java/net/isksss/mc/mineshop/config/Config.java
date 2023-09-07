package net.isksss.mc.mineshop.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private static Config configInstance;
    private Plugin plugin;

    private FileConfiguration config;
    public Config(Plugin plugin){
        this.plugin = plugin;
        this.configInstance = this;

        // コンフィグの初期化
        plugin.saveDefaultConfig();

        LoadConfig();
    }


    public void LoadConfig(){
        this.config = this.plugin.getConfig();
    }

    public void ReloadConfig(){
        this.plugin.reloadConfig();
        LoadConfig();
    }

    public String getStringConfig(String path, String def){
        return this.config.getString(path, def);
    }


    public static Config getConfig(){
        if(configInstance != null){
            return configInstance;
        }
        return null;
    }
}
