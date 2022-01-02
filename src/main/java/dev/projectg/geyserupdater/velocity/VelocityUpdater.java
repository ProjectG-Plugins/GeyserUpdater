package dev.projectg.geyserupdater.velocity;

import com.google.inject.Inject;
import dev.projectg.geyserupdater.common.GeyserUpdater;
import dev.projectg.geyserupdater.common.UpdaterBootstrap;
import dev.projectg.geyserupdater.common.logger.UpdaterLogger;
import dev.projectg.geyserupdater.common.util.ScriptCreator;
import dev.projectg.geyserupdater.velocity.command.GeyserUpdateCommand;
import dev.projectg.geyserupdater.velocity.logger.Slf4jUpdaterLogger;
import dev.projectg.geyserupdater.velocity.bstats.Metrics;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;
import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(id = "geyserupdater", name = "GeyserUpdater", version = VelocityUpdater.VERSION,
        description = "Automatically or manually downloads new builds of Geyser and applies them on server restart.",
        authors = {"Jens", "Konicai"})
public class VelocityUpdater implements UpdaterBootstrap {

    public static final String VERSION = "1.6.0";

    private GeyserUpdater updater;
    private final ProxyServer server;
    private final Path dataDirectory;
    private final Logger logger;
    private final Metrics.Factory metricsFactory;

    @Inject
    public VelocityUpdater(ProxyServer server, Logger baseLogger, @DataDirectory final Path dataDirectory, Metrics.Factory metricsFactory) {
        this.server  = server;
        this.dataDirectory = dataDirectory;
        this.logger = baseLogger;
        this.metricsFactory = metricsFactory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) throws IOException, InvalidConfigException {

        updater = new GeyserUpdater(
                dataDirectory,
                dataDirectory.resolve("BuildUpdate"),
                dataDirectory.getParent(),
                this,
                new Slf4jUpdaterLogger(logger),
                new VelocityScheduler(this),
                new VelocityPlayerHandler(server),
                VERSION,
                "bootstrap/velocity/target/Geyser-Velocity.jar",
                "bootstrap/velocity/target/floodgate-velocity.jar"
        );

        // Register our only command
        server.getCommandManager().register("geyserupdate", new GeyserUpdateCommand());
        metricsFactory.make(this, 10673);
    }

    @Subscribe(order = PostOrder.LAST)
    public void onShutdown(ProxyShutdownEvent event) {
        // PostOrder.LAST ensures that we don't modify plugin jars while they are being used.
        // Hopefully plugins that are being updated don't also use PostOrder.LAST
        onDisable();
    }

    @Override
    public void onDisable() {

        if (updater != null) {
            try {
                updater.shutdown();
            } catch (IOException e) {
                UpdaterLogger.getLogger().error("Failed to install ALL updates:");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createRestartScript() throws IOException {
        ScriptCreator.createRestartScript(true);
    }

    public ProxyServer getProxyServer() {
        return server;
    }
}






