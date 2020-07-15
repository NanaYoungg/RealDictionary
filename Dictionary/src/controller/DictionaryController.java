package controller;
//test
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
			logger.info("<�ܾ� �߰�> " + wordPair.toStringForLog());
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
			EndView.messageView("����� �ܾ �����ϴ�.");
		}		
	}
	
	public void searchMeaning(String word) {
		try {
			String meaning = service.getMeaning(word);
			
			if (meaning != null) {
				EndView.meaningView(meaning);
			} else {
				EndView.messageView("�˻��ϴ� �ܾ�� �������� �ʽ��ϴ�.");
			}
			
		} catch (NotLanguageTypeException e) {
			FailView.failMessageView(e.getMessage());
			logger.debug(e.getMessage());
		}
		
	}
	
	public void deleteWords(String word) {
		try {
			if (service.deleteWords(word)) {
				EndView.messageView("���� �Ϸ��Ͽ����ϴ�.");
				logger.info("<�ܾ� ����> " + word);
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
				EndView.messageView("���� �Ϸ��Ͽ����ϴ�.");
				logger.info("<�ܾ� ����> " + word + "�� ���ο� �� : " + newMeaning);
			} else {
				EndView.messageView("�����ϰ��� �ϴ� �ܾ �����ϴ�.");
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
