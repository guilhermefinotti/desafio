package br.com.frwk.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.frwk.desafio.model.Post;
import br.com.frwk.desafio.model.dto.ComentarioDTO;
import br.com.frwk.desafio.model.dto.PostDTO;
import br.com.frwk.desafio.model.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping (path="/posts")
public class PostController {
	
	@Autowired
	private PostService service;
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<PostDTO>> listar() {

		List<PostDTO> posts = service.listar();
		if(posts == null) {
			posts = new ArrayList<PostDTO>();
		}
		return ResponseEntity.ok(posts);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> buscarPorId(@PathVariable Long id) {
		
		PostDTO postDTO = service.buscarPorId(id);
		
		Post post = service.buscarPostPorId(id);
		postDTO.setImagem(post.getImagem());
		
		return ResponseEntity.ok(postDTO);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/comentarios")
	public ResponseEntity<List<ComentarioDTO>> buscarComentarios(@PathVariable Long id) {
		
		PostDTO postDTO = service.buscarPorId(id);
		return ResponseEntity.ok(new ArrayList<ComentarioDTO>(postDTO.getComentarios()));
	}
	

	/**
	 * 
	 * @param postDTO
	 * @return
	 */
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, consumes ="application/json", produces="application/json")
	public ResponseEntity<PostDTO> criar(@RequestBody PostDTO postDTO) {
		
		postDTO = service.criar(postDTO);
		return ResponseEntity.ok(postDTO);
	}

	
	
	@DeleteMapping("/{id}/comentarios/{idComentario}")
	public ResponseEntity<?> excluirComentario(@PathVariable Long id, @PathVariable Long idComentario) {
		
		service.excluirComentario(idComentario, id);
		return ResponseEntity.ok()
							 .header("Content-Type", "application/json;charset=UTF-8")
							 .body(new Gson().toJson("O comentario id=[" + idComentario + "] foi removido com sucesso"));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		
		service.excluir(id);
		return ResponseEntity.ok()
				.header("Content-Type", "application/json;charset=UTF-8")
				.body(new Gson().toJson("O post id=[" + id + "] foi removido com sucesso"));
	}

}
