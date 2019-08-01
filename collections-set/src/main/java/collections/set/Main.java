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
package collections.set;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
//		Set<Integer> numeros = new LinkedHashSet<>(); //new HashSet<>();
//		
//		numeros.add(1);
//		numeros.add(2);
//		numeros.add(10);
//		numeros.add(22);
//		numeros.add(2);
//		
//		for (int n : numeros) {
//			System.out.println("=> " + n);
//		}
//		
//		System.out.println(numeros.contains(20));
//		
//		
		
		Mes m1 = new Mes("Janeiro", 1);
		Mes m2 = new Mes("Fevereiro", 2);
		Mes m3 = new Mes("Março", 3);
		Mes m4 = new Mes("Abril", 4);
		
		Mes m5 = new Mes("Fevereiro", 2);
		
		Set<Mes> meses = new HashSet<>();
		meses.add(m1); 
		meses.add(m2);
		meses.add(m3);
		meses.add(m4);
		meses.add(m5);
		
		System.out.println(meses);
	}
}
