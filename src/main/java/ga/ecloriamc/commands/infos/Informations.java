package ga.ecloriamc.commands.infos;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Informations implements CommandExecutor {

    EcloriaLobby plugin;

    public Informations(EcloriaLobby plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            plugin.sendMessageToConsole("Vous devez être un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&9&m-------- &7()- Informations -()&9&m--------&r    \n"+
                         " \n"+
                         "&f&l> &9Notre Discord : &bhttps://discord.gg/DNfRkMumSu&r\n"+
                         "&f&l> &9Notre Twitter : &b&bhttps://twitter.com/MoonlighterOffi&r\n"+
                         "&f&l> &9Notre Site : &bhttp://ecloriamc.fr&r\n"+
                         "&f&l> &9Notre Mail: &bMoonlighterPvP@gmail.com&r\n"+
                         "&f&l> &9Notre Insta: &bhttps://www.instagram.com/moonlighterpvp/?hl=fr&r \n"+
                         " \n"+
                         "&9&m-------- &7()- Informations -()&9&m--------&r\n"
        ));

        return true;
    }
}
