package xyz.luobo.checkPoints;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CheckPointsGUI implements InventoryHolder {

    private final Player player;
    private final Inventory inv;
    private final CheckPointsSession session;

    public CheckPointsGUI(Player player, int diceCount, double rate, int focusCount) {
        this.inv = Bukkit.createInventory(this, 45, "判定面板");
        this.session = new CheckPointsSession(player, this, diceCount, focusCount, rate);
        this.player = player;

        // 控制栏
        inv.setItem(0, createIcon(Material.BARRIER, "§c退出"));
        inv.setItem(1, createIcon(Material.EMERALD, "§a使用专注点"));
        inv.setItem(2, createIcon(Material.LIME_DYE, "§e开始判定"));

        // 初始化骰子区域
        for (int i = 0; i < diceCount; i++) {
            updateDiceDisplay(i, PointsType.WAITING);
        }

        // 初始化判定点区域
        for (int i = 0; i < focusCount; i++) {
            updateFocusDisplay(i, FocusType.WAITING);
        }
    }

    public void updateFocusDisplay(int index, FocusType focusType) {
        int slot = index + 36;
        Material material;
        String displayName = switch (focusType) {
            case FOCUSED -> {
                material = Material.ENDER_PEARL;
                yield "§a已加点";
            }
            default -> {
                material = Material.ENDER_EYE;
                yield "§f待加点";
            }
        };

        inv.setItem(slot, createIcon(material, displayName));
    }

    public void updateDiceDisplay(int index, PointsType pointsType) {
        int slot = index + 9; // 从第1行开始
        Material material;
        String displayName = switch (pointsType) {
            case SUCCESS -> {
                material = Material.LIME_WOOL;
                yield "§a成功";
            }
            case FAILURE -> {
                material = Material.RED_WOOL;
                yield "§c失败";
            }
            default -> {
                material = Material.WHITE_WOOL;
                yield "§f待判定";
            }
        };

        inv.setItem(slot, createIcon(material, displayName));
    }

    public void open() {
        player.openInventory(inv);
    }

    private ItemStack createIcon(Material material, String s) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(s);
        item.setItemMeta(meta);
        return item;
    }

    public CheckPointsSession getSession() {
        return session;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }
}
