package ru.muravlevas.disablearrowdespawn;

import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableArrowDespawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getScheduler().runTaskTimer(this, this::setTicksLivedForArrows, 0, 1);
    }

    public void setTicksLivedForArrows() {
        for (World world : getServer().getWorlds()) {
            world.getEntitiesByClasses(AbstractArrow.class).stream().map(AbstractArrow.class::cast).forEach(e -> e.setTicksLived(1));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onDespawnEntity(EntityRemoveEvent e) {
        if (e.getEntityType() == EntityType.ARROW || e.getEntityType() == EntityType.SPECTRAL_ARROW) {
            if (e.getCause() == EntityRemoveEvent.Cause.DESPAWN) {
                getLogger().info("§cARROW DESPAWNED AAAAAA BLYAATTT");
            }
        }
    }

    public void on(EntitySpawnEvent e) {
        if (e.getEntityType() == EntityType.ARROW || e.getEntityType() == EntityType.SPECTRAL_ARROW) {
            e.getEntity().setPersistent(true);
        }
    }
}
