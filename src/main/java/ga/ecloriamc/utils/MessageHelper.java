package ga.ecloriamc.utils;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;

public class MessageHelper {

    EcloriaLobby plugin;

    public MessageHelper(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    public String chatColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
