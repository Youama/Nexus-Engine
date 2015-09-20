package com.youama.nexus.core.engine.scope.model;

import javax.persistence.*;

/**
 * Scope entity for defining the language or another type of scopes.
 * 
 * @author David Belicza
 * @since 2015.09.20.
 */
@Entity
@Table(name = "Scope")
public class ScopeModel {

	public final static int TYPE_DEFAULT = 0;
	
	public final static int STATUS_ENABLED = 1;
	public final static int STATUS_DISABLED = 0;
	
	@Id
    @Column(name="scopeId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long scopeId;
	
	@Column(name="status", columnDefinition = "tinyint default 0", nullable = false)
	private int status;
	
	@Column(name="type", columnDefinition = "int default 0", nullable = false)
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
