package me.tahacheji.mafana.packets;

import org.bukkit.entity.Player;

public class TabListPlayer {

    private Player player;
    private TabListTemplate template;

    public TabListPlayer(Player player, TabListTemplate template) {
        this.player = player;
        this.template = template;
    }

    public Player getPlayer() {
        return player;
    }

    public TabListTemplate getTemplate() {
        return template;
    }


}
