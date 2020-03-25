package br.com.softblue.bluetasks.domain.task;

@SuppressWarnings("serial")
public class DuplicateTaskException extends Exception {

	public DuplicateTaskException(String message) {
		super(message);
	}
}
