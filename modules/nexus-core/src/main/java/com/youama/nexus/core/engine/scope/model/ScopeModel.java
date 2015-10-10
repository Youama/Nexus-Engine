package com.youama.nexus.core.engine.scope.model;

import javax.persistence.*;

/**
 * Scope entity for defining the language or another type of scopes.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
@Entity
@Table(name = ScopeTable.TABLE)
public class ScopeModel {
    
    @Id
    @Column(name = ScopeTable.COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long scopeId;
    
    @Column(name = ScopeTable.COLUMN_STATUS, columnDefinition = "tinyint default 0", nullable = false)
    private int status;
    
    @Column(name = ScopeTable.COLUMN_TYPE, columnDefinition = "int default 0", nullable = false)
    private int type;
    
    public void setScopeId(long scopeId) {
        this.scopeId = scopeId;
    }
    
    public long getScopeId() {
        return scopeId;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
}
