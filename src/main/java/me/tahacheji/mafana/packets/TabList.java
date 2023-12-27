package me.tahacheji.mafana.packets;

public class TabList {

    private final PacketSender packet;

    protected TabList(TabListTemplate template) {
        this.packet = new TabListPacket(template);
    }

    protected PacketSender getPacket() {
        return this.packet;
    }

}
