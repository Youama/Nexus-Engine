package com.youama.nexus.parser.collector;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public interface ICollector {

    /**
     * Initiate the default parsing process.
     */
    void parse();

    /**
     * Initiate the selected parsing process
     * @param rule
     */
    void parseByRule(int rule);

    /**
     * It retrieves the collected, parsed data in a list of objects.
     *
     * @return The value from the property.
     */
    List<CollectedItem> getItems();
}
