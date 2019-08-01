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
package collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<String> listaMercado = new ArrayList<>();
		
		listaMercado.add("macarrão");
		listaMercado.add("feijão");
		listaMercado.add("ovo");
		listaMercado.add("sal");
		
		Collections.sort(listaMercado);
		
		System.out.println(listaMercado);
		
		for (String item : listaMercado) {
			System.out.println("Item: " + item);
		}
		
		for (int i = 0; i < listaMercado.size(); i++) {
			String item = listaMercado.get(i);
			System.out.println("Item => " + item);
		}
		
		listaMercado.remove(0);
		System.out.println(listaMercado);
		
		listaMercado.remove("ovo");
		System.out.println(listaMercado);
		
		System.out.println(listaMercado.contains("sal"));
		System.out.println(listaMercado.contains("farinha"));
		
		int posSal = listaMercado.indexOf("sal");
		System.out.println("Posição do sal: " + posSal);
		
		int posFarinha = listaMercado.indexOf("farinha");
		System.out.println("Posição da farinha: " + posFarinha);
	}
}
