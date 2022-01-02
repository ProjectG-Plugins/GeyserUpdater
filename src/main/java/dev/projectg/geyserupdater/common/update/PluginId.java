package dev.projectg.geyserupdater.common.update;

import dev.projectg.geyserupdater.common.config.UpdaterConfiguration;

public enum PluginId {
    GEYSER("https://ci.opencollab.dev/job/GeyserMC/job/Geyser/job/", "org.geysermc.connector.GeyserConnector"),
    FLOODGATE("https://ci.opencollab.dev/job/GeyserMC/job/Floodgate/job/", "org.geysermc.floodgate.FloodgatePlatform");

    /**
     * https://ci.opencollab.dev/job/GeyserMC/job/{PLUGIN_PAGE}/job/
     */
    private final String projectLink;

    /**
     * A class from the given plugin
     */
    private final String pluginClassName;
    private String branch;
    private String artifactLink;

    private boolean enable = false;
    private boolean autoCheck = false;
    private boolean autoUpdate = false;

    PluginId(String link, String pluginClassName) {
        this.projectLink = link;
        this.pluginClassName = pluginClassName;
    }

    public boolean isEnable() {
        return enable;
    }
    public boolean isAutoCheck() {
        return autoCheck;
    }
    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    /**
     * @return The download link. Not usable if {@link PluginId#setArtifact(String)} has not been called.
     */
    public String getLatestFileLink() {
        return projectLink + branch + "/lastSuccessfulBuild/" + artifactLink;
    }

    public String getLatestBuildNumber() {
        return projectLink + branch + "/lastSuccessfulBuild/buildNumber";
    }

    /**
     * @param branch The branch to be used for {@link PluginId#getLatestFileLink()}
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @param artifactLink The artifact link. `bootstrap/spigot/target/Geyser-Spigot.jar` for example.
     */
    public void setArtifact(String artifactLink) {
        this.artifactLink = "artifact/" + artifactLink;
    }

    /**
     * @return A class from the plugin. Will throw an {@link ClassNotFoundException} if the class is not available, i.e. the plugin is not loaded.
     */
    public Class<?> getPluginClass() throws ClassNotFoundException {
        return Class.forName(pluginClassName);
    }

    /**
     * Load the enable, autoCheck, and autoUpdate configuration settings into the enum values.
     * {@link JacksonConfiguration#getUpdateEntries()} should contain entries whose keys are equal an enum value's name in lowercase
     * @param config The config to load from
     */
    public static void loadSettings(UpdaterConfiguration config) {
        throw new UnsupportedOperationException();
    }
}
