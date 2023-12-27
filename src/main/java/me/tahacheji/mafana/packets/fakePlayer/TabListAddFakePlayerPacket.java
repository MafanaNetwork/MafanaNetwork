package me.tahacheji.mafana.packets.fakePlayer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.entity.Player;

import java.util.*;

public class TabListAddFakePlayerPacket extends FakePlayerPacket {

    private WrappedGameProfile profile;
    private PacketContainer packet;

    protected TabListAddFakePlayerPacket(UUID uuid, String playerName, String displayText) {
        super(uuid, playerName, displayText);
        WrappedGameProfile gameProfile = new WrappedGameProfile(uuid, playerName);
        profile = gameProfile;
        PacketContainer packet = createPacket();
        setPacket(packet);
        PlayerInfoData data = new PlayerInfoData(FakePlayerPacket.changeGameProfileSkin(gameProfile), 20, EnumWrappers.NativeGameMode.CREATIVE, WrappedChatComponent.fromLegacyText(displayText));
        List<PlayerInfoData> infoLists = Collections.singletonList(data);
        if (VersionUtil.isNewTabList()) {
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

    public void updateDisplayName(String newDisplayName, Player player) {
        PacketContainer packet = createPacket();
        setPacket(packet);
        List<PlayerInfoData> infoLists = new ArrayList<>();

        PlayerInfoData playerInfoData = new PlayerInfoData(profile, 20, EnumWrappers.NativeGameMode.CREATIVE, WrappedChatComponent.fromLegacyText(newDisplayName));

        infoLists.add(playerInfoData);

        packet.getPlayerInfoDataLists().write(1, infoLists);
        sendPacketOnce(player);
    }


    protected TabListAddFakePlayerPacket(UUID uuid, String playerName, String displayText, SkinType skinType) {
        super(uuid, playerName, displayText);
        WrappedGameProfile gameProfile = new WrappedGameProfile(uuid, playerName);
        profile = FakePlayerPacket.changeGameProfileSkin(gameProfile, skinType.getSignature(), skinType.getTexture());
        PacketContainer packet = createPacket();
        setPacket(packet);

        int fixedLatency = 20;

        PlayerInfoData data = new PlayerInfoData(
                FakePlayerPacket.changeGameProfileSkin(gameProfile, skinType.getSignature(), skinType.getTexture()),
                fixedLatency,
                EnumWrappers.NativeGameMode.CREATIVE,
                WrappedChatComponent.fromLegacyText(displayText)
        );

        List<PlayerInfoData> infoLists = Collections.singletonList(data);

        if (VersionUtil.isNewTabList()) {
            EnumSet<EnumWrappers.PlayerInfoAction> actions = EnumSet.of(
                    EnumWrappers.PlayerInfoAction.ADD_PLAYER,
                    EnumWrappers.PlayerInfoAction.UPDATE_LATENCY,
                    EnumWrappers.PlayerInfoAction.UPDATE_LISTED,
                    EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME
            );
            packet.getPlayerInfoActions().write(0, actions);
            packet.getPlayerInfoDataLists().write(1, infoLists);
            return;
        }

        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        packet.getPlayerInfoDataLists().write(0, infoLists);
    }


    private PacketContainer createPacket() {
        return packet = packet == null ? ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO) : packet;
    }

    public PacketContainer getPacket() {
        return packet;
    }

    public WrappedGameProfile getProfile() {
        return profile;
    }


}
