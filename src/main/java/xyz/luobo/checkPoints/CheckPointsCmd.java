package xyz.luobo.checkPoints;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckPointsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("checkpoints")) {
            if (args.length == 0) {
                sender.sendMessage("CheckPoints v1.0");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage("Reloading CheckPoints...");
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            int diceCount = Math.min(Integer.parseInt(args[1]), 27); // 最大27个
            double successRate = Double.parseDouble(args[2]);
            int focusCount = Math.min(Integer.parseInt(args[3]), 7); // 最大7个

            new CheckPointsGUI(player, diceCount, successRate, focusCount).open();
            return true;
        }
        return false;
    }
}
