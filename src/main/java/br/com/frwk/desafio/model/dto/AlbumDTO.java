package br.com.frwk.desafio.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

public class AlbumDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175571396057464244L;
	
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(name="titulo")
	private String titulo;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private UsuarioDTO usuario;

	@Getter @Setter
	@JsonManagedReference
	private Set<ImagemDTO> imagens = new HashSet<ImagemDTO>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumDTO other = (AlbumDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
