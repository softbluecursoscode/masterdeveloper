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
package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.util.StringUtils;
import lombok.Data;

@Data
public class SearchFilter {

	public enum SearchType {
		Texto, Categoria
	}
	
	public enum Order {
		Taxa, Tempo;
	}
	
	public enum Command {
		EntregaGratis, MaiorTaxa, MenorTaxa, MaiorTempo, MenorTempo;
	}
	
	private String texto;
	private SearchType searchType;
	private Integer categoriaId;
	private Order order = Order.Taxa;
	private boolean asc;
	private boolean entregaGratis;
	
	public void processFilter(String cmdString) {
		
		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);
			
			if (cmd == Command.EntregaGratis) {
				entregaGratis = !entregaGratis;
			
			} else if (cmd == Command.MaiorTaxa) {
				order = Order.Taxa;
				asc = false;
			
			} else if (cmd == Command.MenorTaxa) {
				order = Order.Taxa;
				asc = true;
			
			} else if (cmd == Command.MaiorTempo) {
				order = Order.Tempo;
				asc = false;
			
			} else if (cmd == Command.MenorTempo) {
				order = Order.Tempo;
				asc = true;
			}
		}	
		
		if (searchType == SearchType.Texto) {
			categoriaId = null;
		} else if (searchType == SearchType.Categoria) {
			texto = null;
		}
	}
}
