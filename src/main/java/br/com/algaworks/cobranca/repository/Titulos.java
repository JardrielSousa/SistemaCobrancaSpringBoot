package br.com.algaworks.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{

}
