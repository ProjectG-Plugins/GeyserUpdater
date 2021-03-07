package com.alysaa.geyserupdater.spigot.util;

import com.alysaa.geyserupdater.common.util.CheckBuildFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpigotJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // We allow a cached result of maximum age 30 minutes to be used
        if (CheckBuildFile.checkSpigotFile(true)) {
            if (event.getPlayer().hasPermission("gupdater.geyserupdate")) {
                event.getPlayer().sendMessage("[GeyserUpdater] New Geyser build has been downloaded! Server restart is required!");
            }
        }
    }
}