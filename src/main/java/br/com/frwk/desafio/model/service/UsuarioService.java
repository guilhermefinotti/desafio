package br.com.frwk.desafio.model.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.frwk.desafio.model.Usuario;
import br.com.frwk.desafio.model.dto.UsuarioDTO;
import br.com.frwk.desafio.model.repository.LoginRepository;
import br.com.frwk.desafio.model.repository.UsuarioRepository;

@Component
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LoginRepository loginRepository;

	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * 
	 * @return
	 */
	public List<UsuarioDTO> listar() {

		List<UsuarioDTO> listaResponseDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> listaEntidades = usuarioRepository.findAll();
		Type targetListType = new TypeToken<List<UsuarioDTO>>() {}.getType();
		listaResponseDTO = modelMapper.map(listaEntidades, targetListType);

//		try {
//			List<Usuario> listaEntidades = usuarioRepository.findAll();
//
//			Type targetListType = new TypeToken<List<UsuarioDTO>>() {}.getType();
//			listaResponseDTO = modelMapper.map(listaEntidades, targetListType);

//		} catch (Exception ex) {
//			throw new RestApiServiceException("Erro ao listar todos os usuarios", ex);
//		}
		return listaResponseDTO;

	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UsuarioDTO buscarPorId(Long id) {

		UsuarioDTO usuarioDTO = null;

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			usuarioDTO = buildDTO(optionalUsuario.get());
		} else {
			//throw new EntityNotFoundException("Nenhum usuario encontrado. [id=" + id + "]");
		}

		return usuarioDTO;
	}
	


	/**
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	public UsuarioDTO criar(UsuarioDTO usuarioDTO) {

		UsuarioDTO retornoDTO = null;

		if (usuarioDTO != null) {

			String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.getSenha());
			
			Usuario usuario = buildEntidade(usuarioDTO);			
			usuario.setSenha(senhaCriptografada);
			usuario = usuarioRepository.save(usuario);

			retornoDTO = buildDTO(usuario);
		}
		return retornoDTO;
	}
	
	
	
	/**
	 * Converte uma entidade JPA em DTO
	 * 
	 * @param usuario
	 * @return
	 */
	public UsuarioDTO buildDTO(Usuario usuario) {
		UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
		return dto;
	}

	/**
	 * Converte um DTO em entidade JPA
	 * 
	 * @param dto
	 * @return
	 */
	private Usuario buildEntidade(UsuarioDTO dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		return usuario;
	}



	/**
	 * 
	 * @param email
	 * @return
	 */
	public Usuario findByUsername(String email) {
		
		Usuario usuario = null;
		UserDetails userDetails = loginRepository.loadUserByUsername(email);
		
		if(userDetails != null) {
			usuario = (Usuario) userDetails;
		}		
		return usuario;
	}


}
