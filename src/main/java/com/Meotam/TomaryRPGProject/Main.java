package com.Meotam.TomaryRPGProject;

import com.Meotam.commands.spawnBoss;
import com.Meotam.listener.interactEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;


    @Override
    public void onEnable()
    {
        plugin = this;
        this.getCommand("spawnBoss").setExecutor(new spawnBoss());
        Bukkit.getPluginManager().registerEvents(new interactEvent(), this);
    }

    public static Main getPlugin()

    {

        return plugin;
    }

}