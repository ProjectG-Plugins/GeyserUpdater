package dev.projectg.geyserupdater.spigot;

import dev.projectg.geyserupdater.common.PlayerManager;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpigotPlayerHandler implements PlayerManager {

    private final Server server;

    public SpigotPlayerHandler(Server server) {
        this.server = server;
    }

    @Override
    public @NotNull List<UUID> getOnlinePlayers() {
        List<UUID> uuidList = new ArrayList<>();
        for (Player player : server.getOnlinePlayers()) {
            uuidList.add(player.getUniqueId());
        }
        return uuidList;
    }

    @Override
    public void sendMessage(@NotNull UUID uuid, @NotNull String message) {
        server.getPlayer(uuid).sendMessage(message);
    }
}
