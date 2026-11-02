package br.com.fiap.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Locale;

/**
 * Tarefa 5 - Classe de objeto "Produto".
 * Também é uma entidade JPA, para que possa ser gravada no banco de dados (tarefa 24).
 */
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    // Chave técnica gerada pelo banco (evita conflito caso dois produtos tenham o mesmo número de item)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Tarefa 6 - campos de instância privados
    @Column(name = "NUMERO_ITEM")
    private int numeroItem;            // a) Número do item

    @Column(name = "NOME", length = 100)
    private String nome;               // b) Nome do produto

    @Column(name = "QUANTIDADE_ESTOQUE")
    private int quantidadeEstoque;     // c) Número de unidades em estoque

    @Column(name = "PRECO")
    private double preco;              // d) Preço de cada unidade

    // Tarefa 15a - campo booleano "ativo" com valor padrão true
    @Column(name = "ATIVO")
    private boolean ativo = true;

    // Tarefa 7a - construtor padrão: o compilador inicializa os campos com seus valores padrão
    public Produto() {
    }

    // Tarefa 7b - construtor sobrecarregado com parâmetros para todos os campos
    public Produto(int numeroItem, String nome, int quantidadeEstoque, double preco) {
        this.numeroItem = numeroItem;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
    }

    // Tarefa 8 - getters e setters
    public Long getId() {
        return id;
    }

    public int getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(int numeroItem) {
        this.numeroItem = numeroItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Tarefa 15b - getter/setter do campo ativo
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Tarefa 16 - instrução que converte true/false em "Ativo"/"Descontinuado"
    public String getStatusProduto() {
        if (ativo) {
            return "Ativo";
        } else {
            return "Descontinuado";
        }
    }

    // Tarefa 18 - valor do inventário do item (preço x quantidade), em uma única linha e sem variáveis locais
    public double getValorEstoque() {
        return preco * quantidadeEstoque;
    }

    // Tarefas 9, 15c, 16 e 19 - sobrescrita do toString() da classe Object
    @Override
    public String toString() {
        return "Número do Item : " + numeroItem + "\n"
                + "Nome : " + nome + "\n"
                + "Quantidade em Estoque : " + quantidadeEstoque + "\n"
                + "Preço : R$ " + String.format(Locale.US, "%.2f", preco) + "\n"
                + "Valor do Estoque : R$ " + String.format(Locale.US, "%.2f", getValorEstoque()) + "\n"
                + "Status do Produto : " + getStatusProduto() + "\n";
    }
}
