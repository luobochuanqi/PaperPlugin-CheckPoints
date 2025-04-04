package xyz.luobo.checkPoints;

import org.bukkit.entity.Player;

public class CheckPointsActionBarSession {
    private final Player player;
    private final CheckPoints plugin = CheckPoints.getPlugin(CheckPoints.class);
    private final boolean[] checkedDices;
    private final double successRate;
    private boolean isChecking = false;

    public CheckPointsActionBarSession(Player player, int diceCount, double rate) {
        this.player = player;
        this.checkedDices = new boolean[diceCount];
        this.successRate = rate;
    }
}
