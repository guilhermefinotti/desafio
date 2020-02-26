package br.com.frwk.desafio.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="post")
public class Post implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7104402857608523479L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(name="titulo")
	private String titulo;
	
	@Getter @Setter
	@Column(name="texto")
	private String texto;
	
	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data")
	private Date data;
		
	
	//******************************
	// TODO: imagens Blob ou url???
	//******************************
	@Lob
	@Getter @Setter
	@Column(name="imagem")
	private byte[] imagem;
	
	/**
	 * 
	 * @return
	 */
	public String getDataHoraFormatada() {
		if(getData() != null) {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(getData());
		} 
		return "";
	}
	
	@Getter @Setter
	@OneToMany(mappedBy="post", 
			   cascade=CascadeType.ALL,
			   fetch = FetchType.LAZY,
			   orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<Comentario>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	@Getter @Setter
	private Usuario usuario;

}
