package com.youama.nexus.wordplex.word.service;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.wordplex.word.model.SentenceModel;

/**
 * @author David Belicza
 * @since 2015.09.22.
 */
public class SentenceService extends BaseService<SentenceModel> {
	
    @Override
    public SentenceModel add(SentenceModel sentenceModel) {
        try {
            return super.add(sentenceModel);
        } catch (Exception e) {
            Log.warning(e);
        }
        
        return null;
    }
}
