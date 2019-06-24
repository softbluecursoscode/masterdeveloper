package br.com.tictactoe.score;

import br.com.tictactoe.game.Player;

public interface ScoreManager {

	public Integer getScore(Player player);
	
	public void saveScore(Player player) throws ScoreException;
}
