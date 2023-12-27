package me.tahacheji.mafana.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabListPacket implements PacketSender {

    private final TablistTemplate tablistTemplate;
    private final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
    private final PacketContainer packet;

    protected TabListPacket(TablistTemplate tablistTemplate) {
        this.packet = this.manager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        this.tablistTemplate = tablistTemplate;
    }

    @Override
    public void sendPacketOnce(Player player) {
        this.packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', this.tablistTemplate.getHeader())));
        this.packet.getChatComponents().write(1, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', this.tablistTemplate.getFooter())));
        this.manager.sendServerPacket(player, this.packet);
    }
}
