package dev.projectg.geyserupdater.common.update.identity.provider;

import dev.projectg.geyserupdater.common.logger.UpdaterLogger;
import dev.projectg.geyserupdater.common.update.identity.type.BuildNumber;
import dev.projectg.geyserupdater.common.util.WebUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Provides a build number from a given Jenkins link.
 */
public class JenkinsBuildProvider implements IdentityProvider<BuildNumber> {

    private final URL url;

    /**
     * Creates a build number provider for a given link.
     * @param link A link which onto which "/buildNumber" will be added, which should link to a page that contains only the build number as a plain integer. For example: https://ci.opencollab.dev/job/GeyserMC/job/Geyser/job/master/lastSuccessfulBuild/buildNumber
     * @throws MalformedURLException If the link provided cannot be converted to a {@link URL}
     */
    public JenkinsBuildProvider(String link) throws MalformedURLException {
        url = new URL(link + "/buildNumber");
    }

    @Override
    public BuildNumber getValue() {
        BuildNumber buildNumber = null;
        try {
            String body = WebUtils.getBody(url);
            try {
                buildNumber = new BuildNumber(Integer.parseInt(body));
            } catch (NumberFormatException e) {
                UpdaterLogger.getLogger().error("Failed to get a build number from a Jenkins server because an integer was not returned.");
                UpdaterLogger.getLogger().error("Body returned: <" + body + "> (excluding the angle brackets)");
                e.printStackTrace();
            }
        } catch (IOException e) {
            UpdaterLogger.getLogger().error("Failed to get a build number from a Jenkins server:");
            e.printStackTrace();
        }

        return buildNumber;
    }
}
