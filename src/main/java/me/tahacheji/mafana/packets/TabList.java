package me.tahacheji.mafana.packets;

public class Tablist {

    private final PacketSender packet;

    protected Tablist(TablistTemplate template) {
        this.packet = new TabListPacket(template);
    }

    protected PacketSender getPacket() {
        return this.packet;
    }

}
