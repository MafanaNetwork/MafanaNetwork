package me.tahacheji.mafana.util;

import de.tr7zw.nbtapi.NBTItem;
import me.tahacheji.mafana.MafanaNetwork;
import me.tahacheji.mafana.itemData.GameItem;
import org.bukkit.inventory.ItemStack;

public class GameItemUtil {

    public GameItem getGameItem(ItemStack itemStack) {
        if(itemStack != null) {
            if(itemStack.getItemMeta() != null) {
                if(new NBTItem(itemStack).hasTag("GameItemUUID")) {
                    for(GameItem gameItem : MafanaNetwork.getInstance().getGameItems()) {
                        if(gameItem.compare(itemStack)) {
                            return gameItem;
                        }
                    }
                }
            }
        }
        return null;
    }
}
