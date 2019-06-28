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
package logica.metodo2;

import br.com.softblue.commons.io.Console;

public class Main {

	public static void main(String[] args) {

		System.out.print("Digite a temperatura: ");
		double temp = Console.readDouble();

		System.out.print("Qual a conversão? (1 = F -> C, 2 = C -> F): ");
		int opcao = Console.readInt();

		double r;

		if (opcao == 1) {
			r = converterParaCelsius(temp);

		} else if (opcao == 2) {
			r = converterParaFahrenheit(temp);

		} else {
			System.out.println("Opção inválida");
			return;
		}

		System.out.println("A temperatura convertida é: " + r);
	}

	static double converterParaCelsius(double fahrenheit) {
		return (fahrenheit - 32) / 1.8;
	}

	static double converterParaFahrenheit(double celsius) {
		return celsius * 1.8 + 32;
	}
}
