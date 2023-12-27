package me.tahacheji.mafana.packets;

import org.bukkit.entity.Player;

public interface TabListUpdater {

    default void updateFakePlayer(Player player) {}
}
