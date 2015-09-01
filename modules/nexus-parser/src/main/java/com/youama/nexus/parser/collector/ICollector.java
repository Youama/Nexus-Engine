package com.youama.nexus.parser.collector;

import com.youama.nexus.core.item.BasicItem;

import java.util.List;

/**
 * Interface for Collector classes. Collectors can collect specified data from a source and they can retrieve them.
 * The parsing logic is protected or private and they are unique for each kind of Collectors.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.11.
 */
public interface ICollector {

    /**
     * Initiate the default parsing process. This method has to be implemented in a non-abstract class.
     */
    void parse();

    /**
     * Initiate the selected parsing process. This method has to be implemented in a non-abstract class.
     *
     * @param rule The ID of the rule.
     */
    void parseByRule(CollectorSelector rule);

    /**
     * It retrieves the collected, parsed data in a list of objects. This method has to be implemented on abstract
     * level.
     *
     * @return The value from the property.
     */
    List<BasicItem> getItems();
}
