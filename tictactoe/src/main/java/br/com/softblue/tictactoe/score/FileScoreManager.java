package br.com.softblue.tictactoe.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.softblue.tictactoe.game.Player;

public class FileScoreManager implements ScoreManager {
	
	private static final String SCORE_FILE = "score.txt";
	private Map<String, Integer> scoreMap = new HashMap<String, Integer>();

	public FileScoreManager() throws ScoreException {
		setup();
	}

	private void setup() throws ScoreException {
		// Verifica se o arquivo existe. Caso não exista, cria
		File file = new File(SCORE_FILE);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new ScoreException(e.getMessage());
			}
		}

		BufferedReader reader = null;

		try {
			// Utiliza um BufferedReader para ler o arquivo
			
			reader = new BufferedReader(new FileReader(file));
			String line;

			while ((line = reader.readLine()) != null) {
				// O método split divide a linha em duas, onde cada token é separado por um '|'
				String[] tokens = line.split("\\|");
				
				// Adiciona a pontuação lida no map
				scoreMap.put(tokens[0].toUpperCase(), Integer.valueOf(tokens[1]));
			}
		
		} catch (IOException e) {
			throw new ScoreException(e.getMessage());
		
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new ScoreException(e.getMessage());
				}
			}
		}
	}
	
	public Integer getScore(Player player) {
		return scoreMap.get(player.getName().toUpperCase());
	}

	public void saveScore(Player player) throws ScoreException {
		// Lê a pontuação do jogador
		Integer score = getScore(player);

		if (score == null) {
			score = 0;
		}

		// Incrementa a pontuação do jogador e recoloca no map
		scoreMap.put(player.getName().toUpperCase(), score + 1);

		// Utiliza um BufferedWriter para armazenar as entradas do map no arquivo
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
			for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
				// Escreve a linha no arquivo no formato '<nome>|<pontuação>'
				writer.write(entry.getKey() + "|" + entry.getValue());
				
				// Insere uma quebra de linha
				writer.newLine();
			}
		
		} catch (IOException e) {
			throw new ScoreException(e.getMessage());
		}
	}
}
