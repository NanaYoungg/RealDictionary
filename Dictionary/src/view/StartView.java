//branch �ռ� �׽�Ʈ

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
			System.out.println("1. �ܾ� �˻�");
			System.out.println("2. �ܾ� �߰�");
			System.out.println("3. �ܾ� ����");
			System.out.println("4. �ܾ� ����");
			System.out.println("5. �ܾ� ���");
			System.out.println("6. ������");	
			
			String command = in.readLine();
			WordPair wordPair = null;
			String word = null;
			String newMeaning = null;
			
			switch (command) {
			case "1":
				System.out.print("�˻� �ܾ� : ");
				word = in.readLine();
				
				controller.searchMeaning(word);
				break;
				
			case "2":
				wordPair = new WordPair();
				
				System.out.print("���� : ");
				wordPair.setEnglish(in.readLine());
				
				System.out.print("�ѱ� : ");
				wordPair.setKorean(in.readLine());
				
				controller.insertWords(wordPair);
				break;
				
			case "3":
				System.out.print("������ �ܾ� : ");
				word = in.readLine();
				
				controller.deleteWords(word);
				break;
				
			case "4":
				wordPair = new WordPair();
				
				System.out.print("������ �ܾ� : ");
				word = in.readLine();
				
				System.out.print("������ �� : ");
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
				System.out.println("1~6�� ���̷� �����ּ���.");
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
		
		System.out.println("���� ���α׷� ����...");
	}
}
