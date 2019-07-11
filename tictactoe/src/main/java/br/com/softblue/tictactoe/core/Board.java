/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2019 Softblue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.Constants;
import br.com.softblue.tictactoe.ui.UI;

public class Board {

	private char[][] matrix;
	
	public Board() {
		matrix = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		clear();
	}
	
	public void clear() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = ' ';
			}
		}
	}
	
	public void print() {
		UI.printNewLine();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				UI.printTextWithNoNewLine(String.valueOf(matrix[i][j]));
				
				if (j < matrix[i].length - 1) {
					UI.printTextWithNoNewLine(" | ");
				}
			}
			
			UI.printNewLine();
			
			if (i < matrix.length - 1) {
				UI.printText("---------");
			}
		}
	}
	
	public boolean isFull() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
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
		
		if (i < 0 || j < 0 || i >= Constants.BOARD_SIZE || j >= Constants.BOARD_SIZE) {
			throw new InvalidMoveException("O intervalo da jogada é inválido");
		}
		
		if (matrix[i][j] != ' ') {
			throw new InvalidMoveException("Esta jogada já foi realizada");
		}
		
		matrix[i][j] = player.getSymbol();
		return checkRows(player) || checkCols(player) || checkDiagonal1(player) || checkDiagonal2(player);
	}
	
	private boolean checkRows(Player player) {
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (checkRow(i, player)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkRow(int i, Player player) {
		char symbol = player.getSymbol();
		
		for (int j = 0; j < Constants.BOARD_SIZE; j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkCols(Player player) {
		for (int j = 0; j < Constants.BOARD_SIZE; j++) {
			if (checkCol(j, player)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkCol(int j, Player player) {
		char symbol = player.getSymbol();
		
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkDiagonal1(Player player) {
		char symbol = player.getSymbol();
		
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (matrix[i][i] != symbol) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkDiagonal2(Player player) {
		char symbol = player.getSymbol();
		int lastLine = Constants.BOARD_SIZE - 1;
		
		for (int i = lastLine, j = 0; i >= 0; i--, j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		
		return true;
	}
}
