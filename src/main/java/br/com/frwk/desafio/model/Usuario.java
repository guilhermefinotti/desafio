package br.com.frwk.desafio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable, UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -124342020439272807L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(name="nome")
	private String nome;
	
	@Getter @Setter
	@Column(name="senha")
	private String senha;
	
	@Getter @Setter
	@Column(name="email")
	private String email;
	
	@Getter @Setter
	@OneToMany(mappedBy="usuario", 
			   cascade=CascadeType.ALL,
			   orphanRemoval=true)
	private Set<Post> posts = new HashSet<Post>();
	
	
	//****************
	// spring security
	//****************
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return null;
		return new ArrayList<GrantedAuthority>();
	}

}

