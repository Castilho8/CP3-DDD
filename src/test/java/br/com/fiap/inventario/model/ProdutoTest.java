package br.com.fiap.inventario.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProdutoTest {

    @Test
    void produtoNovoComecaAtivo() {
        Produto produto = new Produto();
        assertTrue(produto.isAtivo());
        assertEquals("Ativo", produto.getStatusProduto());
    }

    @Test
    void calculaValorDoEstoque() {
        Produto produto = new Produto(1, "CD Greatest Hits", 25, 29.99);
        assertEquals(749.75, produto.getValorEstoque(), 0.001);
    }

    @Test
    void toStringNoFormatoPedido() {
        Produto produto = new Produto(1, "CD Greatest Hits", 25, 29.99);
        produto.setAtivo(false);
        String esperado = """
                Número do Item : 1
                Nome : CD Greatest Hits
                Quantidade em Estoque : 25
                Preço : R$ 29.99
                Valor do Estoque : R$ 749.75
                Status do Produto : Descontinuado
                """;
        assertEquals(esperado, produto.toString());
    }
}
