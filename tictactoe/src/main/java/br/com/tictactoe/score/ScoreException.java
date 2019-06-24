package br.com.tictactoe.score;

import br.com.tictactoe.core.TicTacToeException;

@SuppressWarnings("serial")
public class ScoreException extends TicTacToeException {

	public ScoreException(String message) {
		super(message);
	}
}
