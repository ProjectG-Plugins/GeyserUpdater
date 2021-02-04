[![Build Status](https://ci.alysaa.net/job/GeyserUpdater/job/main/badge/icon)](https://ci.alysaa.net/job/GeyserUpdater/job/main/)
[![License](https://img.shields.io/badge/License-GPL-orange)](https://github.com/YHDiamond/GeyserUpdater/blob/main/LICENSE)
[![bStats Spigot](https://img.shields.io/bstats/servers/10202?color=yellow&label=Spigot%20servers)](https://bstats.org/plugin/bukkit/GeyserUpdater/10202)
[![bStats Bungee](https://img.shields.io/bstats/servers/10203?label=Bungee%20servers)](https://bstats.org/plugin/bungeecord/GeyserUpdater/10203)
[![Discord](https://img.shields.io/discord/806179549498966058?color=7289da&label=discord&logo=discord&logoColor=white)](https://discord.gg/xXzzdAXa2b)
[![Spigot page downloads](https://img.shields.io/spiget/downloads/88555?color=yellow&label=Spigot%20page%20downloads)](https://www.spigotmc.org/resources/geyserupdater.88555/)
[![Spigot reviews](https://img.shields.io/spiget/stars/88555?color=yellow&label=Spigot%20rating)](https://www.spigotmc.org/resources/geyserupdater.88555/)

# GeyserUpdater
 
GeyserUpdater is a plugin that can update Geyser-Spigot | Geyser-BungeeCord manually or automatically. 

## Downloading

Please download the plugin from [our Spigot page](https://www.spigotmc.org/resources/geyserupdater.88555/) so we can get an idea of how many people are downloading the plugin.

If you don't want to use the Spigot website you can download the plugin [on our CI](https://ci.alysaa.net/job/GeyserUpdater/job/main) or use the actions button in the GitHub bar and download the zip with the GeyserUpdater jar inside.

## Installation

GeyserUpdater can be installed on Spigot & Bungeecord. Place the GeyserUpdater plugin file in the plugins folder and restart the server.


# Permissions:
```
gupdater.geyserupdate
```

# Commands:
```
/geyserupdate 
```

# Auto-Updating:

You can set Auto-Updating to true in the config.yml.


# Auto-Restart:

Once you enable auto-restart it will restart the server automatically after each new build that has being downloaded,
therefore you will need to setup a restart script that the server can use (via /restart)!

*Example*

```
@echo off
:restart
java -Xms-X-G -Xmx-X-G -jar ServerSoftware.jar nogui
Goto restart

```

## Usage

After you ran the command or enabled auto updating, it will check the current running Geyser version. If it's outdated, it will automatically download the latest Geyser build. Changes will only take place once the server has been shutdown correctly and restarted. Do not kill (Hard shutdown) the server/proxy or the updater wont update Geyser!

## bStats
[Spigot stats](https://bstats.org/plugin/bukkit/GeyserUpdater/10202)

[Bungee stats](https://bstats.org/plugin/bungeecord/GeyserUpdater/10203)

## Discord
[Here](https://discord.gg/xXzzdAXa2b) is our Discord link, you can come to get support, get GitHub feeds, or just hang out. Don't ping anyone that doesn't have the pingable role!

## Spigot page
[Here](https://www.spigotmc.org/resources/geyserupdater.88555/) is our Spigot page if you want to download it there (it makes the download count go up) or post a review (they help)!

## Release History
* 0.2.6
    * Add warning for players in config option
* 0.2.5
    * Added bStats support
* 0.2.4
    * Added auto restart on Spigot/Bungeecord after downloading update.
    * If you use this build you will need to regenerate config.yml file due to changes!
* 0.2.2
    * Mini-rework > Common classes.
    * Jenkins CI has been setup.
    * Pipeline has been added.
* 0.1.0
    * Merged Spigot & Bungeecord updater into 1 plugin.
    * Added maven

## Meta

The project is owned by:
- Jens
- YHDiamond

Note: This is NOT an official GeyserMC plugin. It is made to work with GeyserMC but it is not maintained or produced by GeyserMC. If you need support with this plugin, do not ask Geyser devs, and instead, go to our Discord server linked above.

 
