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
	
	
	@GetMapping(path = "/listar")
	public String listar(Model model) {
		
		List<Mensagem> mensagens = mensagemRepository.findAll(new Sort(Direction.DESC, "dataHora"));
		model.addAttribute("mensagens", mensagens);
		
		model.addAttribute("now", LocalDateTime.now());
		
		return "mensagem-lista";
	}
	
	@GetMapping(path = "/nova")
	public String abrir(Model model) {
		Mensagem mensagem = new Mensagem();
		model.addAttribute("mensagem", mensagem);
		
		List<Usuario> usuarios = usuarioRepository.findAll(new Sort(Direction.ASC, "id"));
		model.addAttribute("usuarios", usuarios);
		
		return "mensagem-nova";
	}
	
	@GetMapping(path = "/curtir")
	public String curtir(@RequestParam("msgId") Integer msgId) {
		Mensagem mensagem = mensagemRepository.findById(msgId).orElseThrow();
		mensagem.curtir();
		mensagemRepository.save(mensagem);
		
		return "redirect:/mensagem/listar";
	}
	
	
	@PostMapping(path = "/criar")
	public String criar(Model model, Mensagem mensagem) {
		if (!mensagem.getTexto().isEmpty()) {
			mensagem.setDataHora(LocalDateTime.now());
			mensagemRepository.save(mensagem);
				
		}
		
		return "redirect:/mensagem/listar";
	}
}
