package com.youama.nexus.core.model.scope;

/**
 * @author David Belicza
 * @since 0.1.0
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
