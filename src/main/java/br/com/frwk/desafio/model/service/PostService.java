package br.com.frwk.desafio.model.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.frwk.desafio.model.Comentario;
import br.com.frwk.desafio.model.Post;
import br.com.frwk.desafio.model.Usuario;
import br.com.frwk.desafio.model.dto.ComentarioDTO;
import br.com.frwk.desafio.model.dto.PostDTO;
import br.com.frwk.desafio.model.repository.ComentarioRepository;
import br.com.frwk.desafio.model.repository.PostRepository;
import br.com.frwk.desafio.model.repository.UsuarioRepository;

@Component
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * 
	 * @return
	 */
	public List<PostDTO> listar() {

		List<PostDTO> listaResponseDTO = new ArrayList<PostDTO>();
		//List<Post> listaEntidades = postRepository.findAll();	
		List<Post> listaEntidades = postRepository.findAll(Sort.by(Sort.Direction.DESC, "data")); // ORDER BY DATA DESC
		
		Type targetListType = new TypeToken<List<PostDTO>>() {}.getType();
		listaResponseDTO = modelMapper.map(listaEntidades, targetListType);

//		try {
//			List<Post> listaEntidades = postRepository.findAll();
//
//			Type targetListType = new TypeToken<List<PostDTO>>() {}.getType();
//			listaResponseDTO = modelMapper.map(listaEntidades, targetListType);

//		} catch (Exception ex) {
//			throw new RestApiServiceException("Erro ao listar todos os posts", ex);
//		}
		return listaResponseDTO;
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Post buscarPostPorId(Long id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if (optionalPost.isPresent()) {
			return optionalPost.get();
		} 
		
		return null;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public PostDTO buscarPorId(Long id) {

		PostDTO postDTO = null;

		Optional<Post> optionalPost = postRepository.findById(id);

		if (optionalPost.isPresent()) {
			postDTO = buildPostDTO(optionalPost.get());
		} else {
			//throw new EntityNotFoundException("Nenhum post encontrado. [id=" + id + "]");
		}

		return postDTO;
	}
	


	/**
	 * 
	 * @param postDTO
	 * @return
	 */
	public PostDTO criar(PostDTO postDTO) {

		PostDTO retornoDTO = null;

		if (postDTO != null) {

			Post post = buildPost(postDTO);
			post.setData(new Date());
			post = postRepository.save(post);

			retornoDTO = buildPostDTO(post);
		}
		return retornoDTO;
	}
	
	
	
	/**
	 * 
	 * @param comentarioDTO
	 * @return
	 */
	public ComentarioDTO criar(ComentarioDTO comentarioDTO) {
		
		ComentarioDTO retornoDTO = null;
		
		if (comentarioDTO != null) {
			
			Comentario comentario = new Comentario();
			Post post = null;
			Usuario usuario = null;
			
			
			Optional<Post> optionalPost = postRepository.findById(comentarioDTO.getPost().getId());
			if (optionalPost.isPresent()) {
				post = optionalPost.get();
			}
			
			Optional<Usuario> optionalUsuario = usuarioRepository.findById(comentarioDTO.getUsuario().getId());
			if (optionalUsuario.isPresent()) {
				usuario = optionalUsuario.get();
			}
			
			comentario.setUsuario(usuario);
			comentario.setPost(post);
			comentario.setTexto(comentarioDTO.getTexto());
			comentario.setData(new Date());
			comentario = comentarioRepository.save(comentario);

			retornoDTO = buildComentarioDTO(comentario );
		}
		return retornoDTO;
	}
	
	
	
	/**
	 * 
	 * @param id
	 */
	public void excluir(Long id) {
		try {
			postRepository.deleteById(id);
		} catch (Exception ex) {
			//throw new RestApiServiceException("Erro ao remover o post=>" + ex.getMessage());
		}
	}
	
	
	
	
	/**
	 * 
	 * @param idComentario
	 * @param idPost
	 */
	public void excluirComentario(Long idComentario, Long idPost) {
		try {

			Comentario comentario = null;
			Optional<Comentario> optionalComentario = comentarioRepository.findById(idComentario);
			if (optionalComentario.isPresent()) {
				comentario = optionalComentario.get();
			}
			
			Post post = null;
			Optional<Post> optionalPost = postRepository.findById(idPost);
			if (optionalPost.isPresent()) {
				post = optionalPost.get();
			}
			post.getComentarios().remove(comentario);
			
			postRepository.saveAndFlush(post);
			//comentarioRepository.delete(comentario);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//throw new RestApiServiceException("Erro ao remover o post=>" + ex.getMessage());
		}
	}
	
	
	
	/**
	 * Converte uma entidade JPA em DTO
	 * 
	 * @param post
	 * @return
	 */
	private PostDTO buildPostDTO(Post post) {
		PostDTO dto = modelMapper.map(post, PostDTO.class);
		return dto;
	}

	/**
	 * Converte um DTO em entidade JPA
	 * 
	 * @param dto
	 * @return
	 */
	public Post buildPost(PostDTO dto) {
		Post post = modelMapper.map(dto, Post.class);
		return post;
	}
	
	
	/**
	 * Converte uma entidade JPA em DTO
	 * 
	 * @param post
	 * @return
	 */
	private ComentarioDTO buildComentarioDTO(Comentario comentario) {
		ComentarioDTO dto = modelMapper.map(comentario, ComentarioDTO.class);
		return dto;
	}

}
