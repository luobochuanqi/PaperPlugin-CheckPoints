package xyz.luobo.checkPoints;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CheckPoints extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = this.getLogger();
        logger.info("CheckPoints is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
