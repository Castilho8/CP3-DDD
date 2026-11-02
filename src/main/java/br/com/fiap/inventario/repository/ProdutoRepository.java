package br.com.fiap.inventario.repository;

import br.com.fiap.inventario.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Tarefa 24 - Repositório Spring Data JPA responsável por gravar e ler os produtos no banco de dados.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
