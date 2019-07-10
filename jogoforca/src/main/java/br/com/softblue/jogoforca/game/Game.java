package br.com.softblue.jogoforca.game;

import java.util.HashSet;
import java.util.Set;

import br.com.softblue.jogoforca.core.Dictionary;
import br.com.softblue.jogoforca.core.InvalidCharacterException;
import br.com.softblue.jogoforca.core.Word;
import br.com.softblue.jogoforca.ui.UI;

public class Game {
	
	private static final int MAX_ERRORS = 5;

	public void start() {
		UI.print("Bem Vindo ao Jogo da Forca!");
		
		Dictionary dictionary = Dictionary.getInstance();
		Word word = dictionary.nextWord();
		
		UI.print("A palavra tem " + word.size() + " letras");
		
		Set<Character> usedChars = new HashSet<>();
		int errorCount = 0;
		
		while (true) {
			UI.print(word);
			UI.printNewLine();
			
			char c;
			try {
				c = UI.readChar("Digite uma letra:");
				
				if (usedChars.contains(c)) {
					throw new InvalidCharacterException("Esta letra já foi utilizada");
				}
				
				usedChars.add(c);
				
				if (word.hasChar(c)) {
					UI.print("Você acertou uma letra!");
				
				} else {
					errorCount++;
					
					if (errorCount < MAX_ERRORS) {
						UI.print("Você errou! Você ainda pode errar " + (MAX_ERRORS - errorCount) + " vez(es)");
					}
				}
				
				UI.printNewLine();
				
				if (word.discovered()) {
					UI.print("PARABÉNS! Você acertou a palavra correta: " + word.getOriginalWord());
					UI.print("Fim do Jogo!");
					break;
				}
				
				if (errorCount == MAX_ERRORS) {
					UI.print("Você perdeu o jogo! A palavra correta era: " + word.getOriginalWord());
					UI.print("Fim do Jogo!");
					break;
				}
			
			} catch (InvalidCharacterException e) {
				UI.print("Erro: " + e.getMessage());
				UI.printNewLine();
			}
		}
	}
}
