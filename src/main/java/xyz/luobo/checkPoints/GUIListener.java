package xyz.luobo.checkPoints;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof CheckPointsGUI gui) {
            // 如果正在判定中，禁止操作
            if (gui.getSession().isChecking()) {
                return;
            }

            int slot = e.getSlot();

            switch (slot) {
                case 0 -> e.getWhoClicked().closeInventory(); // 退出按钮
                case 1 -> {
                    // 锁定专注点
                    gui.getSession().useFocus();
                }
                case 2 -> {
                    gui.getSession().startCheck();
                }
            }
        }

        e.setCancelled(true);
    }
}
