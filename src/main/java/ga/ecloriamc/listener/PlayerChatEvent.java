package ga.ecloriamc.listener;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class PlayerChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(org.bukkit.event.player.PlayerChatEvent e){
        LuckPerms lP = LuckPermsProvider.get();
        UserManager uM = lP.getUserManager();
        Player p = e.getPlayer();

        String prefix = uM.getUser(e.getPlayer().getName()).getCachedData().getMetaData().getPrefix();
        e.setFormat(ChatColor.translateAlternateColorCodes('&', prefix +" &f"+p.getName()+" &e&l> &7"+e.getMessage() ));
    }
}
