package xyz.luobo.checkPoints.Handle;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionBarHandle {
    private static final Map<UUID, String> activeMessages = new HashMap<>();

    // 更新指定玩家的ActionBar
    public static void updateActionBar(Player player, String message) {
        activeMessages.put(player.getUniqueId(), message);
        Component text = Component.text(message)
                .color(NamedTextColor.BLUE);
        player.sendActionBar(text);
    }

    // 清除玩家的ActionBar
    public static void clearActionBar(Player player) {
        activeMessages.remove(player.getUniqueId());
        player.sendActionBar(Component.empty());
    }

    // 获取当前消息
    public static String getCurrentMessage(UUID uuid) {
        return activeMessages.getOrDefault(uuid, "");
    }
}
