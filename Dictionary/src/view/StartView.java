//branch 합성 테스트

package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.DictionaryController;
import model.dto.WordPair;

public class StartView {
	
	public static void run(BufferedReader in) throws IOException {
		DictionaryController controller = DictionaryController.getInstance();
				
		boolean isRun = true;
		while (isRun) {
			System.out.println("1. 단어 검색");
			System.out.println("2. 단어 추가");
			System.out.println("3. 단어 삭제");
			System.out.println("4. 단어 수정");
			System.out.println("5. 단어 목록");
			System.out.println("6. 나가기");	
			
			String command = in.readLine();
			WordPair wordPair = null;
			String word = null;
			String newMeaning = null;
			
			switch (command) {
			case "1":
				System.out.print("검색 단어 : ");
				word = in.readLine();
				
				controller.searchMeaning(word);
				break;
				
			case "2":
				wordPair = new WordPair();
				
				System.out.print("영어 : ");
				wordPair.setEnglish(in.readLine());
				
				System.out.print("한글 : ");
				wordPair.setKorean(in.readLine());
				
				controller.insertWords(wordPair);
				break;
				
			case "3":
				System.out.print("삭제할 단어 : ");
				word = in.readLine();
				
				controller.deleteWords(word);
				break;
				
			case "4":
				wordPair = new WordPair();
				
				System.out.print("수정할 단어 : ");
				word = in.readLine();
				
				System.out.print("수정할 뜻 : ");
				newMeaning = in.readLine();
				
				controller.updateWords(word, newMeaning);
				break;
				
			case "5":
				controller.getWordPairList();
				break;
				
			case "6":
				isRun = false;
				controller.finish();
				break;
				
			default:
				System.out.println("1~6번 사이로 눌러주세요.");
				break;
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			StartView.run(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("사전 프로그램 종료...");
	}
}
