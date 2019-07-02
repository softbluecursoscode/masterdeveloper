package br.com.softblue.tictactoe.score;

import br.com.softblue.tictactoe.game.Player;

public interface ScoreManager {

	public Integer getScore(Player player);
	
	public void saveScore(Player player) throws ScoreException;
}
