package com.youama.nexus.parser.collector;

/**
 * It is a factory class for retrieving the different kind of BaseCollector classes.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.11.
 */
public class FactoryCollector {

    /**
     * For link collector.
     */
    public static final int TYPE_LINK = 1;

    /**
     * For text collector.
     */
    public static final int TYPE_TEXT = 2;

    /**
     * For image url collector.
     */
    public static final int TYPE_IMAGE = 3;

    /**
     * It retrieves the collector object by collector type ID and the source.
     *
     * @param collectorType It is given by constants of the FactoryCollector.
     * @param source The source code.
     * @return Implementation of the Collector interface.
     */
    public static Collector getCollector(int collectorType, String source) {
        switch (collectorType) {
            case TYPE_LINK: return new LinkCollector(source);
            default: return null;
        }
    }
}
