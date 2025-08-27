package xyz.luobo.checkPoints.ActionBar;

import org.bukkit.entity.Player;
import xyz.luobo.checkPoints.CheckPoints;
import xyz.luobo.checkPoints.Handle.ActionBarHandle;

public class CheckPointsActionBarSession {
    private final Player player;
    private final CheckPoints plugin = CheckPoints.getPlugin(CheckPoints.class);
    private final boolean[] checkedDices;
    private final double successRate;
    private ActionBarHandle actionBarHandle;
    private boolean isChecking = false;

    public CheckPointsActionBarSession(Player player, int diceCount, double rate) {
        this.player = player;
        this.checkedDices = new boolean[diceCount];
        this.successRate = rate;
        this.actionBarHandle = new ActionBarHandle();
    }
}
