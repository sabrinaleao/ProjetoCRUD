package br.umc.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.umc.crud.model.Produto;
import br.umc.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar(String busca) {
        if (busca == null || busca.isBlank()) {
            return repository.findAll();
        }
        return repository.findByNomeContainingIgnoreCase(busca);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
