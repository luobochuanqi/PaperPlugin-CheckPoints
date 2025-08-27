package xyz.luobo.checkPoints;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.luobo.checkPoints.GUI.GUIListener;
import xyz.luobo.checkPoints.Handle.PluginCmdHandle;

import java.util.Objects;
import java.util.logging.Logger;

public final class CheckPoints extends JavaPlugin {

    public static JavaPlugin instance;
    public Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        if (Bukkit.getPluginCommand("checkpoints") != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand("checkpoints")).setExecutor(new PluginCmdHandle());
        }

        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);

        logger.info("CheckPoints is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
