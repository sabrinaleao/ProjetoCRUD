package br.umc.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.umc.crud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Query Method: busca por parte do nome, ignorando maiusculas/minusculas
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
