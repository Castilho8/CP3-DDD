package br.com.fiap.inventario.repository;

import br.com.fiap.inventario.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void gravaELeProdutosNoBanco() {
        Produto produto = new Produto(1, "CD Greatest Hits", 25, 29.99);
        produto.setAtivo(false);

        Produto salvo = produtoRepository.save(produto);
        assertNotNull(salvo.getId());

        List<Produto> todos = produtoRepository.findAll();
        assertEquals(1, todos.size());
        assertEquals("CD Greatest Hits", todos.get(0).getNome());
        assertEquals("Descontinuado", todos.get(0).getStatusProduto());
    }
}
