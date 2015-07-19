package com.youama.nexus.parser.collector;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a collected item from a text based source code. This item can be words of a sentence or a
 * single HTML link or a single image URL. Many instances of this item represent a full HTML page. Images, sentences,
 * sharing links for example.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public class CollectedItem {

    /**
     * It contains sub-items. All items are String.
     */
    protected List<String> data = new ArrayList<String>();

    /**
     * It adds the value to the property or replaces it when data already contains a value.
     *
     * @param data It will be stored in the property.
     */
    public void addItem(List<String> data) {
        this.data = data;
    }

    /**
     * It adds a sub-item to the data property.
     *
     * @param data It will be stored in the property.
     */
    public void addItem(String data) {
        this.data.add(data);
    }

    public boolean hasData() {
        return data.size() > 0;
    }

    /**
     * It retrieves the data property.
     * @return The stored data from the property.
     */
    public List<String> getData() {
        return data;
    }
}
