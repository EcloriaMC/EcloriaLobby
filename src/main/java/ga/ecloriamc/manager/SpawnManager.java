package ga.ecloriamc.manager;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {

    EcloriaLobby plugin;
    FileConfiguration config;
    ConfigurationSection spawnLocationConfig;

    public SpawnManager(EcloriaLobby plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
        spawnLocationConfig = config.getConfigurationSection("SpawnLocation");
    }

    public Location getSpawnLocation(){
        World getWorld = Bukkit.getServer().getWorld(spawnLocationConfig.getString("World"));
        double getX = spawnLocationConfig.getDouble("X");
        double getY = spawnLocationConfig.getDouble("Y");
        double getZ = spawnLocationConfig.getDouble("Z");
        double getYaw = spawnLocationConfig.getDouble("yaw");
        double getPitch = spawnLocationConfig.getDouble("pitch");

        return new Location(getWorld, getX , getY, getZ, (float) getYaw, (float) getPitch);
    }

    public void setSpawnLocation(World world, double X, double Y, double Z, int pitch, int yaw){
        spawnLocationConfig.set("World", world.getName());
        spawnLocationConfig.set("X", X);
        spawnLocationConfig.set("Y", Y);
        spawnLocationConfig.set("Z", Z);
        spawnLocationConfig.set("pitch", pitch);
        spawnLocationConfig.set("yaw", yaw);
        plugin.saveConfig();
    }
}
