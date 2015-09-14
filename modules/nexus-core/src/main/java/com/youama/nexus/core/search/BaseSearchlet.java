package com.youama.nexus.core.search;

/**
 * @author David Belicza
 * @since 2015.09.14.
 */
public abstract class BaseSearchlet implements Searchlet {

    protected SearchLogic searchLogic;

    @Override
    public void addSearchLogic(SearchLogic searchLogic) {
        if (this.searchLogic == null) {
            this.searchLogic = searchLogic;
        }
    }

    @Override
    public void ask(SearchQuery searchQuery) {
        searchLogic.input(searchQuery);
        searchLogic.run();
    }

    @Override
    public SearchReply answer() {
        return searchLogic.output();
    }
}
