package com.youama.nexus.core.item;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a collected item from a text based source code. This item can be words of a sentence or a
 * single HTML link or a single image URL. Many instances of this item represent a full HTML page. Images, sentences,
 * sharing links for example.
 *
 * @author David Belicza
 * @since 2015.08.01.
 */
public class BasicItem {

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

    /**
     * It retrieves that it has any item or it doesn't have.
     *
     * @return It is true when it has data.
     */
    public boolean hasData() {
        return data.size() > 0;
    }

    /**
     * It retrieves the data property.
     *
     * @return The stored data from the property.
     */
    public List<String> getData() {
        return data;
    }
}
