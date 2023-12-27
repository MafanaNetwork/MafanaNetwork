package me.tahacheji.mafana.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.tahacheji.mafana.MafanaNetwork;
import me.tahacheji.mafana.packets.PacketSender;
import me.tahacheji.mafana.packets.TabListAddPlayerPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NamedEntityListener extends PacketAdapter {



    public NamedEntityListener(ListenerPriority listenerPriority, PacketType... types) {
        super(MafanaNetwork.getInstance(), listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        Player targetPlayer = event.getPlayer();
        UUID entityUUID = packet.getUUIDs().read(0);
        Player packetPlayer = Bukkit.getPlayer(entityUUID);
        if (packetPlayer != null) {
            PacketSender tablistAddPacket = new TabListAddPlayerPacket(packetPlayer);
            PacketSender tablistAddPacket2 = new TabListAddPlayerPacket(targetPlayer);
            tablistAddPacket.sendPacketOnce(targetPlayer);
            tablistAddPacket2.sendPacketOnce(packetPlayer);
        }
    }

}
