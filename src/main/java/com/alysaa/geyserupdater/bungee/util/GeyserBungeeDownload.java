package com.alysaa.geyserupdater.bungee.util;

import com.alysaa.geyserupdater.bungee.BungeeUpdater;
import com.alysaa.geyserupdater.common.util.CheckBuildFile;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class GeyserBungeeDownload {
    public static void downloadGeyser() {
        OutputStream os = null;
        InputStream is = null;
        String fileUrl = "https://ci.opencollab.dev/job/GeyserMC/job/Geyser/job/master/lastSuccessfulBuild/artifact/bootstrap/bungeecord/target/Geyser-BungeeCord.jar";
        String outputPath = ("plugins/GeyserUpdater/BuildUpdate/Geyser-BungeeCord.jar");
        try {
            // create a url object
            URL url = new URL(fileUrl);
            // connection to the file
            URLConnection connection = url.openConnection();
            // get input stream to the file
            is = connection.getInputStream();
            // get output stream to download file
            os = new FileOutputStream(outputPath);
            final byte[] b = new byte[2048];
            int length;
            // read from input stream and write to output stream
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close streams
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Check if the file was downloaded successfully
        boolean downloadSuccess = CheckBuildFile.checkBungeeFile(false);
        // Restart the server if the option is enabled
        if (BungeeUpdater.getConfiguration().getBoolean("Auto-Restart-Server") && downloadSuccess) {
            BungeeUpdater.plugin.getLogger().info("The Server will restart in 10 Seconds!");
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', BungeeUpdater.getConfiguration().getString("Restart-Message-Players"))));
            }
            Runnable runnable = () -> {
                try {
                    Thread.sleep(10000);
                    ProxyServer.getInstance().stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}