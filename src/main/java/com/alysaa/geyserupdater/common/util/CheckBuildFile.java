package com.alysaa.geyserupdater.common.util;

import com.alysaa.geyserupdater.bungee.BungeeUpdater;
import com.alysaa.geyserupdater.spigot.SpigotUpdater;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckBuildFile {

    public static boolean checkBungeeFile() {
        Path p = Paths.get("plugins/GeyserUpdater/BuildUpdate/Geyser-BungeeCord.jar");
        boolean exists = Files.exists(p);
        if (exists) {
            BungeeUpdater.plugin.getLogger().info("New Geyser build has been downloaded! BungeeCord restart is required!");
            return true;
        }
        return false;
    }
    public static boolean checkSpigotFile() {
        Path p = Paths.get("plugins/update/Geyser-Spigot.jar");
        boolean exists = Files.exists(p);
        if (exists) {
            SpigotUpdater.plugin.getLogger().info("New Geyser build has been downloaded! Server restart is required!");
            return true;
        }
        return false;
    }
}

