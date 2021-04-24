package ga.ecloriamc.listener;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.manager.InventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;


public class InventoryClickEvent implements Listener {

    EcloriaLobby plugin;
    InventoryManager inventoryManager;

    public InventoryClickEvent(EcloriaLobby plugin){
        this.plugin = plugin;
        inventoryManager = plugin.getInventoryManager();
    }

    @EventHandler
    public void inventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent e){
        InventoryView inv = e.getView();

        if(inv.getTitle().equals(ChatColor.translateAlternateColorCodes('&',"&7&l> &3Menu Des Jeux &7&l<"))){
            System.out.println("1");
            System.out.println(e.getCurrentItem().getType());
            Player p = (Player) e.getWhoClicked();


            if(e.getCurrentItem().getType() == Material.GRASS){
                plugin.getBungeeManager().connect(p,"Skymoon");
            }
            if(e.getCurrentItem().getType() == Material.REDSTONE_COMPARATOR){
                plugin.getBungeeManager().connect(p,"Paintball");
            }

            if(e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&f[&bSpawn&f]"))){
                p.teleport(plugin.getSpawnManager().getSpawnLocation());
            }
            if(e.getCurrentItem().getType() == Material.DIAMOND_AXE){
                plugin.getBungeeManager().connect(p,"KB-FFA");
            }
            if(e.getCurrentItem().getType() == Material.LOG){
                plugin.getBungeeManager().connect(p,"Cr\u00e9atif");
            }

        }
        e.setCancelled(true);
    }
    @EventHandler
    public void openInventory(PlayerInteractEvent e){
        Action action = e.getAction();

        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK) ||action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){

            Player p =  e.getPlayer();

            if(e.getItem() != null)
            if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&7&l> &3Menu Des Jeux &7&l<"))){
                Inventory gameMenu = inventoryManager.gameMenu(p);

                p.openInventory(gameMenu);
            }
        }
    }
}
