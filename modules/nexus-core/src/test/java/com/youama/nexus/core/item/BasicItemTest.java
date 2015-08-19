package com.youama.nexus.core.item;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.11.
 */
public class BasicItemTest extends TestCase {

    @Test
    public void testAddItem() {
        // For addItem
        BasicItem item = new BasicItem();
        item.addItem("something/thing.png");
        Assert.assertEquals("something/thing.png", item.data.get(0));

        item.addItem("something/thing2.png");
        Assert.assertEquals("something/thing2.png", item.data.get(1));

        // For addItem overload
        BasicItem item2 = new BasicItem();
        List<String> items = new ArrayList<String>();
        items.add("something/thing.png");
        items.add("something/thing2.png");
        item2.addItem(items);
        Assert.assertEquals(2, item2.data.size());
        Assert.assertEquals("something/thing2.png", item2.data.get(1));
    }

    @Test
    public void testHasData() {
        BasicItem item = new BasicItem();
        Assert.assertFalse(item.hasData());

        item.addItem("value");
        Assert.assertTrue(item.hasData());
    }

    @Test
    public void testGetData() {
        BasicItem item = new BasicItem();
        item.addItem("something/thing.png");
        Assert.assertEquals(1, item.getData().size());
    }
}