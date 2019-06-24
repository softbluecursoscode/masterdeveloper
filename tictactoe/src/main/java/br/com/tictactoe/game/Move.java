package br.com.tictactoe.game;

public class Move {

	private int i;

	private int j;

	public Move(String moveStr) throws InvalidMoveException {
		try {
			// Separa em dois tokens, onde o delimitador é a ','
			String[] tokens = moveStr.split(",");

			// Armazena os tokens nos atributos
			this.i = Integer.parseInt(tokens[0].trim());
			this.j = Integer.parseInt(tokens[1].trim());

		} catch (Exception e) {
			throw new InvalidMoveException("A jogada é inválida");
		}
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
}
