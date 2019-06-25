package br.com.softblue.jogoforca.game;

@SuppressWarnings("serial")
public class GameException extends RuntimeException {

	public GameException(String message) {
		super(message);
	}
	
	public GameException(String message, Throwable cause) {
		super(message, cause);
	}
}
