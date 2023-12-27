package me.tahacheji.mafana.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.tahacheji.mafana.MafanaNetwork;
import me.tahacheji.mafana.itemData.GameItem;
import me.tahacheji.mafana.itemData.GameItemEvents;
import me.tahacheji.mafana.util.GameItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GameItemListener implements Listener {

    @EventHandler
    public void onHold(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!event.getPlayer().isOnline()) {
                    this.cancel();
                    return;
                }
                ItemStack itemStack = event.getPlayer().getItemInHand();
                GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
                if (gameItem != null) {
                    if(gameItem.onItemHoldAction(event.getPlayer(), itemStack)) {
                        gameItem.onItemUse(event.getPlayer(), itemStack);
                    }
                }
                for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
                    GameItem m = new GameItemUtil().getGameItem(i);
                    if(m != null) {
                        if(m.onItemWhileWearingAction(event.getPlayer(), i)) {
                            m.onItemUse(event.getPlayer(), i);
                        }
                    }
                }
            }
        }.runTaskTimer(MafanaNetwork.getInstance(), 0L, 20L);
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        ItemStack itemStack = event.getPlayer().getItemInHand();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if(gameItem.onItemJumpAction(event.getPlayer(), itemStack)) {
                gameItem.onItemUse(event.getPlayer(), itemStack);
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if(m.onItemJumpAction(event.getPlayer(), i)) {
                    m.onItemUse(event.getPlayer(), i);
                }
            }
        }
    }

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event) {
        ItemStack itemStack = event.getPlayer().getItemInHand();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if(gameItem.onItemShiftAction(event.getPlayer(), itemStack)) {
                gameItem.onItemUse(event.getPlayer(), itemStack);
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if(m.onItemShiftAction(event.getPlayer(), i)) {
                    m.onItemUse(event.getPlayer(), i);
                }
            }
        }
    }

    @EventHandler
    public void entityAction(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Entity entity = event.getEntity();
            Player player = (Player) event.getDamager();
            ItemStack itemStack = player.getItemInHand();
            GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
            if (gameItem != null) {
                if (gameItem.hitEntityAction(player, entity, event)) {
                    gameItem.onItemUse(player, itemStack);
                }
            }
            for(ItemStack i : player.getInventory().getArmorContents()) {
                GameItem m = new GameItemUtil().getGameItem(i);
                if(m != null) {
                    if(m.hitEntityAction(player, entity, event)) {
                        m.onItemUse(player, i);
                    }
                }
            }
        } else if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Entity entity = event.getDamager();
            ItemStack itemStack = player.getItemInHand();
            GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
            if (gameItem != null) {
                if (gameItem.getHitByEntityAction(player, entity, event)) {
                    gameItem.onItemUse(player, itemStack);
                }
            }
            for(ItemStack i : player.getInventory().getArmorContents()) {
                GameItem m = new GameItemUtil().getGameItem(i);
                if(m != null) {
                    if(m.getHitByEntityAction(player, entity, event)) {
                        m.onItemUse(player, i);
                    }
                }
            }
        }
    }

    @EventHandler
    public void breakBlockAction(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if (gameItem.breakBlockAction(player, event, event.getBlock(), itemStack)) {
                gameItem.onItemUse(player, itemStack);
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if(m.breakBlockAction(player, event, event.getBlock(), itemStack)) {
                    m.onItemUse(event.getPlayer(), i);
                }
            }
        }
    }

    @EventHandler
    public void breakBlockAction(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if (gameItem.placeBlockAction(player, event, event.getBlock(), itemStack)) {
                gameItem.onItemUse(player, itemStack);
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if(m.placeBlockAction(player, event, event.getBlock(), itemStack)) {
                    m.onItemUse(event.getPlayer(), i);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        if (event.getInventory().getType() == InventoryType.CRAFTING) {
            GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
            if (gameItem != null) {
                if (gameItem.clickedInInventoryAction(player, event, itemStack, event.getCursor())) {
                    gameItem.onItemUse(player, itemStack);
                }
            }
            for(ItemStack i : player.getInventory().getArmorContents()) {
                GameItem m = new GameItemUtil().getGameItem(i);
                if(m != null) {
                    if(m.clickedInInventoryAction(player, event, itemStack, event.getCursor())) {
                        m.onItemUse(player, i);
                    }
                }
            }
        }
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemDrop().getItemStack();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if (gameItem.dropItemAction(player, event, itemStack)) {
                gameItem.onItemUse(player, itemStack);
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if(m.dropItemAction(player, event, itemStack)) {
                    m.onItemUse(event.getPlayer(), i);
                }
            }
        }
    }


    @EventHandler
    public void leftClickAirAction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        GameItem gameItem = new GameItemUtil().getGameItem(itemStack);
        if (gameItem != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (gameItem.leftClickAirAction(player, itemStack)) {
                        gameItem.onItemUse(player, itemStack);
                    }
                } else if (gameItem.shiftLeftClickAirAction(player, itemStack)) {
                    gameItem.onItemUse(player, itemStack);
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (!player.isSneaking()) {
                    if (gameItem.leftClickBlockAction(player, event, event.getClickedBlock(), itemStack)) {
                        gameItem.onItemUse(player, itemStack);
                    }
                } else if (gameItem.shiftLeftClickBlockAction(player, event, event.getClickedBlock(), itemStack)) {
                    gameItem.onItemUse(player, itemStack);
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (gameItem.rightClickAirAction(player, itemStack)) {
                        gameItem.onItemUse(player, itemStack);
                    }
                } else if (gameItem.shiftRightClickAirAction(player, itemStack)) {
                    gameItem.onItemUse(player, itemStack);
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!player.isSneaking()) {
                    if (gameItem.rightClickBlockAction(player, event, event.getClickedBlock(), itemStack)) {
                        gameItem.onItemUse(player, itemStack);
                    }
                } else if (gameItem.shiftRightClickBlockAction(player, event, event.getClickedBlock(), itemStack)) {
                    gameItem.onItemUse(player, itemStack);
                }
            }
        }
        for(ItemStack i : event.getPlayer().getInventory().getArmorContents()) {
            GameItem m = new GameItemUtil().getGameItem(i);
            if(m != null) {
                if (event.getAction() == Action.LEFT_CLICK_AIR) {
                    if (!player.isSneaking()) {
                        if (m.leftClickAirAction(player, i)) {
                            m.onItemUse(player, i);
                        }
                    } else if (m.shiftLeftClickAirAction(player, i)) {
                        m.onItemUse(player, i);
                    }
                } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (!player.isSneaking()) {
                        if (m.leftClickBlockAction(player, event, event.getClickedBlock(), i)) {
                            m.onItemUse(player, i);
                        }
                    } else if (m.shiftLeftClickBlockAction(player, event, event.getClickedBlock(), i)) {
                        m.onItemUse(player, i);
                    }
                } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (!player.isSneaking()) {
                        if (m.rightClickAirAction(player, i)) {
                            m.onItemUse(player, i);
                        }
                    } else if (m.shiftRightClickAirAction(player, i)) {
                        m.onItemUse(player, i);
                    }
                } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (!player.isSneaking()) {
                        if (m.rightClickBlockAction(player, event, event.getClickedBlock(), i)) {
                            m.onItemUse(player, i);
                        }
                    } else if (m.shiftRightClickBlockAction(player, event, event.getClickedBlock(), i)) {
                        m.onItemUse(player, i);
                    }
                }
            }
        }
    }

}
