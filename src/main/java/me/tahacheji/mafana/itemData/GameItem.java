package me.tahacheji.mafana.itemData;

import me.tahacheji.mafana.util.EncryptionUtil;
import me.tahacheji.mafana.util.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameItem implements GameItemEvents{

    private String name;
    private Material material;
    private boolean glow;
    private String itemUUID;
    private List<String> lore = new ArrayList<>();

    public GameItem(String name, Material material, boolean glow, List<String> lore) {
        this.name = name;
        this.material = material;
        this.glow = glow;
        this.lore = lore;
        this.itemUUID = UUID.randomUUID().toString();
    }

    public GameItem(String name, Material material, boolean glow, String... lore) {
        this.name = name;
        this.material = material;
        this.glow = glow;
        this.lore = List.of(lore);
        this.itemUUID = UUID.randomUUID().toString();
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if(glow) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        item = NBTUtils.setString(item, "GameItemUUID", getItemUUID());
        return item;
    }

    public GameItem createNewInstance() {
        try {
            return this.getClass().newInstance();
        } catch (Exception ignore) {}
        return null;
    }

    public void remove(Player player, int amount) {
        ItemStack itemStack = player.getItemInHand();
        if(itemStack.getAmount() == amount) {
            player.setItemInHand(new ItemStack(Material.AIR));
        } else {
            itemStack.setAmount(itemStack.getAmount() - amount);
            player.setItemInHand(itemStack);
        }
    }

    public void removeInventory(InventoryClickEvent event, int amount) {
        ItemStack itemStack = event.getCurrentItem(); // Use event.getCurrentItem() to get the item in the player's inventory.

        if (itemStack != null) {
            if (itemStack.getAmount() == amount) {
                event.setCurrentItem(new ItemStack(Material.AIR));
            } else if (itemStack.getAmount() > amount) {
                itemStack.setAmount(itemStack.getAmount() - amount);
                event.setCurrentItem(itemStack); // Update the item in the player's inventory.
            } else {
                event.setCurrentItem(new ItemStack(Material.AIR));
            }
        }
    }


    public void removeDrop(PlayerDropItemEvent event, int amount) {
        ItemStack itemStack = event.getItemDrop().getItemStack();

        if (itemStack.getAmount() > amount) {
            itemStack.setAmount(itemStack.getAmount() - amount);
            event.getItemDrop().setItemStack(itemStack);
        } else {
            event.getItemDrop().setItemStack(new ItemStack(Material.AIR));
        }
    }

    public boolean compare(ItemStack other) {
        String otherUUID = NBTUtils.getString(other, "GameItemUUID");
        return otherUUID.equalsIgnoreCase(itemUUID);
    }

    public String getItemUUID() {
        return itemUUID;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isGlow() {
        return glow;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }
}
