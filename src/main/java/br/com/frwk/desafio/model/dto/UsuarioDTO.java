package br.com.frwk.desafio.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2229248776658465365L;
	
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String senha;
	
	@Getter @Setter
	private String email;

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
		UsuarioDTO other = (UsuarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
