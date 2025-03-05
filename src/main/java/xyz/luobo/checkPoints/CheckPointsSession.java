package xyz.luobo.checkPoints;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.IntStream;

public class CheckPointsSession {
    private final Player player;
    private final CheckPoints plugin = CheckPoints.getPlugin(CheckPoints.class);
    private final CheckPointsGUI gui;
    private final boolean[] lockedDices;
    private final double successRate;
    private boolean isChecking = false;

    public CheckPointsSession(Player player, CheckPointsGUI gui, int diceCount, double rate) {
        this.player = player;
        this.lockedDices = new boolean[diceCount];
        this.successRate = rate;
        this.gui = gui;
    }

    public void lockDice(int index) {
        if (!isChecking && index < lockedDices.length) {
            lockedDices[index] = true;
            updateDice(index, true);
        }
    }

    public void startCheck() {
        isChecking = true;
        new BukkitRunnable() {
            int current = 0;

            @Override
            public void run() {
                if (current >= lockedDices.length) {
                    sendResult();
                    cancel();
                    return;
                }

                boolean success = lockedDices[current] || Math.random() < successRate;
                updateDice(current, success);
                current++;
            }
        }.runTaskTimer(plugin, 0L, 5L); // 每0.25秒判定一个骰子
    }

    private void updateDice(int current, boolean success) {
        player.sendMessage("§6[判定] 第" + (current + 1) + "个骰子: " + (success ? "§a成功" : "§c失败"));
        gui.updateDiceDisplay(current, success ? PointsType.SUCCESS : PointsType.FAILURE);
    }

    private void sendResult() {
        long success = IntStream.range(0, lockedDices.length)
                .filter(i -> lockedDices[i] || Math.random() < successRate)
                .count();

        player.sendMessage("§6[判定结果] 成功数: " + success + "/" + lockedDices.length);
    }
}
