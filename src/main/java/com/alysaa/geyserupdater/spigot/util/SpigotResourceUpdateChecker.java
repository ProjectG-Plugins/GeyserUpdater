package com.alysaa.geyserupdater.spigot.util;

import com.alysaa.geyserupdater.bungee.BungeeUpdater;
import com.alysaa.geyserupdater.spigot.SpigotUpdater;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class SpigotResourceUpdateChecker {

    private JavaPlugin plugin;
    private int resourceId;

    public SpigotResourceUpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public static String getVersion(SpigotUpdater updater) {

        try (InputStream inputStream = new URL("https://api.spigotmc.org/simple/0.1/index.php?action=getResource&id=88555").openStream(); Scanner scanner = new Scanner(inputStream)) {
            String total = "";
            while (scanner.hasNext()) {
                total += scanner.next();
            }
            JsonObject jsonObject = new JsonParser().parse(total).getAsJsonObject();
            String version = jsonObject.get("current_version").getAsString();
            return version;
        } catch (IOException exception) {
            updater.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            return null;
        }
    }
}
