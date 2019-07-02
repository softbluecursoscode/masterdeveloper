package br.com.softblue.tictactoe.game;

import br.com.softblue.tictactoe.core.TicTacToeException;

@SuppressWarnings("serial")
public class InvalidMoveException extends TicTacToeException {

	public InvalidMoveException(String message) {
		super(message);
	}
}
