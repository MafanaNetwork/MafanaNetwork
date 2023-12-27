package me.tahacheji.mafana.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TabListRemovePlayerPacket implements PacketSender{

    private final PacketContainer packet;
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();


    public TabListRemovePlayerPacket(List<Player> playersToRemove) {
        if(VersionUtil.isNewTabList()){
            this.packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO_REMOVE);
            List<UUID> uuidList = playersToRemove.stream().map(Player::getUniqueId).collect(Collectors.toList());
            packet.getLists(Converters.passthrough(UUID.class)).write(0,uuidList);
            return;
        }
        this.packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        List<PlayerInfoData> playerInfoDataList = new ArrayList<>();
        for(Player player : playersToRemove) {
            playerInfoDataList.add( // latency isnt important!
                    new PlayerInfoData(WrappedGameProfile.fromPlayer(player), 20, EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()), WrappedChatComponent.fromText(player.getDisplayName()))
            );
        }
        packet.getPlayerInfoDataLists().write(0,playerInfoDataList);
    }

    public TabListRemovePlayerPacket(Player player) {
        this(Collections.singletonList(player));
    }

    @Override
    public void sendPacketOnce(Player player) {
        protocolManager.sendServerPacket(player, this.packet);
    }
}
