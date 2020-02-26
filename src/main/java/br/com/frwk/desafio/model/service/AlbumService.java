package br.com.frwk.desafio.model.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frwk.desafio.model.Album;
import br.com.frwk.desafio.model.dto.AlbumDTO;
import br.com.frwk.desafio.model.repository.AlbumRepository;

@Component
public class AlbumService {

		
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * 
	 * @return
	 */
	public List<AlbumDTO> listar() {

		List<AlbumDTO> listaResponseDTO = new ArrayList<AlbumDTO>();
		List<Album> listaEntidades = albumRepository.findAll(); // ORDER BY DATA DESC
		
		Type targetListType = new TypeToken<List<AlbumDTO>>() {}.getType();
		listaResponseDTO = modelMapper.map(listaEntidades, targetListType);

		return listaResponseDTO;
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Album buscarAlbumPorId(Long id) {
		
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		
		if (optionalAlbum.isPresent()) {
			return optionalAlbum.get();
		} 
		
		return null;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AlbumDTO buscarPorId(Long id) {

		AlbumDTO albumDTO = null;

		Optional<Album> optionalAlbum = albumRepository.findById(id);

		if (optionalAlbum.isPresent()) {
			albumDTO = buildAlbumDTO(optionalAlbum.get());
		} else {
			//throw new EntityNotFoundException("Nenhum album encontrado. [id=" + id + "]");
		}
		return albumDTO;
	}
	


	/**
	 * 
	 * @param albumDTO
	 * @return
	 */
	public AlbumDTO criar(AlbumDTO albumDTO) {

		AlbumDTO retornoDTO = null;

		if (albumDTO != null) {

			Album album = buildAlbum(albumDTO);
			album = albumRepository.save(album);

			retornoDTO = buildAlbumDTO(album);
		}
		return retornoDTO;
	}
	

	/**
	 * 
	 * @param id
	 */
	public void excluir(Long id) {
		try {
			albumRepository.deleteById(id);
		} catch (Exception ex) {
			//throw new RestApiServiceException("Erro ao remover o album=>" + ex.getMessage());
		}
	}
	
	
	
	/**
	 * Converte uma entidade JPA em DTO
	 * 
	 * @param album
	 * @return
	 */
	private AlbumDTO buildAlbumDTO(Album album) {
		AlbumDTO dto = modelMapper.map(album, AlbumDTO.class);
		return dto;
	}

	/**
	 * Converte um DTO em entidade JPA
	 * 
	 * @param dto
	 * @return
	 */
	public Album buildAlbum(AlbumDTO dto) {
		Album album = modelMapper.map(dto, Album.class);
		return album;
	}


}
