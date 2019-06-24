package br.com.tictactoe.game;

import br.com.tictactoe.Constants;
import br.com.tictactoe.ui.UI;

public class Board {

	private char[][] matrix;

	public Board() {
		matrix = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

		// Zera todos os elementos da matriz
		clear();
	}

	public void clear() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = ' ';
			}
		}
	}

	public void print() {
		UI.printNewLine();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				UI.printTextWithNoNewLine(matrix[i][j]);
				if (j < matrix[0].length - 1) {
					UI.printTextWithNoNewLine(" | ");
				}
			}
			UI.printNewLine();

			if (i < matrix.length - 1) {
				UI.printText("---------");
			}
		}
		
		UI.printNewLine();
	}

	public boolean isFull() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean play(Player player, Move move) throws InvalidMoveException {
		int i = move.getI();
		int j = move.getJ();

		// Verifica se a jogada está fora dos limites da matriz
		if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
			throw new InvalidMoveException("A jogada é inválida");
		}

		// Verifica se a jogada já não foi realizada
		if (matrix[i][j] != ' ') {
			throw new InvalidMoveException("Esta jogada já foi realizada");
		}

		// Coloca o símbolo na posição especificada
		matrix[i][j] = player.getSymbol();

		// Verifica se houve uma sequência
		return checkRows() || checkColumns() || checkDiagonal1() || checkDiagonal2();
	}

	
	private boolean checkRows() {
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (checkRow(i)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkRow(int i) {
		char symbol = matrix[i][0];
		if (symbol == ' ') {
			return false;
		}
		
		for (int j = 1; j < Constants.BOARD_SIZE; j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkColumns() {
		for (int j = 0; j < Constants.BOARD_SIZE; j++) {
			if (checkColumn(j)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkColumn(int j) {
		char symbol = matrix[0][j];
		if (symbol == ' ') {
			return false;
		}
		
		for (int i = 1; i < Constants.BOARD_SIZE; i++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkDiagonal1() {
		char symbol = matrix[0][0];
		if (symbol == ' ') {
			return false;
		}
		
		for (int i = 1; i < Constants.BOARD_SIZE; i++) {
			if (matrix[i][i] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkDiagonal2() {
		int lastLine = Constants.BOARD_SIZE - 1;
		char symbol = matrix[lastLine][0];
		if (symbol == ' ') {
			return false;
		}
		
		int i, j;
		for (i = lastLine - 1, j = 1; i >= 0; i--, j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
}
