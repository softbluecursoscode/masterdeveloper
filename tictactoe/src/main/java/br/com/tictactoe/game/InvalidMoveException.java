package br.com.tictactoe.game;

import br.com.tictactoe.core.TicTacToeException;

@SuppressWarnings("serial")
public class InvalidMoveException extends TicTacToeException {

	public InvalidMoveException(String message) {
		super(message);
	}
}
