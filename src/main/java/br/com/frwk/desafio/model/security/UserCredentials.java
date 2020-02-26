package br.com.frwk.desafio.model.security;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Guilherme Finotti Carvalho
 *
 */
public class UserCredentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3064519686925157487L;
	
	@Getter @Setter
	private String usuario;
	
	@Getter @Setter
	private String senha;

}
