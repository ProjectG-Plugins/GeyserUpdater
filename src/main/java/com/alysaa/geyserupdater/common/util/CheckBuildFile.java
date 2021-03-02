package com.alysaa.geyserupdater.common.util;

import com.alysaa.geyserupdater.bungee.BungeeUpdater;
import com.alysaa.geyserupdater.spigot.SpigotUpdater;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckBuildFile {

    public static boolean checkBungeeFile() {
        Path p = Paths.get("plugins/GeyserUpdater/BuildUpdate/Geyser-BungeeCord.jar");
        boolean exists = Files.exists(p);
        if (exists) {
            BungeeUpdater.plugin.getLogger().info("New Geyser build has been downloaded! BungeeCord restart is required!");
            for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                if (all.hasPermission("gupdater.geyserupdate")) {
                    all.sendMessage(new TextComponent("[GeyserUpdater] New Geyser build has been downloaded! BungeeCord restart is required!"));
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkSpigotFile() {
        Path p = Paths.get("plugins/update/Geyser-Spigot.jar");
        boolean exists = Files.exists(p);
        if (exists) {
            SpigotUpdater.plugin.getLogger().info("New Geyser build has been downloaded! Server restart is required!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("gupdater.geyserupdate")) {
                    player.sendMessage("[GeyserUpdater] New Geyser build has been downloaded! Server restart is required!");
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

