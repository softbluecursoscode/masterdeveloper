package br.com.softblue.jogoforca.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.softblue.jogoforca.game.GameException;
import br.com.softblue.jogoforca.utils.RandomUtils;

public class Dictionary {
	private static final String FILE_NAME = "dicionario.txt";
	
	private static Dictionary instance;
	
	private List<String> words = new ArrayList<>();

	private Dictionary() {
		load();
	}

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}
		return instance;
	}

	private void load() {
		try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/" + FILE_NAME))) {
			while (scanner.hasNextLine()) {
				words.add(scanner.nextLine().trim());
			}
			
			if (words.size() == 0) {
				throw new GameException("A lista de palavras não pode estar vazia");
			}
		}
	}
	
	public Word nextWord() {
		int pos = RandomUtils.newRandomNumber(0, words.size());
		Word word = new Word(words.get(pos));
		return word;
	}
}
