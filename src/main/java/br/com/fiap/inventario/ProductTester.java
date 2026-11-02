package br.com.fiap.inventario;

import br.com.fiap.inventario.model.Produto;
import br.com.fiap.inventario.service.ProdutoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Tarefa 10 - Classe driver "ProductTester".
 * Também é a classe principal do Spring Boot, que inicializa o acesso ao banco de dados.
 */
@SpringBootApplication
public class ProductTester {

    public static void main(String[] args) {
        // Tarefa 13a - Scanner chamado no início do método main
        // (Locale.US para aceitar o preço com ponto decimal, ex.: 29.99)
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        // Inicializa o Spring Boot (contexto da aplicação + conexão com o banco de dados)
        ConfigurableApplicationContext contexto = SpringApplication.run(ProductTester.class, args);
        ProdutoService produtoService = contexto.getBean(ProdutoService.class);

        // Tarefa 13b - variáveis locais temporárias para os atributos do Produto
        int tempNumber;
        String tempName;
        int tempQty;
        double tempPrice;

        // Tarefa 20a - variável maxSize
        int maxSize;

        // Tarefas 20b e 20c - prompt + loop do-while até ser inserido um valor >= 0
        do {
            System.out.println("Insira o número de produtos que gostaria de adicionar:");
            System.out.println("Insira \"0\" (zero) se não quiser adicionar mais produtos:");
            if (in.hasNextInt()) {
                maxSize = in.nextInt();
            } else {
                in.next();      // descarta a entrada que não é um número inteiro
                maxSize = -1;
            }
            if (maxSize < 0) {
                System.out.println("Valor incorreto inserido");
            }
        } while (maxSize < 0);

        // Limpa o buffer de entrada (o último valor lido foi numérico)
        in.nextLine();

        // ===================== Tarefas 11 a 14 - seis objetos Produto =====================

        // Tarefas 13c e 13d - p1 criado com os valores digitados pelo usuário (construtor de 4 parâmetros)
        System.out.println("\n=== Cadastro do produto p1 ===");
        System.out.print("Nome do produto: ");
        tempName = in.nextLine();
        tempQty = lerInteiro(in, "Quantidade em estoque: ");
        tempPrice = lerPreco(in, "Preço (ex.: 29.99): ");
        tempNumber = lerInteiro(in, "Número do item: ");
        Produto p1 = new Produto(tempNumber, tempName, tempQty, tempPrice);

        // Tarefa 14c - limpa o buffer de entrada antes de solicitar os valores de p2
        in.nextLine();

        // Tarefa 14a - p2 criado reaproveitando as mesmas variáveis locais
        System.out.println("\n=== Cadastro do produto p2 ===");
        System.out.print("Nome do produto: ");
        tempName = in.nextLine();
        tempQty = lerInteiro(in, "Quantidade em estoque: ");
        tempPrice = lerPreco(in, "Preço (ex.: 29.99): ");
        tempNumber = lerInteiro(in, "Número do item: ");
        Produto p2 = new Produto(tempNumber, tempName, tempQty, tempPrice);

        // Tarefa 11a - dois produtos criados com o construtor padrão (valores definidos pelos setters)
        Produto p3 = new Produto();
        p3.setNumeroItem(3);
        p3.setNome("Software Microsoft Office 365 - Licença 1 ano");
        p3.setQuantidadeEstoque(8);
        p3.setPreco(349.00);

        Produto p4 = new Produto();
        p4.setNumeroItem(4);
        p4.setNome("Caneta Esferográfica Azul - Caixa c/ 50");
        p4.setQuantidadeEstoque(30);
        p4.setPreco(45.50);

        // Tarefa 11b - produtos criados com o construtor de parâmetros
        Produto p5 = new Produto(5, "Caderno Universitário 10 Matérias", 40, 32.90);
        Produto p6 = new Produto(6, "Software Antivírus - Licença 1 ano", 15, 129.90);

        // Tarefa 17 - define p1 como descontinuado antes de exibir os valores
        p1.setAtivo(false);

        // Tarefa 12 - exibe os detalhes de cada produto no console
        System.out.println("\n=============== PRODUTOS p1 a p6 ===============\n");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);

        // Tarefa 24 - grava os seis produtos no banco de dados
        produtoService.salvar(List.of(p1, p2, p3, p4, p5, p6));

        // ===================== Tarefas 21 a 23 - array de produtos =====================

        // Tarefa 21a - nenhum produto a cadastrar
        if (maxSize == 0) {
            System.out.println("Não há produtos!");
        } else {
            // Tarefa 21b - array unidimensional "Produtos" com maxSize elementos
            Produto[] produtos = new Produto[maxSize];

            // Tarefa 22a - percorre o array de 0 até (maxSize - 1)
            for (int i = 0; i < maxSize; i++) {
                // Tarefa 22b - esvazia o buffer de entrada (a última entrada foi numérica)
                in.nextLine();

                // Tarefa 22c - leitura dos campos do produto
                System.out.println("\n=== Cadastro do produto " + (i + 1) + " de " + maxSize + " ===");
                System.out.print("Nome do produto: ");
                tempName = in.nextLine();
                tempQty = lerInteiro(in, "Quantidade em estoque: ");
                tempPrice = lerPreco(in, "Preço (ex.: 29.99): ");
                tempNumber = lerInteiro(in, "Número do item: ");

                // Tarefa 22d - adiciona o novo Produto ao array na posição i (construtor de 4 parâmetros)
                produtos[i] = new Produto(tempNumber, tempName, tempQty, tempPrice);
            }

            // Tarefa 23 - loop for each para exibir cada produto do array
            System.out.println("\n=============== PRODUTOS CADASTRADOS NO ARRAY ===============\n");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }

            // Tarefa 24 - grava os produtos do array no banco de dados
            produtoService.salvar(Arrays.asList(produtos));
        }

        // Tarefa 14e - fecha o Scanner ao terminar de usá-lo
        in.close();

        // Tarefa 24 - confirma o que está gravado no banco de dados
        List<Produto> gravados = produtoService.listarTodos();
        System.out.println("=============== PRODUTOS GRAVADOS NO BANCO DE DADOS (" + gravados.size()
                + ", incluindo execuções anteriores) ===============");
        for (Produto produto : gravados) {
            System.out.printf(Locale.US, "ID %d | Item %d | %s | Qtd %d | R$ %.2f | %s%n",
                    produto.getId(), produto.getNumeroItem(), produto.getNome(),
                    produto.getQuantidadeEstoque(), produto.getPreco(), produto.getStatusProduto());
        }

        contexto.close();
    }

    /**
     * Lê um número inteiro maior ou igual a zero. Se o usuário digitar texto ou um número negativo,
     * mostra "Valor incorreto inserido" e pede de novo (em vez de o programa parar com InputMismatchException).
     * Assim como nextInt(), deixa a quebra de linha no buffer de entrada.
     */
    private static int lerInteiro(Scanner in, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (in.hasNextInt()) {
                int valor = in.nextInt();
                if (valor >= 0) {
                    return valor;
                }
            } else {
                in.next(); // descarta a entrada inválida
            }
            System.out.println("Valor incorreto inserido");
        }
    }

    /**
     * Lê o preço aceitando ponto ou vírgula como separador decimal (29.99 ou 29,99).
     * Assim como nextDouble(), deixa a quebra de linha no buffer de entrada.
     */
    private static double lerPreco(Scanner in, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = in.next();
            if (valor.matches("\\d+([.,]\\d+)?")) {
                return Double.parseDouble(valor.replace(',', '.'));
            }
            System.out.println("Valor incorreto inserido");
        }
    }
}
