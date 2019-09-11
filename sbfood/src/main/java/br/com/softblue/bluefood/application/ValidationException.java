package br.com.softblue.bluefood.application;

@SuppressWarnings("serial")
public class ValidationException extends Exception {
	
	public ValidationException(String message) {
		super(message);
	}
}
