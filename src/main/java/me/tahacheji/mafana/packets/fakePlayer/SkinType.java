package me.tahacheji.mafana.packets.fakePlayer;

import org.bukkit.OfflinePlayer;

public class SkinType {

    private String signature;
    private String texture;

    public SkinType(String signature, String texture) {
        this.signature = signature;
        this.texture = texture;
    }


    public String getSignature() {
        return signature;
    }

    public String getTexture() {
        return texture;
    }

}
