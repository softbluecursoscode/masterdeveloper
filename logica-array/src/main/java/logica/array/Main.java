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
package logica.array;

import br.com.softblue.commons.io.Console;

public class Main {

	public static void main(String[] args) {
		System.out.println("Nota 1: ");
		double nota1 = Console.readDouble();
		
		System.out.println("Nota 2: ");
		double nota2 = Console.readDouble();
		
		System.out.println("Nota 3: ");
		double nota3 = Console.readDouble();
		
		System.out.println("Notas: ");
		System.out.println(nota1);
		System.out.println(nota2);
		System.out.println(nota3);
		
		double[] notas = new double[3];
		
		
		for (int i = 0; i < notas.length; i++) {
			System.out.println("Digite a nota " + (i + 1) + ": ");
			notas[i] = Console.readDouble();
		}
		
		for (double nota : notas) {
			System.out.println(nota);
		}
		
		double[] notas2 = { 8.5, 9.0, 7.5 };
		for (double nota : notas2) {
			System.out.println(nota);
		}
	}

}
