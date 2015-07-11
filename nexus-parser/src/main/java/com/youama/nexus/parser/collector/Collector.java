package com.youama.nexus.parser.collector;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public abstract class Collector implements ICollector {

    /**
     * Any kind of text based source: HTML, JSON, XML, etc.
     */
    protected String source;

    /**
     * The parsed, collected data in a list of objects.
     */
    protected List<CollectedItem> parsedItems;

    /**
     * It stores the source in the property.
     *
     * @param source HTML, XML, JSON, etc. as String.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * It retrieves the untouched text based source.
     *
     * @return The value from the property.
     */
    public String getSource() {
        return source;
    }

    /**
     * It retrieves the collected, parsed data in a list of objects.
     *
     * @return The value from the property.
     */
    public List<CollectedItem> getParsedItems() {
        return parsedItems;
    }
}
