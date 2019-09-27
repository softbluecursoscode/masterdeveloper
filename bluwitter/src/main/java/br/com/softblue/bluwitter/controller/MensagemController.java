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
package br.com.softblue.bluwitter.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.softblue.bluwitter.domain.mensagem.Mensagem;
import br.com.softblue.bluwitter.domain.mensagem.MensagemRepository;
import br.com.softblue.bluwitter.domain.usuario.Usuario;
import br.com.softblue.bluwitter.domain.usuario.UsuarioRepository;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(path = "/nova")
	public String nova(Model model) {
		Mensagem mensagem = new Mensagem();
		model.addAttribute("mensagem", mensagem);
		
		List<Usuario> usuarios = usuarioRepository.findAll(new Sort(Direction.ASC, "nome"));
		model.addAttribute("usuarios", usuarios);
		
		return "mensagem-criar";
	}
	
	@PostMapping(path = "/criar")
	public String criar(Mensagem mensagem) {
		if (!mensagem.getTexto().isEmpty()) {
			mensagem.setDataHora(LocalDateTime.now());
			mensagemRepository.save(mensagem);
		}
		
		return "redirect:/mensagem/listar";
	}
	
	@GetMapping(path = "/listar")
	public String listar(Model model) {
		List<Mensagem> mensagens = mensagemRepository.findAll(new Sort(Direction.DESC, "dataHora"));
		model.addAttribute("mensagens", mensagens);
		
		return "mensagem-listar";
	}
	
	@GetMapping(path = "/curtir")
	public String curtir(
			@RequestParam("msgId") Integer msgId) {
		
		Mensagem mensagem = mensagemRepository.findById(msgId).orElseThrow();
		mensagem.curtir();
		mensagemRepository.save(mensagem);
		return "redirect:/mensagem/listar";
	}
}
