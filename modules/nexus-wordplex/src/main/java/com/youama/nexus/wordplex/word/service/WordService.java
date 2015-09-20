package com.youama.nexus.wordplex.word.service;

import java.util.List;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.wordplex.word.model.WordModel;
import com.youama.nexus.wordplex.word.model.WordTable;

/**
 * @author David Belicza
 * @since 2015.09.20.
 */
public class WordService extends BaseService<WordModel> {
	
	/**
	 * It saves the word entity but if the word is already exits it increases the weight of the word.
	 */
	@Override
	public WordModel save(WordModel wordModel) {
		WordModel existEntity = findEntityByAttribute(WordModel.class, WordTable.COLUMN_WORD, wordModel.getWord());
		
		if (existEntity != null) {
			// TODO weighting word
			return null;
		}
		
		try {
			return super.save(wordModel);
		} catch (Exception e) {
			Log.warning(e);
		}
		
		return null;
	}
	
	public List<WordModel> getCollection() {
		return super.getCollection(WordModel.class);
	}
}
