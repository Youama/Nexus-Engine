package com.youama.nexus.parser.collector;

/**
 * Constants of this class should be used for Collector objects.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.12.
 */
final public class HelperCollector {

    /**
     * Gathers all links.
     */
    final public static int LINK_ALL = 1;

    /**
     * Gathers all links what have nofollow value in the rel attribute.
     */
    final public static int LINK_NO_FOLLOW = 2;

    /**
     * Gathers all correct links.
     */
    final public static int LINK_VALID = 3;

    /**
     * Gathers all correct links what are point to another pages not resources like css, js, etc.
     */
    final public static int LINK_VALID_NO_RESOURCES = 4;
}
