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
package logica.metodo;

public class Main {

	public static void main(String[] args) {

		calcularFibonacci(2, ",");
		System.out.println();
		
		calcularFibonacci(5, "-");
		System.out.println();
		
		
		int[] valores = calcularFibonacci(10);
		
		for (int v : valores) {
			System.out.print(v + " - ");
		}
		
		
		System.out.println();
		System.out.println("Fim!");
	}

	static void calcularFibonacci(int vezes, String separador) {
		for (int cont = 0, i = 0, j = 1; cont < vezes; cont++) {
			System.out.print(i + separador);

			i = i + j;
			j = i - j;
		}
	}
	
	static int[] calcularFibonacci(int vezes) {
		int[] resposta = new int[vezes];
		
		for (int cont = 0, i = 0, j = 1; cont < vezes; cont++) {
			i = i + j;
			j = i - j;
			
			resposta[cont] = i;
		}
		
		return resposta;
	}

}
