package ru.muravlevas.disablearrowdespawn;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableArrowDespawn extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onDespawn(ItemDespawnEvent e) {
                if (e.getEntityType() == EntityType.ARROW) {
                    e.getEntity().setTicksLived(1);
                    e.setCancelled(true);
                }
            }
        }, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
