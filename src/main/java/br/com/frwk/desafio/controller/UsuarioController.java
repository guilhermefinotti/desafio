package br.com.frwk.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.frwk.desafio.model.Usuario;
import br.com.frwk.desafio.model.dto.UsuarioDTO;
import br.com.frwk.desafio.model.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping (path="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {

		List<UsuarioDTO> usuarios = service.listar();
		if(usuarios == null) {
			usuarios = new ArrayList<UsuarioDTO>();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		
		UsuarioDTO usuarioDTO = service.buscarPorId(id);
		return ResponseEntity.ok(usuarioDTO);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/logado")
	public ResponseEntity<UsuarioDTO> getNomeUsuario() {
		
		String nomeDoUsuario = "";		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			nomeDoUsuario = ((UserDetails)principal).getUsername();
		} else {
			nomeDoUsuario = principal.toString();
		}
		
		Usuario usuario = service.findByUsername(nomeDoUsuario);
		UsuarioDTO dto = service.buildDTO(usuario);
		dto.setSenha("");//TODO: verificar
		
		return ResponseEntity.ok(dto);
	}
	

	/**
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO usuarioDTO) {

		//TODO: validar no cliente, aqui ou no service?
		
		usuarioDTO = service.criar(usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}

}
