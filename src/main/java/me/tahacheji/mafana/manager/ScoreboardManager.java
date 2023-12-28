package me.tahacheji.mafana.manager;

import fr.mrmicky.fastboard.FastBoard;
import me.tahacheji.mafana.MafanaNetwork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ScoreboardManager {

    public String name;
    public FastBoard board;

    public ScoreboardManager(String name) {
        this.name = name;
    }

    public void updateScoreboard() {
        Bukkit.getScheduler().runTaskTimer(MafanaNetwork.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(PlayerScoreboard playerScoreboard : MafanaNetwork.getInstance().getPlayerScoreboards()) {
                    updateBoard(playerScoreboard.getFastBoard(), playerScoreboard.getPlayer());
                    System.out.println(3);
                }
            }
        }, 0, 25);
    }

    public void onJoin(Player player) {
        FastBoard board = new FastBoard(player);
        board.updateTitle(name);
        MafanaNetwork.getInstance().getPlayerScoreboards().add(new PlayerScoreboard(player, board));
        System.out.println(2);
    }

    public void onLeave(Player player) {
        List<PlayerScoreboard> playerScoreboardList = new ArrayList<>();
        for(PlayerScoreboard playerScoreboard : MafanaNetwork.getInstance().getPlayerScoreboards()) {
            if(playerScoreboard.getPlayer().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                playerScoreboardList.add(playerScoreboard);
            }
        }
        MafanaNetwork.getInstance().getPlayerScoreboards().removeAll(playerScoreboardList);
    }

    public abstract void updateBoard(FastBoard board, Player player);

    public FastBoard getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }
}
