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
package collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		Map<Integer, String> meses = new HashMap<>();
		
		meses.put(1, "Janeiro");
		meses.put(2, "Fevereiro");
		meses.put(3, "Março");
		meses.put(12, "Dezembro");
		
		System.out.println(meses);
		
		String m1 = meses.get(1);
		System.out.println(m1);
		
		Set<Integer> numeros = meses.keySet();
		System.out.println(numeros);
		
		Collection<String> nomes = meses.values();
		System.out.println(nomes);
		
		Set<Map.Entry<Integer, String>> entries = meses.entrySet();
		
		for (Map.Entry<Integer, String> entry : entries) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
