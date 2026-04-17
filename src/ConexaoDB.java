import java.sql.*;

public class ConexaoDB {
    private static final String URL = "jdbc:sqlite:almoxarifado.db";

    /**
     * Estabelece conexão com o banco de dados SQLite
     */
    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            System.out.println("Conectado ao banco de dados com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite não encontrado. Certifique-se de adicionar o JAR do SQLite ao projeto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Cria as tabelas necessárias se não existirem
     */
    public static void criarTabelas() {
        String sqlPerfil = "CREATE TABLE IF NOT EXISTS perfis (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo TEXT NOT NULL," +
                "tamanho TEXT NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "linha_fornecedor TEXT NOT NULL" +
                ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPerfil);
            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas.");
            e.printStackTrace();
        }
    }

    /**
     * Fecha a conexão com o banco de dados
     */
    public static void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão.");
            e.printStackTrace();
        }
    }
}
