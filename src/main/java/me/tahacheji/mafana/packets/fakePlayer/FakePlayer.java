package me.tahacheji.mafana.packets.fakePlayer;

import me.tahacheji.mafana.util.FakePlayerUtil;

import java.util.UUID;

public class FakePlayer {

    private final TabListAddFakePlayerPacket tablistAddPacket;
    private final TabListRemoveFakePlayerPacket tablistRemovePacket;


    public FakePlayer(String name, String displayName) {
        UUID fakeUUID = UUID.randomUUID();
        this.tablistAddPacket = new TabListAddFakePlayerPacket(fakeUUID, name, displayName);
        this.tablistRemovePacket = new TabListRemoveFakePlayerPacket(fakeUUID, name, displayName);
    }

    public FakePlayer(String name, String displayName, SkinType skinType) {
        UUID fakeUUID = UUID.randomUUID();
        this.tablistAddPacket = new TabListAddFakePlayerPacket(fakeUUID, name, displayName, skinType);
        this.tablistRemovePacket = new TabListRemoveFakePlayerPacket(fakeUUID, name, displayName, skinType);
    }

    public FakePlayer(String name) {
        this(name, " ");
    }



    public TabListAddFakePlayerPacket getTablistAddPacket() {
        return tablistAddPacket;
    }

    public TabListRemoveFakePlayerPacket getTablistRemovePacket() {
        return tablistRemovePacket;
    }

    public static FakePlayer randomFakePlayer() {
        return new FakePlayer(FakePlayerUtil.randomName());
    }

}
