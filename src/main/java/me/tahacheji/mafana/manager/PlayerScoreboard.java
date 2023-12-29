package me.tahacheji.mafana.manager;

import me.tahacheji.mafana.scoreboard.FastBoard;
import org.bukkit.entity.Player;

public class PlayerScoreboard {

    private Player player;
    private FastBoard fastBoard;

    public PlayerScoreboard(Player player, FastBoard fastBoard) {
        this.player = player;
        this.fastBoard = fastBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public FastBoard getFastBoard() {
        return fastBoard;
    }
}
