package ga.ecloriamc.listener;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.manager.InventoryManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickEvent implements Listener {

    EcloriaLobby plugin;
    InventoryManager inventoryManager;

    public InventoryClickEvent(EcloriaLobby plugin){
        this.plugin = plugin;
        inventoryManager = plugin.getInventoryManager();
    }

    @EventHandler
    public void inventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent e){
        Inventory inv = e.getInventory();

        if(e.getClick() == ClickType.RIGHT && e.getClick() == ClickType.LEFT){
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo("test");
            BungeeCord.getInstance().getPlayer("").connect(serverInfo);
        }

        if(inv.getName().equals("&7&l> &3Menu Des Jeux &7&l<")){
            if(e.getCurrentItem().getType() == Material.WOOD){

            }
        }
        e.setCancelled(true);
    }
    @EventHandler
    public void openInventory(PlayerInteractEvent e){
        Action action = e.getAction();

        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK) ||action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){

            Player p =  e.getPlayer();
            if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&7&l> &3Menu Des Jeux &7&l<"))){
                Inventory gameMenu = inventoryManager.gameMenu(p);
                p.openInventory(gameMenu);
            }
        }
    }

}
