package com.youama.nexus.core.system;

import java.io.File;
import java.util.Properties;

/**
 * This static class is the main class of the Nexus engine. The NexusCoreUtil class provides access to other classes and
 * objects what are provide global, resource and system/configuration specified features.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.09.
 */
final public class NexusCoreUtil {

    public static void initServices(String artifactId) {
        Configuration.getInstance().setRegisteredPrimaryModuleArtifactId(artifactId);
        ServiceUtil.initServiceDriver();
    }
}
