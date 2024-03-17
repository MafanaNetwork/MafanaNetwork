package me.tahacheji.mafana.command;

import me.tahacheji.mafana.commandExecutor.Command;
import me.tahacheji.mafana.commandExecutor.bukkit.Material;
import me.tahacheji.mafana.commandExecutor.paramter.Param;
import me.tahacheji.mafana.itemData.GameItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ItemCreateCommand {


    @Command(names = "mn create", permission = "mafana.admin", playerOnly = true)
    public void createItem(Player player, @Param(name = "ID") String id, @Param(name = "name") String name, @Param(name = "material") Material material, @Param(name = "glow") boolean glow, @Param(name = "lore", concated = true) String lore) {
        lore = ChatColor.translateAlternateColorCodes('&', lore);
        name = ChatColor.translateAlternateColorCodes('&', name);
        GameItem gameItem = new GameItem(id, name, material.getMaterials().get(0), glow, List.of(lore));
        player.getInventory().addItem(gameItem.getItem());
    }

}
