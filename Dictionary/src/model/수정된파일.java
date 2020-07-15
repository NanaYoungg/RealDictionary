package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WordPair {
	public static final int ENGLISH = 0;
	public static final int KOREAN = 1;
	public static final int NOTHING = 2;

	private String[] words = new String[2];

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[영어] : ");
		builder.append(words[ENGLISH]);
		builder.append("\t\t");
		builder.append("[한글] : ");
		builder.append(words[KOREAN]);

		return builder.toString();
	}
	public String toStringForLog() {
		StringBuilder builder = new StringBuilder();
		builder.append("[영어] : ");
		builder.append(words[ENGLISH]);
		builder.append(", ");
		builder.append("[한글] : ");
		builder.append(words[KOREAN]);

		return builder.toString();
	}

	public void setEnglish(String english) {
		words[ENGLISH] = english;
	}

	public void setKorean(String korean) {
		words[KOREAN] = korean;
	}

	public static boolean isEnglishCode(int code) {	
		if (('A' <= code && code <= 'Z') ||
		    ('a' <= code && code <= 'z')) {
		    return true;
		    
		    }
		return false;
	}	
	public static int getLanguageType(String word) {
		int code = word.charAt(0);
		int language;

		if (isEnglishCode(code)) {
			language = ENGLISH;
		} else {
			language = KOREAN;
		}

		for (int i = 1; i < word.length(); i++) {
			code = word.charAt(i);

			if (language == ENGLISH && !isEnglishCode(code)) {
				return NOTHING;
			} else if (language == KOREAN && isEnglishCode(code)) {
				return NOTHING;
			}
		}

		return language;
	}
}
