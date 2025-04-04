package xyz.luobo.checkPoints;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.IntStream;

public class CheckPointsGUISession {
    private final Player player;
    private final CheckPoints plugin = CheckPoints.getPlugin(CheckPoints.class);
    private final CheckPointsGUI gui;
    private final boolean[] checkedDices;
    private final boolean[] focuses;
    private final double successRate;
    private boolean isChecking = false;
    private int toLockIndex = 0;

    public CheckPointsGUISession(Player player, CheckPointsGUI gui, int diceCount, int focusCount, double rate) {
        this.player = player;
        this.checkedDices = new boolean[diceCount];
        this.focuses = new boolean[focusCount];
        this.successRate = rate;
        this.gui = gui;
    }

    /**
     * 使用 Focus 锁定骰子为成功
     * @param index
     */
    public void lockDice(int index) {
        if (index < checkedDices.length) {
            checkedDices[index] = true;
            updateDice(index, true);
        }
    }

    /**
     * 使用 Focus 锁定点位
     * WTF?!
     * @param index
     */
    private void setFocused(int index) {
        if (index < focuses.length) {
            focuses[index] = true;
            updateFocus(index);
        }
    }

    public void startCheck() {
        isChecking = true;
        new BukkitRunnable() {
            int current = IntStream.range(0, checkedDices.length)
                    .filter(i -> !checkedDices[i])
                    .findFirst().orElse(0);

            @Override
            public void run() {
                if (current >= checkedDices.length) {
                    sendResult();
                    cancel();
                    return;
                }

                boolean success = checkedDices[current] || Math.random() < successRate;
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                updateDice(current, success);
                current++;
            }
        }.runTaskTimer(plugin, 0L, 5L); // 每0.25秒判定一个骰子
    }

    private void updateDice(int current, boolean success) {
        player.sendMessage("§6[判定] 第" + (current + 1) + "个骰子: " + (success ? "§a成功" : "§c失败"));
        gui.updateDiceDisplay(current, success ? PointsType.SUCCESS : PointsType.FAILURE);
    }

    private void updateFocus(int current) {
        gui.updateFocusDisplay(current, FocusType.FOCUSED);
    }

    private void sendResult() {
        long success = IntStream.range(0, checkedDices.length)
                .filter(i -> checkedDices[i] || Math.random() < successRate)
                .count();

        player.sendMessage("§6[判定结果] 成功数: " + success + "/" + checkedDices.length);
    }

    public boolean isChecking() {
        return isChecking;
    }

    public boolean useFocus() {

        // 仅在非判定过程时允许使用 Focus
        if (!isChecking){
            toLockIndex ++;
//            lockDice(index);
            setFocused(toLockIndex);
            return true;
        }
        return false;
    }
}
