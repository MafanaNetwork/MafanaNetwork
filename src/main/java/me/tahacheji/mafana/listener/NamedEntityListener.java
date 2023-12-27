package me.tahacheji.mafana.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.tahacheji.mafana.MafanaHub;
import me.tahacheji.mafana.packets.PacketSender;
import me.tahacheji.mafana.packets.TablistAddPlayerPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NamedEntityListener extends PacketAdapter {



    public NamedEntityListener(MafanaHub manager, ListenerPriority listenerPriority, PacketType... types) {
        super(manager.getPlugin(), listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        Player targetPlayer = event.getPlayer();
        UUID entityUUID = packet.getUUIDs().read(0);
        Player packetPlayer = Bukkit.getPlayer(entityUUID);
        if (packetPlayer != null) {
            PacketSender tablistAddPacket = new TablistAddPlayerPacket(packetPlayer);
            PacketSender tablistAddPacket2 = new TablistAddPlayerPacket(targetPlayer);
            tablistAddPacket.sendPacketOnce(targetPlayer);
            tablistAddPacket2.sendPacketOnce(packetPlayer);
        }
    }

}
