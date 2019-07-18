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
package insider.wrappers;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	
		//int i = 10;
		//Integer integer = Integer.valueOf(i);
		//i = integer.intValue();
		//System.out.println(i);
		
		//double d = null;
		//Double d2 = null;
		
//		List<Integer> list = new ArrayList<>();
//		list.add(Integer.valueOf(1));
//		list.add(Integer.valueOf(2));
//		list.add(Integer.valueOf(3));
//		System.out.println(list);
//		
//		print(Integer.valueOf(20));
		
		//Integer x = 20;
		//x++;
		
		Integer x = Integer.valueOf(20);
		int temp = x.intValue();
		temp++;
		x = Integer.valueOf(temp);
		
		System.out.println(x);
		
		print(true);
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		for (int j : list) {
			System.out.println(j);
		}
		
		Integer abc = Integer.valueOf(50);
		int cba = abc;
		System.out.println(cba);
		
		Integer w = null;
		int w2 = w;
		
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
}
