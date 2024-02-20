package me.tahacheji.mafana.packets;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class TabListHandler {

    private final HashMap<Player, TabList> tablistHashMap = new HashMap<>();
    private final JavaPlugin plugin;

    private long updatePeriod = 20;
    private BukkitTask task;

    public TabListHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        start();
    }

    public TabListHandler(JavaPlugin plugin, long updatePeriod) {
        this(plugin);
        this.updatePeriod = updatePeriod;
    }

    public void stop() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
    }

    public void start() {
        if(this.task != null) {
            return;
        }
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                tablistHashMap.forEach((player, tabList) -> tabList.getPacket().sendPacketOnce(player));
            }
        }.runTaskTimerAsynchronously(this.plugin, 0, updatePeriod);
    }


    public void setPlayerTabList(Player player, TabListTemplate template) {
        tablistHashMap.put(player, new TabList(template));
    }


}
