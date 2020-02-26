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

import br.com.frwk.desafio.model.Album;
import br.com.frwk.desafio.model.Imagem;
import br.com.frwk.desafio.model.dto.AlbumDTO;
import br.com.frwk.desafio.model.repository.AlbumRepository;
import br.com.frwk.desafio.model.service.AlbumService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/imagens/album")
public class ImagemAlbumController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2625735118682651294L;

	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private AlbumRepository albumRepository;
	

	/**
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<?> salvarImagemAlbum(@RequestParam("file") MultipartFile uploadfile, @RequestParam String id) {
		
		AlbumDTO albumDTO = null;
		
		if (id != null) {
			albumDTO = albumService.buscarPorId(Long.valueOf(id));
		}
		try {
			
			Album album = albumService.buscarAlbumPorId(albumDTO.getId());
			Imagem imagem = new Imagem();
			imagem.setAlbum(album);
			imagem.setArquivo(uploadfile.getBytes());
			album.getImagens().add(imagem);

			albumRepository.saveAndFlush(album);
			
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Upload realizado com sucesso - " + uploadfile.getOriginalFilename(),	new HttpHeaders(), HttpStatus.OK);
	}

}
