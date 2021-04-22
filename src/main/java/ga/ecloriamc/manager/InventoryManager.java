package ga.ecloriamc.manager;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
        getInventory.setItem(0, createGuiItemColor(Material.DIAMOND, (short) 0,"&7&l> &3Menu Des Jeux &7&l<","",""));
        getInventory.setItem(4, getPlayerHead(p ,color("&7&l> &3Profil &7&l<")));
        getInventory.setItem(8, createGuiItemColor(Material.GOLD_INGOT, (short) 0, "&7&l> &3Boutique &7&l<","", "" ));
    }


    public Inventory gameMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 45,color("&7&l> &3Menu Des Jeux &7&l<"));
        setGlassPanel(inv);

        inv.setItem(12, createGuiItemColor(Material.GRASS, (short) 0, "&7&lun jeu","",""));
        inv.setItem(14, createGuiItemColor(Material.REDSTONE_COMPARATOR, (short) 0, "&7&lun jeu","",""));
        inv.setItem(22, createGuiItemColor(Material.NETHER_STAR, (short) 0, "&7&lun jeu","",""));
        inv.setItem(29, createGuiItemColor(Material.DIAMOND_AXE, (short) 0, "&7&lun jeu","",""));
        inv.setItem(33, createGuiItemColor(Material.LOG, (short) 0, "&7&lun jeu","",""));
        return inv;
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

    private void setGlassPanel(Inventory inv){
        setGlassPane(inv,0, (short) 0);
        setGlassPane(inv,1, (short) 0);
        setGlassPane(inv,9, (short) 0);

        setGlassPane(inv,7, (short) 9);
        setGlassPane(inv,8, (short) 9);
        setGlassPane(inv,17, (short) 9);

        setGlassPane(inv,27, (short) 9);
        setGlassPane(inv,36, (short) 9);
        setGlassPane(inv,37, (short) 9);

        setGlassPane(inv,35, (short) 0);
        setGlassPane(inv,43, (short) 0);
        setGlassPane(inv,44, (short) 0);
    }

    private void setGlassPane(Inventory inv,int i,short color){
        inv.setItem(i, createGuiItemColor(Material.STAINED_GLASS_PANE, color, "", "", "" ));
    }

    private String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private ItemStack createGuiItemColor(final Material material, final short color, final String name , final String... lore){
        return createGuiItem(material,color,color(name),color(lore[0]),color(lore[1]));
    }

    protected ItemStack createGuiItem(final Material material, final short color, final String name, final String... lore) {
        final ItemStack item;

        if(color>=0) item = new ItemStack(material, 1,color);
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
