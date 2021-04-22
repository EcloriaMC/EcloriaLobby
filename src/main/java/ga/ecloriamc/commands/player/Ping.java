package ga.ecloriamc.commands.player;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    EcloriaLobby plugin;

    public Ping(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        int ping = ((CraftPlayer) p).getHandle().ping;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Ton ping &7&l> &b"+ping+" &bms"));
        return true;
    }
}
