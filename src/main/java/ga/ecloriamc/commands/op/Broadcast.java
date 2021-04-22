package ga.ecloriamc.commands.op;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
    EcloriaLobby plugin;

    public Broadcast(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        int numberOfWord = args.length;
        if(numberOfWord == 0){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"Vous devez ecrire: /broadcast <msg>"));
            return true;
        }


        String message = "";

        for(int i = numberOfWord; i < args.length; i++){
            message += " " + args[i];
        }

        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',message));
        return true;
    }
}