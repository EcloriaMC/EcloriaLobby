package ga.ecloriamc.listener;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelEvent implements Listener {

    EcloriaLobby plugin;

    public CancelEvent(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onClickArmorStand(PlayerInteractEntityEvent e){
        if(e.getRightClicked().getType() == EntityType.ARMOR_STAND) e.setCancelled(true);
    }
    @EventHandler
    public void onInterractTrapDoor(PlayerInteractEvent e){
        if(e.getClickedBlock().getType() == Material.TRAP_DOOR)
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onCraft(CraftItemEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageEvent e){

        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            DamageCause damageCause = e.getCause();

            if(damageCause == EntityDamageEvent.DamageCause.VOID ||damageCause == EntityDamageEvent.DamageCause.FALL){
                p.setFallDistance(0);
                if(damageCause == EntityDamageEvent.DamageCause.VOID) p.teleport(plugin.getSpawnManager().getSpawnLocation());
            }
        }
    }

}
