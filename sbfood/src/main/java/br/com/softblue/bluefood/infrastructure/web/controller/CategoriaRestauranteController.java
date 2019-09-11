package br.com.softblue.bluefood.infrastructure.web.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.softblue.bluefood.util.IOUtils;

@Controller
@RequestMapping
public class CategoriaRestauranteController {

	@Value("${bluefood.upload.categorias}")
	private String categoriasDir;
	
	@GetMapping(path = "/categoria/img/{imgName}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getImageBytes(@PathVariable("imgName") String imgName)  {
		try {
			return IOUtils.getBytes(Paths.get(categoriasDir, imgName));
		} catch (IOException e) {
			// TODO: Trocar exceção
			throw new RuntimeException(e);
		}
	}
}