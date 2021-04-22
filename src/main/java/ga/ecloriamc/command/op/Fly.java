package ga.ecloriamc.command.op;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    EcloriaLobby plugin;

    public Fly(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            p.setFlying(true);
            return true;
        }
        else if(args.length == 1){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if(target.isOnline()) {
                Player targetP = (Player) target;
                targetP.setFlying(!targetP.isFlying());

                if(targetP.isFlying())
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous avez desactiver le fly à "+ target +"!"));
                else
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous avez activer le fly à "+ target +"!"));
            }
            return true;
        }
        return true;
    }
}