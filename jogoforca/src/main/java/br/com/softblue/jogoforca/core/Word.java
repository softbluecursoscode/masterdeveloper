package br.com.softblue.jogoforca.core;

import java.util.HashSet;
import java.util.Set;

public class Word {

	private static final char SECRET_CHAR = '_';

	private String originalWord;
	private Set<Character> foundChars = new HashSet<>();

	public Word(String originalWord) {
		this.originalWord = originalWord.toUpperCase();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		char[] letrasArray = originalWord.toCharArray();
		
		for (int i = 0; i < letrasArray.length; i++) {
			char c = letrasArray[i];
			
			if (foundChars.contains(c)) {
				sb.append(c);
			} else {
				sb.append(SECRET_CHAR);
			}
			
			sb.append(" ");
		}

		return sb.toString().trim();
	}
	
	public int size() {
		return originalWord.length();
	}

	public boolean hasChar(char c) {
		if (originalWord.indexOf(c) > -1) {
			foundChars.add(c);
			return true;
		}
		
		return false;
	}
	
	public boolean discovered() {
		char[] chars = originalWord.toCharArray();
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < chars.length; i++) {
			set.add(chars[i]);
		}

		return set.equals(foundChars);
	}
	
	public String getOriginalWord() {
		return originalWord;
	}
}
