package com.youama.nexus.core.search;

/**
 * A SearchReply compose and prepare the the reply what is from the SearchLogic.
 * 
 * @author David Belicza
 * @since 2015.09.14.
 */
public interface SearchReply<T> {
	
	/**
	 * It resets the prepared reply.
	 */
	void reset();
	
	/**
	 * It composes the replies what had set before.
	 * 
	 * @return It is true when compose was successfully.
	 */
	boolean prepare();
	
	/**
	 * It set a reply.
	 * 
	 * @param object Any type of object.
	 */
	void setReply(T object);
	
	/**
	 * It set replies.
	 * 
	 * @param objects Any type of object.
	 */
	void setReply(T[] objects);
	
	/**
	 * It retrieves the prepared reply.
	 * 
	 * @return Any type of object.
	 */
	T getPreparedReply();
	
	/**
	 * It retrieves the prepared replies.
	 * 
	 * @return Any type of objects in array.
	 */
	T[] getPreparedReplies();
}
