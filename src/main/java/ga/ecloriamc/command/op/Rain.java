package ga.ecloriamc.command.op;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rain implements CommandExecutor {
    EcloriaLobby plugin;

    public Rain(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        if(!p.getWorld().hasStorm()) {
            p.getWorld().setStorm(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous avez mis la pluie"));
        }
        else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Il pleut deja !"));
        }

        return true;
    }
}