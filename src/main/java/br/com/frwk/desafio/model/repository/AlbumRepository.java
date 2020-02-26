package br.com.frwk.desafio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.frwk.desafio.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
