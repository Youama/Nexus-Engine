package com.youama.nexus.core.search;

/**
 * @author David Belicza
 * @since 2015.09.14.
 */
public interface Searchlet {

    void addSearchLogic(SearchLogic searchLogic);

    void ask(SearchQuery searchQuery);

    SearchReply answer();
}
