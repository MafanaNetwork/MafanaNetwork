package me.tahacheji.mafana.packets;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class TablistHandler {

    private final HashMap<Player, Tablist> tablistHashMap = new HashMap<>();
    private final JavaPlugin plugin;

    private long updatePeriod = 20;
    private BukkitTask task;

    public TablistHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        start();
    }

    public TablistHandler(JavaPlugin plugin, long updatePeriod) {
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
                tablistHashMap.forEach((player, tablist) -> tablist.getPacket().sendPacketOnce(player));
            }
        }.runTaskTimer(this.plugin, 0, updatePeriod); // every second
    }


    public void setPlayerTablist(Player player, TablistTemplate template) {
        tablistHashMap.put(player, new Tablist(template));
    }


}
