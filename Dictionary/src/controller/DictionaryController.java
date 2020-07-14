package controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import exception.NotLanguageMatchException;
import exception.NotLanguageTypeException;
import model.dto.WordPair;
import service.DictionaryService;
import view.EndView;
import view.FailView;

public class DictionaryController {
	private static DictionaryController instance = new DictionaryController();
	private DictionaryController() {}
	public static DictionaryController getInstance() {
		return instance;
	}
	
	private DictionaryService service = DictionaryService.getInstance();
	private Logger logger = Logger.getLogger(DictionaryController.class);
	
	
	public void insertWords(WordPair wordPair) {
		try {
			service.insertWord(wordPair);
			logger.info("<단어 추가> " + wordPair.toStringForLog());
		} catch (Exception e) {
			FailView.failMessageView(e.getMessage());
			logger.debug(e.getMessage());
		}
	}
	
	public void getWordPairList() {
		ArrayList<WordPair> wordPairList = service.getWordPairList();	
		
		if(wordPairList.size() != 0) {
			EndView.wordPairListView(wordPairList);	
		} else {
			EndView.messageView("저장된 단어가 없습니다.");
		}		
	}
	
	public void searchMeaning(String word) {
		try {
			String meaning = service.getMeaning(word);
			
			if (meaning != null) {
				EndView.meaningView(meaning);
			} else {
				EndView.messageView("검색하는 단어는 존재하지 않습니다.");
			}
			
		} catch (NotLanguageTypeException e) {
			FailView.failMessageView(e.getMessage());
			logger.debug(e.getMessage());
		}
		
	}
	
	public void deleteWords(String word) {
		try {
			if (service.deleteWords(word)) {
				EndView.messageView("삭제 완료하였습니다.");
				logger.info("<단어 삭제> " + word);
			} else {
				
			}
			
		} catch (NotLanguageTypeException e) {
			FailView.failMessageView(e.getMessage());
			logger.debug(e.getMessage());
		}
	}

	public void updateWords(String word, String newMeaning) {
		try {
			if (service.updateWords(word, newMeaning)) {
				EndView.messageView("수정 완료하였습니다.");
				logger.info("<단어 수정> " + word + "의 새로운 뜻 : " + newMeaning);
			} else {
				EndView.messageView("수정하고자 하는 단어가 없습니다.");
			}

		} catch (NotLanguageTypeException e) {
			FailView.failMessageView(e.getMessage());
			logger.debug(e.getMessage());
		}
	}
	
	public void finish() {
		service.finish();
	}
}
