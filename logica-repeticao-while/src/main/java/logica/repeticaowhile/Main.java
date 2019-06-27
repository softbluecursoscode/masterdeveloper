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
package logica.repeticaowhile;

import br.com.softblue.commons.io.Console;

public class Main {

	public static void main(String[] args) {
		
		double nota;
		int cont = 1;
		double soma = 0;
		
		do {
			System.out.println("Digite a Nota " + cont++ + ":");
			nota = Console.readDouble();
			
			if (nota != -1) {
				soma += nota;
				System.out.println("(soma = " + soma + ")");
			}
		
		} while (nota != -1);
		
		System.out.println("Soma = " + soma);
		System.out.println("Cont = " + (cont - 2));
		double media = soma / (cont - 2);
		System.out.println("Média = " + media);
	}
}
