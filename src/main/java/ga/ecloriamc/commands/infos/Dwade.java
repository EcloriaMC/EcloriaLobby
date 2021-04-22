package ga.ecloriamc.commands.infos;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Dwade implements CommandExecutor {
    EcloriaLobby plugin;

    public Dwade(EcloriaLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l&l> &3Chaine de Dwade &7&l> &bhttps://www.youtube.com/Dwade_Gaming"));
        return true;
    }
}
