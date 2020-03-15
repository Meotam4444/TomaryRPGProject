package com.Meotam.listener;

import com.Meotam.TomaryRPGProject.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class interactEvent implements Listener {

    //Befüllt die "Truhe" wenn der Spieler sie öffnet

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType() == Material.EMERALD_BLOCK) {
                    Player p = e.getPlayer();
                    Inventory inv = Bukkit.createInventory(null, 9, "Boss Loot");
                    ItemStack sword = new ItemStack(Material.GOLD_SWORD);
                    ItemMeta im = sword.getItemMeta();
                    im.setDisplayName("§6Flammenschwert");
                    im.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
                    sword.setItemMeta(im);
                    ItemStack emerald = new ItemStack(Material.EMERALD);
                    inv.setItem(4, sword);
                    inv.setItem(0, emerald);
                    inv.setItem(1, emerald);
                    inv.setItem(2, emerald);
                    inv.setItem(3, emerald);
                    inv.setItem(5, emerald);
                    inv.setItem(6, emerald);
                    inv.setItem(7, emerald);
                    inv.setItem(8, emerald);
                    p.openInventory(inv);
                    e.getClickedBlock().setType(Material.AIR);
                }
        }
    }

}
