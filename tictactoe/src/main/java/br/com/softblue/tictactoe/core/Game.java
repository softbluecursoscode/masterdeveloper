package br.com.softblue.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

import br.com.softblue.tictactoe.Constants;
import br.com.softblue.tictactoe.game.Board;
import br.com.softblue.tictactoe.game.InvalidMoveException;
import br.com.softblue.tictactoe.game.Player;
import br.com.softblue.tictactoe.score.FileScoreManager;
import br.com.softblue.tictactoe.score.ScoreManager;
import br.com.softblue.tictactoe.ui.UI;

public class Game {
	private Board board = new Board();
	private ScoreManager scoreManager;
	private List<Player> players = new ArrayList<>();
	private int currentPlayerIndex = -1;

	public void play() throws TicTacToeException {
		UI.printGameTitle();
		scoreManager = new FileScoreManager();
		
		for (int i = 0; i < Constants.SYMBOL_PLAYERS.length; i++) {
			players.add(createPlayer(i));
		}
		
		boolean gameEnded = false;
		Player winner = null;
		Player currentPlayer = nextPlayer();

		while (!gameEnded) {
			board.print();

			boolean sequenceFound;
			try {
				// Solicita jogada ao jogador
				sequenceFound = currentPlayer.play();
			
			} catch (InvalidMoveException e) {
				// Se a jogada foi inválida, mostra o erro e solicita a jogada novamente
				UI.printText("Erro: " + e.getMessage());
				continue;
			}

			if (sequenceFound) {
				winner = currentPlayer;
				gameEnded = true;

			} else if (board.isFull()) {
				gameEnded = true;
			}

			currentPlayer = nextPlayer();
		}

		UI.printNewLine();

		if (winner == null) {
			UI.printText("O jogo terminou empatado!");
		
		} else {
			UI.printText("O jogador '" + winner.getName() + "' venceu o jogo!");

			// O jogador que venceu tem sua pontuação gravada
			scoreManager.saveScore(winner);
		}

		board.print();
		UI.printText("Fim de Jogo!");
	}
	
	private Player createPlayer(int index) throws TicTacToeException {
		String name = UI.readInput("Nome do jogador " + (index + 1) + ":");
		Player player = new Player(board, name, Constants.SYMBOL_PLAYERS[index]);
		
		UI.printText("O jogador '%s' vai usar o símbolo '%s'", player.getName(), player.getSymbol());
		
		Integer score = scoreManager.getScore(player);
		
		if (score != null) {
			UI.printText("O jogador '%s' já possui %d %s!\n", player.getName(), score, score == 1 ? "vitória" : "vitórias");
		}
		
		return player;
	}
	
	private Player nextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		return players.get(currentPlayerIndex);
	}
}
