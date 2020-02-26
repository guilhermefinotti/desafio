package br.com.frwk.desafio.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comentario")
public class Comentario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8309523157010270628L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@Column(name="texto")
	private String texto;
	
	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data")
	private Date data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	@Getter @Setter
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id")
	@Getter @Setter
	private Post post;
	
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

}
