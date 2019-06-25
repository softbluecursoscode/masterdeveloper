package br.com.softblue.jogoforca.ui;

import java.util.Scanner;

import br.com.softblue.jogoforca.core.InvalidCharacterException;

public class UI {

	@SuppressWarnings("resource")
	public static char readChar(String text) throws InvalidCharacterException {
		System.out.print(text + " ");
		
		Scanner scanner = new Scanner(System.in);
		
		String line = scanner.nextLine();

		if (line.trim().isEmpty()) {
			throw new InvalidCharacterException("Nenhuma letra foi digitada");
		}

		if (line.length() > 1) {
			throw new InvalidCharacterException("Apenas uma letra deve ser digitada");
		}

		char c = line.toUpperCase().charAt(0);

		if (!Character.isLetter(c)) {
			throw new InvalidCharacterException("Apenas letras devem ser digitadas");
		}
		
		return c;
	}
	
	public static void print(Object text) {
		System.out.println(text);
	}
	
	public static void printNewLine() {
		System.out.println();
	}
}
