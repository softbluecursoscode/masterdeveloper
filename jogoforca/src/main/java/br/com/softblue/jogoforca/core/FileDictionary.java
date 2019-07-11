/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2019 Softblue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package br.com.softblue.jogoforca.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.softblue.jogoforca.game.GameException;
import br.com.softblue.jogoforca.utils.RandomUtils;

public class FileDictionary extends Dictionary {

	private static final String FILE_NAME = "dicionario.txt";
	
	private List<String> words = new ArrayList<>();
	
	public FileDictionary() {
		load();
	}
	
	private void load() {
		try (Scanner scanner = 
				new Scanner(getClass().getResourceAsStream("/" + FILE_NAME))) {
			
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine().trim();
				words.add(word);
			}
			
			if (words.size() == 0) {
				throw new GameException("A lista de palavras não pode ser vazia");
			}
		}
	}
	
	@Override
	public Word nextWord() {
		int pos = RandomUtils.newRandomNumber(0, words.size());
		return new Word(words.get(pos));
	}

	@Override
	public String getName() {
		return "Arquivo " + FILE_NAME;
	}
}
