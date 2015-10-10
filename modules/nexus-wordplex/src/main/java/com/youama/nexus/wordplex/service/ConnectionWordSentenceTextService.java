package com.youama.nexus.wordplex.service;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.wordplex.model.ConnectionWordSentenceTextModel;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class ConnectionWordSentenceTextService extends BaseService<ConnectionWordSentenceTextModel> {

    public ConnectionWordSentenceTextModel add(ConnectionWordSentenceTextModel connectionModel) {
        try {
            return super.add(connectionModel);
        } catch (Exception e) {
            Log.warning(e);
            return null;
        }
    }
}
