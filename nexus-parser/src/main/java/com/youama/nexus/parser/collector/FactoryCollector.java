package com.youama.nexus.parser.collector;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public class FactoryCollector {

    /**
     * Collects links.
     */
    public static final int TYPE_LINK = 1;

    public static final int TYPE_TEXT = 2;

    public static final int TYPE_IMAGE = 3;

    /**
     * It retrieves the collector object by collector type.
     *
     * @param collectorType It can be given by constants of the FactoryCollector.
     * @return Implementation of the ICollector interface.
     */
    public static ICollector getCollector(int collectorType) {
        switch (collectorType) {
            case TYPE_LINK: return new LinkCollector();
            default: return null;
        }
    }
}
