package ga.ecloriamc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;


public class PlayerEvent implements Listener {
    @EventHandler
    public void playerChangeHeldItem(PlayerItemHeldEvent e){
        Player p = e.getPlayer();

        if(e.getPreviousSlot()<e.getNewSlot()){
            int i = e.getNewSlot();
            if(e.getPlayer().getInventory().getItem(i) == null) {
                while (e.getPlayer().getInventory().getItem(i) == null) {
                    if(i==9) i = 0;
                    i++;
                }
                p.getInventory().setHeldItemSlot(i);
            }
        }
        else if(e.getPreviousSlot()>e.getNewSlot()){

            int i= e.getNewSlot();
            if(e.getPlayer().getInventory().getItem(i) == null) {
                while (e.getPlayer().getInventory().getItem(i) == null) {
                    if(i==-1) i = 9;
                    i--;
                }
                p.getInventory().setHeldItemSlot(i);
            }
        }

    }
    @EventHandler
    public void onSwitch(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }
}
