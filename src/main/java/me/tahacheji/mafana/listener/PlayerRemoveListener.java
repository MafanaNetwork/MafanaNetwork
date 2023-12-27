package me.tahacheji.mafana.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.tahacheji.mafana.MafanaNetwork;

public class PlayerRemoveListener extends PacketAdapter {

    public PlayerRemoveListener(ListenerPriority listenerPriority, PacketType... types) {
        super(MafanaNetwork.getInstance(), listenerPriority, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {}

}
