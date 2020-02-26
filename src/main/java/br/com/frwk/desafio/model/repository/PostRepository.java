package br.com.frwk.desafio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.frwk.desafio.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
