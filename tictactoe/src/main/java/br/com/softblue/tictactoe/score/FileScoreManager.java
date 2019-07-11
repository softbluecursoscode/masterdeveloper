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
package br.com.softblue.tictactoe.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.softblue.tictactoe.core.Player;

public class FileScoreManager implements ScoreManager {

	private static final Path SCORE_FILE = Path.of("score.txt");
	private Map<String, Integer> scoreMap = new HashMap<>();
	
	public FileScoreManager() throws IOException {
		setup();
	}
	
	private void setup() throws IOException {
		if (!Files.exists(SCORE_FILE)) {
			Files.createFile(SCORE_FILE);
		}
		
		try (BufferedReader reader = Files.newBufferedReader(SCORE_FILE)) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				scoreMap.put(tokens[0], Integer.parseInt(tokens[1]));
			}
		}

	}

	@Override
	public Integer getScore(Player player) {
		return scoreMap.get(player.getName().toUpperCase());
	}

	@Override
	public void saveScore(Player player) throws IOException {
		Integer score = getScore(player);
		
		if (score == null) {
			score = 0;
		}
		
		scoreMap.put(player.getName().toUpperCase(), score + 1);
		
		try (BufferedWriter writer = Files.newBufferedWriter(SCORE_FILE)) {
			Set<Map.Entry<String, Integer>> entries = scoreMap.entrySet();
			
			for (Map.Entry<String, Integer> entry : entries) {
				String name = entry.getKey().toUpperCase();
				Integer s = entry.getValue();
				writer.write(name + "|" + s);
				writer.newLine();
			}
		}
	}
}
