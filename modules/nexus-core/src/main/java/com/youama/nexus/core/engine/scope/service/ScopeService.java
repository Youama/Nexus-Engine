package com.youama.nexus.core.engine.scope.service;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.core.engine.scope.model.ScopeModel;

/**
 * @author David Belicza
 * @since 2015.08.02.
 */
public class ScopeService extends BaseService<ScopeModel> {
	
	@Override
	public ScopeModel add(ScopeModel taskModel) {
		try {
			return super.add(taskModel);
		} catch (Exception e) {
			Log.warning(e);
		}
		
		return null;
	}
}
