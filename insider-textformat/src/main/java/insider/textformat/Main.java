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
package insider.textformat;

import java.util.List;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		List<Produto> produtos = List.of(
			new Produto(1, "Copo", 3.5),
			new Produto(2, "Caneta", 0.9),
			new Produto(3, "Jogo de Panelas", 130.5)
		);
		
		for (Produto p : produtos) {
			System.out.format(new Locale("pt", "BR"), "%04d\t%-20s\t%.2f%n", p.getId(), p.getNome(), p.getPreco());
		}
		
		double preco = 1356.7;
		
		String precoStr = String.format("%.2f", preco);
		System.out.println(precoStr);
		
	}
}
