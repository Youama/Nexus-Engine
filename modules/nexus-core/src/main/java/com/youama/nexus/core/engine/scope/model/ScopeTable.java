package com.youama.nexus.core.engine.scope.model;

/**
 * @author David Belicza
 * @since 2015.09.20.
 */
public interface ScopeTable {

    public final static String TABLE = "Scope";
    
    public final static String COLUMN_ID = "scopeId";
    public final static String COLUMN_STATUS = "status";
    public final static String COLUMN_TYPE = "type";
    
    public final static int VALUE_TYPE_DEFAULT = 0;
    
    public final static int VALUE_STATUS_ENABLED = 1;
    public final static int VALUE_STATUS_DISABLED = 0;
}
