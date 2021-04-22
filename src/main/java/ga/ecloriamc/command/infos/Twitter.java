package ga.ecloriamc.command.infos;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Twitter implements CommandExecutor {
    EcloriaLobby plugin;

    public Twitter(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l&l> &3Notre Twitter &7&l> &bhttps://twitter.com/MoonlighterOffi"));
        return true;
    }
}
