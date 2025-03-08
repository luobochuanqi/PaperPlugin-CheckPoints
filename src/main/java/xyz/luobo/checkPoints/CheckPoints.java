package xyz.luobo.checkPoints;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CheckPoints extends JavaPlugin {

    public static JavaPlugin instance;
    public Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        if (Bukkit.getPluginCommand("checkpoints") != null) {
            Bukkit.getPluginCommand("checkpoints").setExecutor(new CheckPointsCmd());
        }

        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);

        logger.info("CheckPoints is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
