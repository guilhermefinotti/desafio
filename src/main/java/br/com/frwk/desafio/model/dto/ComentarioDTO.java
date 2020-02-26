package br.com.frwk.desafio.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

public class ComentarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7308983702875452912L;
	
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String texto;
	
//	@Getter @Setter
//	private Date data;
	
	@Getter @Setter
	private String dataHoraFormatada;
	
	@Getter @Setter
	private UsuarioDTO usuario;
	
	@Getter @Setter
	@JsonBackReference
	private PostDTO post;

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
		ComentarioDTO other = (ComentarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
