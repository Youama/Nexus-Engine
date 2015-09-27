package com.youama.nexus.core.search;

/**
 * The SearchLogic is for the searching process. The query - what should be searched - arrives through the 
 * <strong>input</strong> method.
 * 
 * @author David Belicza
 * @since 2015.09.14.
 */
public interface SearchLogic {

    /**
     * It sets or prepares the searchQuery object before the searching would be proceed.
     * 
     * @param searchQuery It contains the searching query.
     */
    void input(SearchQuery<?> searchQuery);

    /**
     * It runs the searching.
     */
    void run();

    /**
     * It retrieves the results of the searching process.
     * 
     * @return The SearchReply type object, contains the results.
     */
    SearchReply<?> output();
}
