package com.youama.nexus.wordplex.word.service;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.wordplex.word.model.TextModel;

/**
 * @author David Belicza
 * @since 2015.09.22.
 */
public class TextService extends BaseService<TextModel> {

    @Override
    public TextModel add(TextModel textModel) {
        try {
            return super.add(textModel);
        } catch (Exception e) {
            Log.warning(e);
        }

        return null;
    }
}
