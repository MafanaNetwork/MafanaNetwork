package me.tahacheji.mafana.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class TabListAddPlayerPacket implements PacketSender {

    private final PacketContainer packet;
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();


    public TabListAddPlayerPacket(List<Player> playersToAdd) {
        this.packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
        List<PlayerInfoData> playerInfoDataList = new ArrayList<>();
        for (Player player : playersToAdd) {
            playerInfoDataList.add(
                    new PlayerInfoData(WrappedGameProfile.fromPlayer(player), 20, EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()), WrappedChatComponent.fromText(player.getDisplayName()))
            );
        }
        if (VersionUtil.isNewTabList()) {
            EnumSet<EnumWrappers.PlayerInfoAction> actions = EnumSet.of(
                    EnumWrappers.PlayerInfoAction.ADD_PLAYER,
                    EnumWrappers.PlayerInfoAction.UPDATE_LATENCY,
                    EnumWrappers.PlayerInfoAction.UPDATE_LISTED,
                    EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME,
                    EnumWrappers.PlayerInfoAction.UPDATE_GAME_MODE,
                    EnumWrappers.PlayerInfoAction.INITIALIZE_CHAT);
            this.packet.getPlayerInfoActions().write(0, actions);
            packet.getPlayerInfoDataLists().write(1, playerInfoDataList);
            return;
        }
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        packet.getPlayerInfoDataLists().write(0, playerInfoDataList);
    }

    public TabListAddPlayerPacket(Player player) {
        this(Collections.singletonList(player));
    }

    @Override
    public void sendPacketOnce(Player player) {
        protocolManager.sendServerPacket(player, this.packet);
    }
}
