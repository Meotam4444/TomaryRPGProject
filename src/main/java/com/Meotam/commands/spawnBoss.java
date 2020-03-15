package com.Meotam.commands;



import com.Meotam.TomaryRPGProject.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class spawnBoss implements CommandExecutor {

    //spawnt den Pig Boss bei Eingabe von /spawnBoss Pig. Dieser spawnt bei Tötung eine Truhe mit Emeralds und einem modifizierten Schwert, was bei einem Angriff das Ziel in Flammen setzt.

    private int taskID;
    private int taskID1;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1)
        {


            {
                if(commandSender instanceof Player) {
                    if (strings[0].equalsIgnoreCase("pig")) {
                        Player p = (Player) commandSender;
                        if (p.hasPermission("spawnboss.pig")) {
                            // Erster Schedule für Bossspawn

                            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                                int countdown = 5;

                                public void run() {
                                    if (countdown <= 0) {
                                        Bukkit.getScheduler().cancelTask(taskID);
                                        Bukkit.broadcastMessage("§4Der Boss ist gespawnt!");
                                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 1, 0);
                                        String w1 = p.getWorld().getName();
                                        Location location = new Location(p.getWorld(), (int) p.getLocation().getX() + 5, p.getLocation().getBlockY(), (int) p.getLocation().getZ());
                                        Bukkit.getServer().getWorld(w1).getBlockAt(location).setType(Material.AIR);
                                        Pig pigboss = location.getWorld().spawn(location, Pig.class);
                                        pigboss.setCustomName("§dSchweinekönig");
                                        pigboss.setCustomNameVisible(true);

                                        // Zweiter Schedule für Zombiespawn
                                        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                                            public void run() {
                                                Location loc = pigboss.getLocation();
                                                if (pigboss.getHealth() > 0) {
                                                    int loc1Z = (int) pigboss.getLocation().getZ() + 2;
                                                    int loc2Z = (int) pigboss.getLocation().getZ() - 2;
                                                    int locX = (int) pigboss.getLocation().getX();
                                                    int locY = (int) pigboss.getLocation().getY();
                                                    Zombie z1 = loc.getWorld().spawn(new Location(p.getWorld(), locX, locY, loc1Z), Zombie.class);
                                                    z1.setCustomName("§7Sklave");
                                                    z1.setCustomNameVisible(true);
                                                    Zombie z2 = loc.getWorld().spawn(new Location(p.getWorld(), locX, locY, loc2Z), Zombie.class);
                                                    z2.setCustomName("§7Sklave");
                                                    z2.setCustomNameVisible(true);

                                                } else {
                                                    //spawnt die Loot-"Truhe"
                                                    Block b = pigboss.getWorld().getBlockAt(pigboss.getLocation());
                                                    b.setType(Material.EMERALD_BLOCK);
                                                    Bukkit.getScheduler().cancelTask(taskID1);
                                                }
                                            }
                                        }, 0, 60);

                                    } else {
                                        Bukkit.broadcastMessage("§4Der Boss spawnt in " + countdown);
                                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                                        countdown--;
                                    }

                                }
                            }, 0, 20);
                        }
                    } else {
                        commandSender.sendMessage("§4Gib einen gültigen Boss an!");
                    }
                }

            }
        }


        return false;
    }
}
