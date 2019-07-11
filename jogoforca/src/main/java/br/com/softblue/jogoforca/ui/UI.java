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
package br.com.softblue.jogoforca.ui;

import java.util.Scanner;

import br.com.softblue.jogoforca.core.InvalidCharacterException;

public class UI {

	public static void print(Object object) {
		System.out.println(object);
	}
	
	public static void printNewLine() {
		System.out.println();
	}
	
	@SuppressWarnings("resource")
	public static char readChar(String text) throws InvalidCharacterException {
		System.out.println(text + " ");
		
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		
		if (line.trim().isEmpty()) {
			throw new InvalidCharacterException("Nenhuma letra foi digitada");
		}
		
		if (line.length() > 1) {
			throw new InvalidCharacterException("Apenas uma letra deve ser digitada");
		}
		
		char c = line.charAt(0);
		
		if (!Character.isLetter(c)) {
			throw new InvalidCharacterException("Apenas letras devem ser digitadas");
		}
		
		return c;
	}
}
