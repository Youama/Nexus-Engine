package com.youama.nexus.parser.collector;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public interface ICollector {

    /**
     * Any kind of text based source as String, e.g.: HTML.
     *
     * @param source HTML, XML, JSON, etc. as String.
     */
    void setSource(String source);

    /**
     * It retrieves the untouched text based source.
     *
     * @return The value from the property.
     */
    String getSource();

    /**
     * Initiate the parsing process.
     */
    void doParse();

    /**
     * It retrieves the collected, parsed data in a list of objects.
     *
     * @return The value from the property.
     */
    List<CollectedItem> getParsedItems();
}
