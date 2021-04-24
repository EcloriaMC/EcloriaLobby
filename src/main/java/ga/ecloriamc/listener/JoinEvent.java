package ga.ecloriamc.listener;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.manager.InventoryManager;
import ga.ecloriamc.manager.SpawnManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class JoinEvent implements Listener {

    EcloriaLobby plugin;
    FileConfiguration config;
    SpawnManager spawnManager;
    InventoryManager inventoryManager;

    public JoinEvent(EcloriaLobby plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
        spawnManager = plugin.getSpawnManager();
        inventoryManager = plugin.getInventoryManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.getInventory().clear();
        p.teleport(spawnManager.getSpawnLocation());
        p.setHealth(20.0);

        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        p.addPotionEffect(PotionEffectType.SPEED.createEffect(999999, 1));
        inventoryManager.setItemsOnJoin(p);

        if(checkGroup(p)) e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&f[&b&l+&f] "+ getPrefix(p)+" "+p.getName()));
        else e.setJoinMessage("");
    }

    private String getGroup(Player p){
        UserManager userManager = LuckPermsProvider.get().getUserManager();
        String group = userManager.getUser(p.getName()).getCachedData().getMetaData().getPrimaryGroup();
        return group;
    }

    private String getPrefix(Player p){
        UserManager userManager = LuckPermsProvider.get().getUserManager();
        String group = userManager.getUser(p.getName()).getCachedData().getMetaData().getPrefix();
        return group;
    }

    private boolean checkGroup(Player p){
        return getGroup(p).contains("admin") || getGroup(p).contains("modérateur") || getGroup(p).contains("super-modo") ||
                getGroup(p).contains("développeur") || getGroup(p).contains("buildeur") || getGroup(p).contains("helper") ||
                getGroup(p).contains("youtubeur") || getGroup(p).contains("mini-youtubeur");
    }


}

