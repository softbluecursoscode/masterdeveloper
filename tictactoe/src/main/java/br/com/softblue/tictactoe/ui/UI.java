package br.com.softblue.tictactoe.ui;

import br.com.softblue.commons.io.Console;

public class UI {

	public static void printText(Object text) {
		System.out.println(text);
	}
	
	public static void printTextWithNoNewLine(Object text) {
		System.out.print(text);
	}
	
	public static void printText(String text, Object...params) {
		System.out.format(text, params);
		printNewLine();
	}
	
	public static void printNewLine() {
		System.out.println();
	}
	
	public static void printGameTitle() {
		printText("=================");
		printText("| JOGO DA VELHA |");
		printText("=================");
		printNewLine();
	}
	
	public static String readInput(String text) {
		printTextWithNoNewLine(text + " ");
		return Console.readString();
	}
}
