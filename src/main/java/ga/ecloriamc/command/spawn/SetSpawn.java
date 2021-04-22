package ga.ecloriamc.command.spawn;

import ga.ecloriamc.EcloriaLobby;
import ga.ecloriamc.manager.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class SetSpawn implements CommandExecutor {

    EcloriaLobby plugin;
    FileConfiguration config;
    SpawnManager spawnManager;

    public SetSpawn(EcloriaLobby plugin){
        this.plugin = plugin;
        this.config = plugin.getConfig();
        spawnManager = plugin.getSpawnManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            plugin.sendMessageToConsole("Vous devez etre un joueur pour executer cette commande" , true);
            return true;
        }
        Player p = (Player) sender;

        Location l = p.getLocation();
        spawnManager.setSpawnLocation(l.getWorld(), l.getX(),l.getY(), l.getZ(), (int) l.getPitch(),  (int) l.getYaw());
        p.sendMessage("Vous avez set le spawn");
        return true;
    }



}
