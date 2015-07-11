package com.youama.nexus.parser.collector;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public class LinkCollector extends Collector implements ICollector {

    public void doParse() {
        getLinks();
    }

    protected boolean getLinks() {
        return true;
    }
}
