package service;

import java.util.ArrayList;

import exception.DuplicateException;
import exception.NotLanguageMatchException;
import exception.NotLanguageTypeException;
import model.WordPairModelVirtualDB;
import model.dto.WordPair;

public class DictionaryService {
	private static DictionaryService instance = new DictionaryService();
	private DictionaryService() {}
	public static DictionaryService getInstance() {
		return instance;
	}
	
	private WordPairModelVirtualDB wordPairData = WordPairModelVirtualDB.getInstance();

	
	public ArrayList<WordPair> getWordPairList() {
		return wordPairData.getWordPairList();
	}

	public String getMeaning(String word) throws NotLanguageTypeException {
		ArrayList<WordPair> wordPairList = wordPairData.getWordPairList();

		int language = WordPair.getLanguageType(word);
		if (language == WordPair.NOTHING) {
			throw new NotLanguageTypeException("영어나 한글 둘 중 하나로만 입력해주세요.");
		}

		for(int i=0; i<wordPairList.size(); i++) {
			String [] words = wordPairList.get(i).getWords();
				
			
		
			if (word.equals(words[language])) {
				return words[(language + 1) % 2]; // 
			}
		}

		return null;
	}

	public void insertWord(WordPair wordPair) throws Exception {
		
		String[] words = wordPair.getWords();
		
		int english = WordPair.getLanguageType(words[WordPair.ENGLISH]);
		int korean = WordPair.getLanguageType(words[WordPair.KOREAN]);
		
		if (english == WordPair.NOTHING || korean == WordPair.NOTHING) {
			throw new NotLanguageTypeException("영어나 한글 둘 중 하나로만 입력해주세요.");
		}
		if (english != WordPair.ENGLISH || korean != WordPair.KOREAN) {
			throw new NotLanguageMatchException("영어에는 영어, 한글에는 한글을 입력해주세요.");
		}
		if (getMeaning(words[WordPair.ENGLISH]) != null ||
			getMeaning(words[WordPair.KOREAN]) != null) {
			throw new DuplicateException("이미 존재하는 영어 또는 한글입니다.");
		}
		
		wordPairData.insertWord(wordPair);
	}

	public boolean deleteWords(String word) throws NotLanguageTypeException {
		ArrayList<WordPair> wordPairList = wordPairData.getWordPairList();

		int language = WordPair.getLanguageType(word);
		if (language == WordPair.NOTHING) {
			throw new NotLanguageTypeException("영어나 한글 둘 중 하나로만 입력해주세요.");
		}

		int size = wordPairList.size();
		for (int i = 0; i < size; i++) {
			String[] words = wordPairList.get(i).getWords();

			if (word.equals(words[language])) {
				wordPairList.remove(i);
				return true;
			}
		}
		
		return false;
	}

	public boolean updateWords(String word, String newMeaning) throws NotLanguageTypeException {
		ArrayList<WordPair> wordPairList = wordPairData.getWordPairList();

		int language = WordPair.getLanguageType(word);
		if (language == WordPair.NOTHING) {
			throw new NotLanguageTypeException("영어나 한글 둘 중 하나로만 입력해주세요.");
		}

		for (WordPair wordPair : wordPairList) {
			String[] words = wordPair.getWords();

			if (word.equals(words[language])) {
				words[(language + 1) % 2] = newMeaning;
				return true;
			}
		}

		return false;
	}
	
	public void finish() {
		wordPairData.finish();
	}
}
