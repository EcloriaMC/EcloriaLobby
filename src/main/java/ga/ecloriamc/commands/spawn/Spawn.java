package ga.ecloriamc.commands.spawn;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.manager.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    EcloriaLobby plugin;
    FileConfiguration config;
    SpawnManager spawnManager;

    public Spawn(EcloriaLobby plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
        spawnManager = plugin.getSpawnManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            plugin.sendMessageToConsole("Vous devez etre un joueur", true);
            return true;
        }
        Player p = (Player) sender;
        p.teleport(spawnManager.getSpawnLocation());
        return true;
    }
}
