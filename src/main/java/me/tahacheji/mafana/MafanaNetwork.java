package me.tahacheji.mafana;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;

import me.tahacheji.mafana.itemData.GameItem;
import me.tahacheji.mafana.listener.*;
import me.tahacheji.mafana.manager.PlayerScoreboard;
import me.tahacheji.mafana.packets.TabListHandler;
import me.tahacheji.mafana.packets.TabListTemplate;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MafanaNetwork extends JavaPlugin {

    private TabListHandler tablistHandler;
    private static MafanaNetwork instance;
    private List<PlayerScoreboard> playerScoreboards = new ArrayList<>();
    private List<TabListTemplate> tabListTemplates = new ArrayList<>();
    private List<GameItem> gameItems = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        tablistHandler = new TabListHandler(this);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        if (VersionUtil.isNewTabList()) {
            manager.addPacketListener(new PlayerRemoveListener( ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO_REMOVE));
        }
        manager.addPacketListener(new PlayerInfoListener(ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO));
        manager.addPacketListener(new NamedEntityListener(ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_ENTITY_SPAWN));

        getServer().getPluginManager().registerEvents(new GameItemListener(), this);
    }

    public List<GameItem> getGameItems() {
        return gameItems;
    }

    public List<TabListTemplate> getTabListTemplates() {
        return tabListTemplates;
    }

    public List<PlayerScoreboard> getPlayerScoreboards() {
        return playerScoreboards;
    }

    public static MafanaNetwork getInstance() {
        return instance;
    }

    public TabListHandler getTabListHandler() {
        return tablistHandler;
    }

}
