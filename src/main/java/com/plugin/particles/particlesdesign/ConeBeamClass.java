package com.plugin.particles.particlesdesign;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class ConeBeamClass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void CreateShape(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            System.out.println("Debug");
            Location loc = e.getPlayer().getLocation();

            new BukkitRunnable() {
                double u = 0;


                @Override
                public void run() {
                    u += Math.PI/12;
                    for (double v = 0; v <= 1; v += 0.1) {
                        double x = 2*v*Math.cos(u);
                        double y =2*v*Math.sin(u) + 1.5;
                        double z =Math.pow(4*v, 2) * Math.pow(Math.cos(u), 2) + Math.pow(8*v, 2) * Math.pow(Math.sin(u), 2);
                        loc.add(x, y ,z);
                        e.getPlayer().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                        loc.subtract(x, y, z);

                    }


                 if (u >= Math.PI*2) cancel();
                }





            }.runTaskTimer(this, 0, 1L);


        }

    }

}