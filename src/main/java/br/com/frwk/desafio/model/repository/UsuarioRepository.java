package br.com.frwk.desafio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.frwk.desafio.model.Usuario;

/**
 * 
 * @author Guilherme Finotti Carvalho
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
