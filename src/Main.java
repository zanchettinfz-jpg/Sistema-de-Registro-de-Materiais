import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializa o banco de dados
        ConexaoDB.criarTabelas();

        boolean rodando = true;
        while (rodando) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    adicionarPerfil();
                    break;
                case "2":
                    listarTodosPerfis();
                    break;
                case "3":
                    buscarPerfil();
                    break;
                case "4":
                    atualizarPerfil();
                    break;
                case "5":
                    deletarPerfil();
                    break;
                case "6":
                    rodando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Exibe o menu principal
     */
    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE ALMOXARIFADO =====");
        System.out.println("1. Adicionar novo perfil/categoria");
        System.out.println("2. Listar todos os perfis");
        System.out.println("3. Buscar perfil por código");
        System.out.println("4. Atualizar perfil");
        System.out.println("5. Deletar perfil");
        System.out.println("6. Sair");
        System.out.println("====================================");
    }

    /**
     * Adiciona um novo perfil ao banco
     */
    private static void adicionarPerfil() {
        System.out.println("\n--- Adicionar Novo Perfil ---");

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = obterInt();

        System.out.print("Linha de Fornecedor: ");
        String linhaFornecedor = scanner.nextLine();

        Perfil perfil = new Perfil(codigo, tamanho, quantidade, linhaFornecedor);
        PerfilDAO.adicionar(perfil);
    }

    /**
     * Lista todos os perfis cadastrados
     */
    private static void listarTodosPerfis() {
        System.out.println("\n--- Lista de Todos os Perfis ---");
        List<Perfil> perfis = PerfilDAO.listarTodos();

        if (perfis.isEmpty()) {
            System.out.println("Nenhum perfil cadastrado.");
        } else {
            for (Perfil perfil : perfis) {
                System.out.println(perfil);
            }
            System.out.println("\nTotal de perfis: " + perfis.size());
        }
    }

    /**
     * Busca um perfil específico pelo código
     */
    private static void buscarPerfil() {
        System.out.println("\n--- Buscar Perfil ---");
        System.out.print("Digite o código do perfil: ");
        String codigo = scanner.nextLine();

        Perfil perfil = PerfilDAO.buscarPorCodigo(codigo);
        if (perfil != null) {
            System.out.println("Perfil encontrado:");
            System.out.println(perfil);
        } else {
            System.out.println("Perfil não encontrado.");
        }
    }

    /**
     * Atualiza um perfil existente
     */
    private static void atualizarPerfil() {
        System.out.println("\n--- Atualizar Perfil ---");
        System.out.print("Digite o código do perfil a atualizar: ");
        String codigo = scanner.nextLine();

        Perfil perfil = PerfilDAO.buscarPorCodigo(codigo);
        if (perfil == null) {
            System.out.println("Perfil não encontrado.");
            return;
        }

        System.out.println("Perfil atual: " + perfil);

        System.out.print("Código (" + perfil.getCodigo() + "): ");
        String novoCodigo = scanner.nextLine();
        if (!novoCodigo.isEmpty()) {
            perfil.setCodigo(novoCodigo);
        }

        System.out.print("Tamanho (" + perfil.getTamanho() + "): ");
        String tamanho = scanner.nextLine();
        if (!tamanho.isEmpty()) {
            perfil.setTamanho(tamanho);
        }

        System.out.print("Quantidade (" + perfil.getQuantidade() + "): ");
        String quantidadeStr = scanner.nextLine();
        if (!quantidadeStr.isEmpty()) {
            try {
                perfil.setQuantidade(Integer.parseInt(quantidadeStr));
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Mantendo a anterior.");
            }
        }

        System.out.print("Linha de Fornecedor (" + perfil.getLinhaFornecedor() + "): ");
        String linhaFornecedor = scanner.nextLine();
        if (!linhaFornecedor.isEmpty()) {
            perfil.setLinhaFornecedor(linhaFornecedor);
        }

        PerfilDAO.atualizar(perfil);
    }

    /**
     * Deleta um perfil
     */
    private static void deletarPerfil() {
        System.out.println("\n--- Deletar Perfil ---");
        System.out.print("Digite o código do perfil a deletar: ");
        String codigo = scanner.nextLine();

        Perfil perfil = PerfilDAO.buscarPorCodigo(codigo);
        if (perfil == null) {
            System.out.println("Perfil não encontrado.");
            return;
        }

        System.out.println("Perfil a deletar: " + perfil);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            PerfilDAO.deletar(perfil.getId());
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Obtém um inteiro do usuário de forma segura
     */
    private static int obterInt() {
        try {
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número inteiro.");
            return obterInt();
        }
    }
}
    