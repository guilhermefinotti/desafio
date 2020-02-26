package br.com.frwk.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.frwk.desafio.model.dto.ComentarioDTO;
import br.com.frwk.desafio.model.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping (path="/comentarios")
public class ComentarioController {
	
	@Autowired
	private PostService service;
	

	@PostMapping
	@RequestMapping(method = RequestMethod.POST,consumes ="application/json",  produces="application/json")
	public ResponseEntity<ComentarioDTO> comentar(@RequestBody ComentarioDTO comentarioDTO/* , @PathVariable Long id */) {
		
		//TODO: validar no cliente, aqui ou no service?
		comentarioDTO = service.criar(comentarioDTO);
		return ResponseEntity.ok(comentarioDTO);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		
		service.excluir(id);
		return ResponseEntity.ok()
							 .header("Content-Type", "application/json;charset=UTF-8")
							 .body(new Gson().toJson("O post id=[" + id + "] foi removido com sucesso"));
	}

}
