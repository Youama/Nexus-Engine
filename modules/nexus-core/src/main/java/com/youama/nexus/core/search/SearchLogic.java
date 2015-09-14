package com.youama.nexus.core.search;

/**
 * @author David Belicza
 * @since 2015.09.14.
 */
public interface SearchLogic {

    void input(SearchQuery searchQuery);

    void run();

    SearchReply output();
}
