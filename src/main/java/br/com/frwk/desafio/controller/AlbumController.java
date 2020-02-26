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

import br.com.frwk.desafio.model.dto.AlbumDTO;
import br.com.frwk.desafio.model.service.AlbumService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping (path="/albuns")
public class AlbumController {
	
	@Autowired
	private AlbumService service;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<AlbumDTO>> listar() {

		List<AlbumDTO> albums = service.listar();
		if(albums == null) {
			albums = new ArrayList<AlbumDTO>();
		}
		return ResponseEntity.ok(albums);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AlbumDTO> buscarPorId(@PathVariable Long id) {
		
		AlbumDTO albumDTO = service.buscarPorId(id);
		
		//Album album = service.buscarAlbumPorId(id);
		//albumDTO.setImagem(album.getImagem());
		
		return ResponseEntity.ok(albumDTO);
	}
	

	/**
	 * 
	 * @param albumDTO
	 * @return
	 */
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, consumes ="application/json", produces="application/json")
	public ResponseEntity<AlbumDTO> criar(@RequestBody AlbumDTO albumDTO) {
		
		albumDTO = service.criar(albumDTO);
		return ResponseEntity.ok(albumDTO);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		
		service.excluir(id);
		return ResponseEntity.ok()
				.header("Content-Type", "application/json;charset=UTF-8")
				.body(new Gson().toJson("O album id=[" + id + "] foi removido com sucesso"));
	}
	

}
