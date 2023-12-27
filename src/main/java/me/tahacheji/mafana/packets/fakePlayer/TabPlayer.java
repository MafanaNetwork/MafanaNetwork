package me.tahacheji.mafana.packets.fakePlayer;

import me.tahacheji.mafana.packets.skin.DARK_GRAY_SKIN_TYPE;
import me.tahacheji.mafana.util.FakePlayerUtil;

import java.util.UUID;

public class TabPlayer {

    private int slot;
    private String displayName;
    private SkinType skinType;
    private String id;

    private TabListAddFakePlayerPacket tabListAddFakePlayerPacket;

    public TabPlayer(int slot, String displayName) {
        this.slot = slot;
        this.displayName = displayName;
        this.skinType = new DARK_GRAY_SKIN_TYPE();
        this.id = null;
        this.tabListAddFakePlayerPacket = new TabListAddFakePlayerPacket(UUID.randomUUID(), new FakePlayerUtil().convertNumberToLetter(slot), displayName);
    }

    public TabPlayer(int slot, String displayName, SkinType skinType) {
        this.slot = slot;
        this.displayName = displayName;
        this.skinType = skinType;
        this.id = null;
        this.tabListAddFakePlayerPacket = new TabListAddFakePlayerPacket(UUID.randomUUID(), new FakePlayerUtil().convertNumberToLetter(slot), displayName, skinType);
    }


    public TabPlayer(int slot, String displayName, String id) {
        this.slot = slot;
        this.displayName = displayName;
        this.id = id;
        this.tabListAddFakePlayerPacket = new TabListAddFakePlayerPacket(UUID.randomUUID(), new FakePlayerUtil().convertNumberToLetter(slot), displayName);
    }

    public TabPlayer(int slot, String displayName, SkinType skinType, String id) {
        this.slot = slot;
        this.displayName = displayName;
        this.skinType = skinType;
        this.id = id;
        this.tabListAddFakePlayerPacket = new TabListAddFakePlayerPacket(UUID.randomUUID(), new FakePlayerUtil().convertNumberToLetter(slot), displayName, skinType);
    }

    public int getSlot() {
        return slot;
    }

    public String getDisplayName() {
        return displayName;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public String getId() {
        return id;
    }

    public TabListAddFakePlayerPacket getFakePlayerPacket() {
       return tabListAddFakePlayerPacket;
    }
}
