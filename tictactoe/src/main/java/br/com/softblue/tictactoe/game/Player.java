package br.com.softblue.tictactoe.game;

import br.com.softblue.tictactoe.ui.UI;

public class Player {

	private Board board;
	private String name;
	private char symbol;

	public Player(Board board, String name, char symbol) {
		this.board = board;
		this.name = name.toUpperCase();
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public char getSymbol() {
		return symbol;
	}

	private Move inputMove() throws InvalidMoveException {
		String str =  UI.readInput("Jogador '" + name + "' => ");
		return new Move(str);
	}
	
	public boolean play() throws InvalidMoveException {
		Move move = inputMove();
		return board.play(this, move);
	}
}
