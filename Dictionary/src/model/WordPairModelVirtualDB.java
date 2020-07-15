package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.dto.WordPair;

public class WordPairModelVirtualDB {
	private static WordPairModelVirtualDB instance = new WordPairModelVirtualDB();
	public static WordPairModelVirtualDB getInstance() {
		return instance;
	}
	
	private ArrayList<WordPair> wordPairList = new ArrayList<>();


	private WordPairModelVirtualDB() {
		BufferedReader in = null;
		String line = null;

		try {
			in = new BufferedReader(new FileReader("word_pair_list.txt"));
			while ((line = in.readLine()) != null) {
				String[] words = line.split(",");

				WordPair wordPair = new WordPair();
				wordPair.setWords(words);

				wordPairList.add(wordPair);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void finish() {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter("word_pair_list.txt"));
			for (WordPair wordPair : wordPairList) {
				String[] words = wordPair.getWords();
				out.write(words[WordPair.ENGLISH] + "," + words[WordPair.KOREAN] + "\n");
				out.flush();// 나중에 지우고도 해보기
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<WordPair> getWordPairList() {
		return wordPairList;
	}

	public void insertWord(WordPair wordPair) {
		wordPairList.add(wordPair);
	}
}
