package ga.ecloriamc.manager;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.utils.MessageHelper;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;


public class InventoryManager {

    EcloriaLobby plugin;

    public InventoryManager(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    public void setItemsOnJoin(Player p) {
        Inventory getInventory = p.getInventory();
        getInventory.setItem(4, getPlayerHead(p , ChatColor.translateAlternateColorCodes('&',"&7&l> &3Profil &7&l<")));
        getInventory.setItem(8, createGuiItem(Material.GOLD_INGOT, (short) 0, ChatColor.translateAlternateColorCodes('&',"&7&l> &3Boutique &7&l<"),"", "" ));

    }

    protected ItemStack getPlayerHead(Player p, String name){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1);
        item.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(p.getName());
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    protected ItemStack createGuiItem(final Material material, final short color, final String name, final String... lore) {
        final ItemStack item;

        if(color==0) item = new ItemStack(material, 1,color);
        else item = new ItemStack(material, 1);



        if(color == -1){
            LeatherArmorMeta meta;
            meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(Color.WHITE);
            meta.setDisplayName(name);

            List<String> loreList = Arrays.asList(lore);
            if(!loreList.get(0).isEmpty() && !loreList.get(1).isEmpty()) meta.setLore(loreList);
            item.setItemMeta(meta);
        }
        else {
            final ItemMeta meta;
            meta = item.getItemMeta();
            meta.setDisplayName(name);

            List<String> loreList = Arrays.asList(lore);
            if(!loreList.get(0).isEmpty() && !loreList.get(1).isEmpty()) meta.setLore(loreList);
            item.setItemMeta(meta);
        }

        return item;
    }

}
