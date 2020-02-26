package br.com.frwk.desafio.controller;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.frwk.desafio.model.Post;
import br.com.frwk.desafio.model.dto.PostDTO;
import br.com.frwk.desafio.model.repository.PostRepository;
import br.com.frwk.desafio.model.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/imagens/post")
public class ImagemPostController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2625735118682651294L;
	
	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;



	/**
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<?> salvarImagemPost(@RequestParam("file") MultipartFile uploadfile, @RequestParam String id) {

		PostDTO postDTO = null;

		if (id != null) {
			postDTO = postService.buscarPorId(Long.valueOf(id));
		}
		try {
			
			Post post = postRepository.getOne(postDTO.getId());
			post.setImagem(uploadfile.getBytes());
			postRepository.saveAndFlush(post);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Upload realizado com sucesso - " + uploadfile.getOriginalFilename(),	new HttpHeaders(), HttpStatus.OK);
	}

}
