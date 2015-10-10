package com.youama.nexus.core.search;

/**
 * A SearchQuery compose and prepare the query what is From the SearchLogic.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
public interface SearchQuery<T> {
    
    /**
     * It resets the prepared query.
     */
    void reset();
    
    /**
     * It composes the queries what had set before.
     * 
     * @return It is true when compose was successfully.
     */
    boolean prepare();
    
    /**
     * It set a query.
     * 
     * @param object Any type of object.
     */
    void setQuery(T object);
    
    /**
     * It set queries.
     * 
     * @param objects Any type of object.
     */
    void setQuery(T[] objects);
    
    /**
     * It retrieves the prepared query.
     * 
     * @return Any type of object.
     */
    T getPreparedQuery();
    
    /**
     * It retrieves the prepared queries.
     * 
     * @return Any type of objects in array.
     */
    T[] getPreparedQueries();

}
