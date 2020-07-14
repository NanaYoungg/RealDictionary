package view;

import java.util.ArrayList;

import model.dto.WordPair;

public class EndView {

	public static void wordPairListView(ArrayList<WordPair> wordPairList) {
		for (WordPair wordPair : wordPairList) {
			System.out.println(wordPair);
		}
	}
	
	public static void meaningView(String meaning) {
		System.out.println("¶æ : " + meaning);
	}

	public static void messageView(String message) {
		System.out.println(message);
	}
}
