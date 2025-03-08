package xyz.luobo.checkPoints;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    private int toLockIndex = 0;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof CheckPointsGUI gui) {
            int slot = e.getSlot();

            if (gui.getSession().isChecking()) {
                e.setCancelled(true);
                return;
            }

            e.setCancelled(true);
            if (slot == 0) {
                e.getWhoClicked().closeInventory();
            } else if (slot == 1) {
                gui.getSession().useFocus(toLockIndex);
                toLockIndex ++;
            } else if (slot == 2) {
                gui.getSession().startCheck();
            }
        }
    }
}
