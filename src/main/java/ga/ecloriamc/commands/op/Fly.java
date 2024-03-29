package ga.ecloriamc.commands.op;

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
            if(p.isFlying()) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous avez desactiver le fly !"));
            }
            else {
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous avez activer le fly !"));
            }
            return true;
        }
        else if(args.length == 1){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if(target.isOnline()) {
                Player targetP = (Player) target;
                p.setAllowFlight(!targetP.isFlying());
                targetP.setFlying(!targetP.isFlying());

                if(targetP.isFlying())
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous avez desactiver le fly à "+ target +"!"));
                else
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous avez activer le fly à "+ target +"!"));
            }
            return true;
        }
        return true;
    }
}