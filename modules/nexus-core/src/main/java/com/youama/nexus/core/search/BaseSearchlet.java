package com.youama.nexus.core.search;

/**
 * The Searchlet is a standard way to implement any searching methodology inside Nexus Engine. It always has to get the
 * query by method <strong>ask</strong> and give a response by method <strong>answer</strong>. Before it should
 * proceed method ask the <strong>SearchLogic</strong> object must be set by the method <strong>addSearchLogic</strong>.
 *  
 * @author David Belicza
 * @since 0.1.0
 */
public abstract class BaseSearchlet implements Searchlet {

    /**
     * The SearchLogic object contains the searching methodology.
     */
    protected SearchLogic searchLogic;

    /**
     * It sets the SearchLogic type object to the property.
     * 
     * @param searchLogic This object proceed the searching process or processes.
     */
    @Override
    public void addSearchLogic(SearchLogic searchLogic) {
        if (this.searchLogic == null) {
            this.searchLogic = searchLogic;
        }
    }

    /**
     * It pass the SearchQuery object into the SearchLogic object and runs the SearchLogic.
     * 
     * @param searchQuery This object contains the search query.
     */
    @Override
    public void ask(SearchQuery<?> searchQuery) {
        searchLogic.input(searchQuery);
        searchLogic.run();
    }

    /**
     * It retrieves the searching response.
     * 
     * @return The searching response in SearchReply type object.
     */
    @Override
    public SearchReply<?> answer() {
        return searchLogic.output();
    }
}
