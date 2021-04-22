package ga.ecloriamc;

import ga.ecloriamc.commands.infos.*;
import ga.ecloriamc.commands.op.*;
import ga.ecloriamc.commands.spawn.SetSpawn;
import ga.ecloriamc.commands.spawn.Spawn;
import ga.ecloriamc.commands.player.Ping;
import ga.ecloriamc.listener.*;
import ga.ecloriamc.manager.InventoryManager;
import ga.ecloriamc.manager.SpawnManager;
import ga.ecloriamc.utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class EcloriaLobby extends JavaPlugin {
    private FileConfiguration config;

    private MessageHelper messageHelper;
    private InventoryManager inventoryManager;
    private SpawnManager spawnManager;

    @Override
    public void onEnable() {
        config();

        registerManager();
        registerEvents();
        registerCommands();

        sendMessageToConsole("&aPlugin chargé avec succès", false);
    }

    @Override
    public void onDisable() {
        sendMessageToConsole("&cPlugin dechargé avec succès", false);
    }

    private void registerEvents(){
        registerEvent(new JoinEvent(this));
        registerEvent(new LeaveEvent());
        registerEvent(new CancelEvent(this));
        registerEvent(new PlayerChatEvent());
        registerEvent(new InventoryClickEvent(this));
    }

    private void registerCommands(){
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new Spawn(this));

        getCommand("info").setExecutor(new Informations(this));
        getCommand("discord").setExecutor(new Discord(this));
        getCommand("dwade").setExecutor(new Dwade(this));
        getCommand("instagram").setExecutor(new Instagram(this));
        getCommand("site").setExecutor(new Site(this));
        getCommand("twitter").setExecutor(new Twitter(this));
        getCommand("youtube").setExecutor(new Youtube(this));
        getCommand("ping").setExecutor(new Ping(this));

        getCommand("sun").setExecutor(new Sun(this));
        getCommand("night").setExecutor(new Night(this));
        getCommand("rain").setExecutor(new Rain(this));
        getCommand("gm").setExecutor(new Gamemode(this));
        getCommand("day").setExecutor(new Day(this));
        getCommand("broadcast").setExecutor(new Day(this));
        getCommand("fly").setExecutor(new Fly(this));
    }

    private void registerManager(){
        spawnManager = new SpawnManager(this);
        inventoryManager = new InventoryManager(this);
        messageHelper = new MessageHelper(this);
    }

    private void config(){
        saveDefaultConfig();
        File configFile = new File(getDataFolder(),"config.yml");
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException ioException) {
            ioException.printStackTrace();
        }
    }
    private void registerEvent(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void sendMessageToConsole(String message, boolean isError){
        if(isError) System.out.println(ChatColor.translateAlternateColorCodes('&', "&c"+message));
        else System.out.println(ChatColor.translateAlternateColorCodes('&', message));
    }

    public MessageHelper getMessageHelper() { return messageHelper; }
    public InventoryManager getInventoryManager() { return inventoryManager; }
    public SpawnManager getSpawnManager() { return spawnManager; }
}
