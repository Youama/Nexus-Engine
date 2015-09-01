package com.youama.nexus.parser.collector;

/**
 * Constants of this enum should be used for BaseCollector objects.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.12.
 */
public enum CollectorSelector {

    /**
     * Gathers all links.
     */
    LINK_ALL,

    /**
     * Gathers all links what have nofollow value in the rel attribute.
     */
    LINK_NO_FOLLOW,

    /**
     * Gathers all correct links.
     */
    LINK_VALID,

    /**
     * Gathers all correct links what are point to another pages not resources like css, js, etc.
     */
    LINK_VALID_NO_RESOURCES,

    /**
     * Gathers all text from the HTML source.
     */
    TEXT_ALL,

    /**
     * Gathers all text from the body of the HTML source.
     */
    TEXT_BODY
}
