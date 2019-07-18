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
package insider.errordetect;


public class Aplicacao {

	public static void main(String[] args) {

		ContatoPF c1 = new ContatoPF();
		c1.setNome("José");
		c1.setCpf("000.456.234-07");
		
		//c1.setEndereco(new Endereco());
		
		c1.getEndereco().setRua("R. dos Limões");
		c1.getEndereco().setNumero("100");
		c1.getEndereco().setCidade("Ribeirão Distante");
		c1.getEndereco().setEstado("Passarinho");
		
		Agenda a = new Agenda();
		a.setContato1(c1);
		
		imprimirNomes(a);
		imprimirCnpj(a);
		
		System.out.println("Contato definido");
	}
	
	private static void imprimirNomes(Agenda a) {
		if (a.getContato1() != null) {
			System.out.println(a.getContato1().getNome());
		}
		
		if (a.getContato2() != null) {
			System.out.println(a.getContato2().getNome());
		}
		
		if (a.getContato3() != null) {
			System.out.println(a.getContato3().getNome());
		}
	}
	
	private static void imprimirCnpj(Agenda a) {
		if (a.getContato1() != null && a.getContato1() instanceof ContatoPJ) {
			ContatoPJ c = (ContatoPJ) a.getContato1();
			System.out.println(c.getCnpj());
		}
		
		if (a.getContato2() != null) {
			ContatoPJ c = (ContatoPJ) a.getContato2();
			System.out.println(c.getCnpj());
		}
		
		if (a.getContato3() != null) {
			ContatoPJ c = (ContatoPJ) a.getContato3();
			System.out.println(c.getCnpj());
		}
	}
}
