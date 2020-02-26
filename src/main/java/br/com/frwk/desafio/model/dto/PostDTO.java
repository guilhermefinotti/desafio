package br.com.frwk.desafio.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

public class PostDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4731570891101066668L;

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String titulo;
	
	@Getter @Setter
	private String texto;
		
	@Getter @Setter
	private UsuarioDTO usuario;
	
	@Getter @Setter
	private String dataHoraFormatada;
	
	@Getter @Setter
	private byte[] imagem;
	
//	@Getter @Setter
//	private Date data;
	
	//******************************
	// TODO: imagens Blob ou url???
	//******************************

	@Getter @Setter
	@JsonManagedReference
	private Set<ComentarioDTO> comentarios = new HashSet<ComentarioDTO>();

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
		PostDTO other = (PostDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
