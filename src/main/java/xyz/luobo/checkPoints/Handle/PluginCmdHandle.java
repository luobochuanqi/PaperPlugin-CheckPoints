package xyz.luobo.checkPoints.Handle;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.luobo.checkPoints.GUI.CheckPointsGUI;

public class PluginCmdHandle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("checkpoints")) {
            if (args.length == 0) {
                sender.sendMessage("CheckPoints v1.0");
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "help" -> {
                    sender.sendMessage("CheckPoints v1.0");
                    sender.sendMessage("/checkpoints gui <player> <diceCount> <successRate> <focusCount>");
                    sender.sendMessage("/checkpoints reload");
                    sender.sendMessage("/checkpoints version");
                    return true;
                }
                case "version" -> {
                    sender.sendMessage("CheckPoints v1.0");
                    return true;
                }
                case "reload" -> {
                    sender.sendMessage("Reloading CheckPoints...");
                    return true;
                }
                case "gui" -> {
                    if (args.length < 4) {
                        sender.sendMessage("Usage: /checkpoints gui <player> <diceCount> <successRate> <focusCount>");
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    int diceCount = Math.min(Integer.parseInt(args[2]), 27); // 最大27个
                    double successRate = Double.parseDouble(args[3]);
                    int focusCount = Math.min(Integer.parseInt(args[4]), 7); // 最大7个

                    if (player == null) {
                        sender.sendMessage("Player not found.");
                    }

                    new CheckPointsGUI(player, diceCount, successRate, focusCount).open();
                    return true;
                }
                case "actionbar" -> {
                    if (args.length < 2) {
                        sender.sendMessage("Usage: /checkpoints actionbar <player> <diceCount> <successRate>");
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null) {
                        String message = ActionBarHandle.getCurrentMessage(player.getUniqueId());
                    } else {
                        sender.sendMessage("Player not found.");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
