package ga.ecloriamc.manager;

import ga.ecloriamc.EcloriaLobby;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class InventoryManager {

    EcloriaLobby plugin;

    public InventoryManager(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    public void setItemsOnJoin(Player p) {
        Inventory getInventory = p.getInventory();
        getInventory.setItem(0, createGuiItemColor(Material.NETHER_STAR, (short) 0,0,"&7&l> &3Menu Des Jeux &7&l<","",""));
        getInventory.setItem(4, getPlayerHead(p ,color("&7&l> &3Profil &7&l<")));
        getInventory.setItem(8, createGuiItemColor(Material.GOLD_INGOT, (short) 0,0 ,"&7&l> &3Boutique &7&l<","", "" ));
    }


    public Inventory gameMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 45,color("&7&l> &3Menu Des Jeux &7&l<"));
        setGlassPanel(inv);

        getServerItem(p,inv,Material.GRASS, (short) 0,12,"Skymoon",2,true);
        getServerItem(p,inv,Material.REDSTONE_COMPARATOR,(short) 0, 14,"Paintball",2,true);

        getServerItem(p,inv,Material.DIAMOND_AXE, (short) 0, 29,"Knockback FFA",2,false);
        getServerItem(p,inv,Material.LOG, (short) 0,33,"Cr\u00e9atif",2,true);

        int playerCountSpawn = p.getServer().getOnlinePlayers().size();
        inv.setItem(22, createGuiItemColor(Material.NETHER_STAR, (short) 0,1, "&f[&bSpawn&f]","&f&lIl y a &b&l"+playerCountSpawn + "&f&l connect\u00e9s au &b&lSpawn.",""));

        return inv;
    }


    private void getServerItem(Player p, Inventory inv,Material material,short color, int i, String serverName, int nbrLine,boolean isSoon){
        String server = "";
        if(serverName.equals("Knockback FFA"))server = "KB-FFA";
        else server = serverName;

        String finalServer = server;

        plugin.getBungeeManager().getServerIp(p,server).whenComplete((result, error) -> {
            try{
                String ip = result.getHostName();
                int port = result.getPort();

                try {
                    Socket s = new Socket(ip,port);
                    s.close();
                    plugin.getBungeeManager().getPlayerCount(p,finalServer).whenComplete((result2, error2) -> {
                        String playerCount = result2.toString();
                        inv.setItem(i,createGuiItemColor(material,  color,nbrLine, "&f[&b"+ serverName +"&f]","&f&lIl y a &b&l"+ playerCount + "&f&l connect\u00e9s au &b&l"+ serverName +".", isSoon?"&7&l> &3Soon &7&l<":""));
                    });
                } catch (IOException e) {
                    inv.setItem(i, createGuiItemColor(material, color,nbrLine, "&f[&b"+ serverName +"&f]","&c&lferm\u00e9",isSoon?"&7&l> &3Soon &7&l<":""));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
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
        inv.setItem(i, createGuiItemColor(Material.STAINED_GLASS_PANE, color,0," ", "", "" ));
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

            int i;
            newLore = new String[1];
            for(i=0;i<2;i++){
                if(!lore[i].isEmpty()) newLore[0] = lore[i];
            }
        } else newLore = lore;


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
