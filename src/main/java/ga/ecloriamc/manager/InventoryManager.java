package ga.ecloriamc.manager;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogRecord;


public class InventoryManager {

    EcloriaLobby plugin;

    public InventoryManager(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    public void setItemsOnJoin(Player p) {
        Inventory getInventory = p.getInventory();
        getInventory.setItem(0, createGuiItemColor(Material.DIAMOND, (short) 0,0,"&7&l> &3Menu Des Jeux &7&l<","",""));
        getInventory.setItem(4, getPlayerHead(p ,color("&7&l> &3Profil &7&l<")));
        getInventory.setItem(8, createGuiItemColor(Material.GOLD_INGOT, (short) 0,0 ,"&7&l> &3Boutique &7&l<","", "" ));
    }


    public Inventory gameMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 45,color("&7&l> &3Menu Des Jeux &7&l<"));
        setGlassPanel(inv);

        String[] playerCount = new String[4];
        playerCount[0] = "0";
        playerCount[1] = "0";
        playerCount[2] = "0";
        playerCount[3] = "0";

        plugin.getBungeeManager().getPlayerCount("Skymoon").whenComplete((result, error) -> {
            playerCount[0] = result.toString();
            p.sendMessage(error.getMessage());
        });
        plugin.getBungeeManager().getPlayerCount("Paintball").whenComplete((result, error) -> {
            playerCount[1] = result.toString();
        });
        plugin.getBungeeManager().getPlayerCount("KB-FFA").whenComplete((result, error) -> {
            playerCount[2] = result.toString();
        });
        plugin.getBungeeManager().getPlayerCount("Cr\u00e9atif").whenComplete((result, error) -> {
            playerCount[3] = result.toString();
        });

        String playerCountSkymoon = playerCount[0];
        String playerCountPaintball = playerCount[1];
        String playerCountKBFFA = playerCount[2];
        String playerCountCreatif = playerCount[3];
        int playerCountSpawn = p.getServer().getOnlinePlayers().size();

        if(plugin.getBungeeManager().isOpen("Skymoon"))
            inv.setItem(12, createGuiItemColor(Material.GRASS, (short) 0,2, " &f[&bSkymoon&f]","&f&lIl y a &b&l"+playerCountSkymoon + "&f&l connect\u00e9s au &b&lSkymoon.","&7&l> &3Soon &7&l<"));
        else
            inv.setItem(12, createGuiItemColor(Material.GRASS, (short) 0,2, " &f[&bSkymoon&f]","  &c&lferm\u00e9"," &7&l> &3Soon &7&l<"));

        if(plugin.getBungeeManager().isOpen("Paintball"))
            inv.setItem(14, createGuiItemColor(Material.REDSTONE_COMPARATOR, (short) 0,2, " &f[&bPaintball&f]","&f&lIl y a &b&l"+playerCountPaintball + "&f&l connect\u00e9s au &b&lPaintball.","&7&l> &3Soon &7&l<"));
        else
            inv.setItem(14, createGuiItemColor(Material.REDSTONE_COMPARATOR, (short) 0,2, " &f[&bPaintball&f]","  &c&lferm\u00e9"," &7&l> &3Soon &7&l<"));

        inv.setItem(22, createGuiItemColor(Material.NETHER_STAR, (short) 0,1, "&f[&bSpawn&f]","&f&lIl y a &b&l"+playerCountSpawn + "&f&l connect\u00e9s au &b&lSpawn.",""));

        if(plugin.getBungeeManager().isOpen("KB-FFA"))
            inv.setItem(29, createGuiItemColor(Material.DIAMOND_AXE, (short) 0,1, "&f[&bKnockback FFA&f]","&f&lIl y a &b&l"+playerCountKBFFA + "&f&l connect\u00e9s au &b&lKnockback FFA.",""));
        else
            inv.setItem(29, createGuiItemColor(Material.DIAMOND_AXE, (short) 0,1, "&f[&bKnockback FFA&f]","      &c&lferm\u00e9",""));

        if(plugin.getBungeeManager().isOpen("Cr\u00e9atif"))
        inv.setItem(33, createGuiItemColor(Material.LOG, (short) 0,2, " &f[&bCr\u00e9atif&f]","&f&lIl y a &b&l"+playerCountCreatif + "&f&l connect\u00e9s au &b&lCr\u00e9atif.","&7&l> &3Soon &7&l<"));
        else
            inv.setItem(33, createGuiItemColor(Material.LOG, (short) 0,2, " &f[&bCr\u00e9atif&f]"," &c&lferm\u00e9", "&7&l> &3Soon &7&l<"));

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
        inv.setItem(i, createGuiItemColor(Material.STAINED_GLASS_PANE, color,0,"", "", "" ));
    }

    private String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }


    private ItemStack createGuiItemColor(Material material, short color, int nbrLine,String name, String... lore){
        return createGuiItem(material,color,color(name),nbrLine,color(lore[0]), color(lore[1]));
    }


    protected ItemStack createGuiItem(Material material, short color, String name, int nbrLine,String... lore ) {
        final ItemStack item;

        if(color>=0) item = new ItemStack(material, 1,color);
        else item = new ItemStack(material, 1);


        String[] newLore;

        if(nbrLine==0){
            newLore = new String[0];
        }
        else if(nbrLine == 1){
            System.out.println(1);
            int i;
            newLore = new String[1];
            for(i=0;i<2;i++){
                if(!lore[i].isEmpty()) newLore[0] = lore[i];
            }
        }
        else {
            System.out.println(2);
            newLore = lore;
        }

        if(color == -1){
            LeatherArmorMeta meta;
            meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(Color.WHITE);
            meta.setDisplayName(name);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


            if(newLore.length>0)
                meta.setLore(Arrays.asList(newLore));


            item.setItemMeta(meta);
        }
        else {
            final ItemMeta meta;
            meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            if(newLore.length>0)
                meta.setLore(Arrays.asList(newLore));

            item.setItemMeta(meta);
        }

        return item;
    }

}
