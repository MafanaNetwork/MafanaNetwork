package me.tahacheji.mafana.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import me.tahacheji.mafana.MafanaNetwork;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerInfoListener extends PacketAdapter {


    public PlayerInfoListener(ListenerPriority listenerPriority, PacketType... types) {
        super(MafanaNetwork.getInstance(), listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        Player destinationPlayer = event.getPlayer();
        PacketContainer packetContainer = event.getPacket();
        if (VersionUtil.isNewTabList()) {
            Set<EnumWrappers.PlayerInfoAction> action = packetContainer.getPlayerInfoActions().read(0);
            if (action.contains(EnumWrappers.PlayerInfoAction.INITIALIZE_CHAT) || action.contains(EnumWrappers.PlayerInfoAction.UPDATE_LISTED)) {
                return;
            }

        } else {
            if (packetContainer.getPlayerInfoAction().read(0) == EnumWrappers.PlayerInfoAction.REMOVE_PLAYER) {
                return;
            }
        }
        List<PlayerInfoData> playerInfoDataList = packetContainer.getPlayerInfoDataLists().read(VersionUtil.isNewTabList() ? 1 : 0); // sent data;
        List<PlayerInfoData> newPlayerInfoDataList = new ArrayList<>(); // new data
        for (PlayerInfoData data : playerInfoDataList) {
            Player dataPlayer = Bukkit.getPlayer(data.getProfile().getName());
            if (dataPlayer != null) { // real player (online one)
                if (dataPlayer.getWorld().equals(destinationPlayer.getWorld())) { // same world
                    PlayerInfoData newData = new PlayerInfoData(data.getProfile(), 20, data.getGameMode(), data.getDisplayName()); // just edit latency
                    newPlayerInfoDataList.add(newData);
                }
            } else {
                newPlayerInfoDataList.add(data); // for fake players is not needed to modify its ping
            }
        }
        packetContainer.getPlayerInfoDataLists().write(VersionUtil.isNewTabList() ? 1 : 0, newPlayerInfoDataList);

    }

}