package me.tahacheji.mafana.packets.fakePlayer;

import me.tahacheji.mafana.util.FakePlayerUtil;

import java.util.UUID;

public class FakePlayer {

    private final TablistAddFakePlayerPacket tablistAddPacket;
    private final TablistRemoveFakePlayerPacket tablistRemovePacket;

    public FakePlayer(String name, String displayName) {
        UUID fakeUUID = UUID.randomUUID();
        this.tablistAddPacket = new TablistAddFakePlayerPacket(fakeUUID, name, displayName);
        this.tablistRemovePacket = new TablistRemoveFakePlayerPacket(fakeUUID, name, displayName);
    }

    public FakePlayer(String name) {
        this(name, " ");
    }

    public TablistAddFakePlayerPacket getTablistAddPacket() {
        return tablistAddPacket;
    }

    public TablistRemoveFakePlayerPacket getTablistRemovePacket() {
        return tablistRemovePacket;
    }

    public static FakePlayer randomFakePlayer() {
        return new FakePlayer(FakePlayerUtil.randomName());
    }

}
