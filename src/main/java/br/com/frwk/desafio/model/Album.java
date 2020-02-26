package br.com.frwk.desafio.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="album")
public class Album implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8914105403185419341L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(name="titulo")
	private String titulo;
	
	@Getter @Setter
	@Column(name="descricao")
	private String descricao;

	@Getter @Setter
	@OneToMany(mappedBy="album", 
			   cascade=CascadeType.ALL,
			   fetch = FetchType.EAGER,
			   orphanRemoval = true)
	private Set<Imagem> imagens = new HashSet<Imagem>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	@Getter @Setter
	private Usuario usuario;

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
		Album other = (Album) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
