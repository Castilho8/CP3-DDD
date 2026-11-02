package br.com.fiap.inventario.service;

import br.com.fiap.inventario.model.Produto;
import br.com.fiap.inventario.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Tarefa 24 - Camada de serviço que grava as informações dos produtos no banco de dados.
 */
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public List<Produto> salvar(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
