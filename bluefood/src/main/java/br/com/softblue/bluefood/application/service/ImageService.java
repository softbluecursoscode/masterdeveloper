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
package br.com.softblue.bluefood.application.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.util.IOUtils;

@Service
public class ImageService {

	@Value("${bluefood.files.logotipo}")
	private String logotiposDir;
	
	@Value("${bluefood.files.categoria}")
	private String categoriasDir;
	
	@Value("${bluefood.files.comida}")
	private String comidasDir;
	
	
	public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
		try {
			IOUtils.copy(multipartFile.getInputStream(), fileName, logotiposDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
	}
	
	public void uploadComida(MultipartFile multipartFile, String fileName) {
		try {
			IOUtils.copy(multipartFile.getInputStream(), fileName, comidasDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
	}
	
	public byte[] getBytes(String type, String imgName) {
		
		try {
			String dir;
			
			if ("comida".equals(type)) {
				dir = comidasDir;
			
			} else if ("logotipo".equals(type)) {
				dir = logotiposDir;
			
			} else if ("categoria".equals(type)) {
				dir = categoriasDir;
			
			} else {
				throw new Exception(type + " não é um tipo de imagem válido");
			}
			
			return IOUtils.getBytes(Paths.get(dir, imgName));
		
		} catch (Exception e) {
			throw new ApplicationServiceException(e);
		}
	}
}
