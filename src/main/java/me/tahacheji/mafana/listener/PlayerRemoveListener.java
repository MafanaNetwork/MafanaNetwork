package me.tahacheji.mafana.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.tahacheji.mafana.MafanaHub;

public class PlayerRemoveListener extends PacketAdapter {

    public PlayerRemoveListener(MafanaHub manager, ListenerPriority listenerPriority, PacketType... types) {
        super(manager.getPlugin(), listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {}

}
