package me.tahacheji.mafana.packets.fakePlayer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import me.tahacheji.mafana.loaders.ConfigLoader;
import me.tahacheji.mafana.util.VersionUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class TablistAddFakePlayerPacket extends FakePlayerPacket {

    protected TablistAddFakePlayerPacket(UUID uuid, String playerName, String displayText) {
        super(uuid, playerName, displayText);
        WrappedGameProfile gameProfile = new WrappedGameProfile(uuid, playerName);
        PacketContainer packet = this.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        setPacket(packet);
        PlayerInfoData data = new PlayerInfoData(FakePlayerPacket.changeGameProfileSkin(gameProfile), ConfigLoader.getDefaultLatency().getLatency() + 1, EnumWrappers.NativeGameMode.CREATIVE, WrappedChatComponent.fromText(displayText));
        List<PlayerInfoData> infoLists = Collections.singletonList(data);
        if (VersionUtil.isNewTablist()) {
            EnumSet<EnumWrappers.PlayerInfoAction> actions = EnumSet.of(
                    EnumWrappers.PlayerInfoAction.ADD_PLAYER,
                    EnumWrappers.PlayerInfoAction.UPDATE_LATENCY,
                    EnumWrappers.PlayerInfoAction.UPDATE_LISTED, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
            packet.getPlayerInfoActions().write(0, actions);
            packet.getPlayerInfoDataLists().write(1, infoLists);
            return;
        }
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        packet.getPlayerInfoDataLists().write(0, infoLists);
    }

}
