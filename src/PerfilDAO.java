import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO {

    /**
     * Adiciona um novo perfil ao banco de dados
     */
    public static boolean adicionar(Perfil perfil) {
        String sql = "INSERT INTO perfis (codigo, tamanho, quantidade, linha_fornecedor) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perfil.getCodigo());
            pstmt.setString(2, perfil.getTamanho());
            pstmt.setInt(3, perfil.getQuantidade());
            pstmt.setString(4, perfil.getLinhaFornecedor());

            pstmt.executeUpdate();
            System.out.println("Perfil adicionado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar perfil.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lista todos os perfis cadastrados
     */
    public static List<Perfil> listarTodos() {
        List<Perfil> perfis = new ArrayList<>();
        String sql = "SELECT * FROM perfis";

        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Perfil perfil = new Perfil(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("tamanho"),
                        rs.getInt("quantidade"),
                        rs.getString("linha_fornecedor")
                );
                perfis.add(perfil);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar perfis.");
            e.printStackTrace();
        }

        return perfis;
    }

    /**
     * Busca um perfil pelo código (case-insensitive)
     */
    public static Perfil buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM perfis WHERE UPPER(codigo) = UPPER(?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Perfil(
                            rs.getInt("id"),
                            rs.getString("codigo"),
                            rs.getString("tamanho"),
                            rs.getInt("quantidade"),
                            rs.getString("linha_fornecedor")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar perfil.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Atualiza um perfil existente
     */
    public static boolean atualizar(Perfil perfil) {
        String sql = "UPDATE perfis SET codigo = ?, tamanho = ?, quantidade = ?, linha_fornecedor = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perfil.getCodigo());
            pstmt.setString(2, perfil.getTamanho());
            pstmt.setInt(3, perfil.getQuantidade());
            pstmt.setString(4, perfil.getLinhaFornecedor());
            pstmt.setInt(5, perfil.getId());

            pstmt.executeUpdate();
            System.out.println("Perfil atualizado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar perfil.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deleta um perfil pelo ID
     */
    public static boolean deletar(int id) {
        String sql = "DELETE FROM perfis WHERE id = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Perfil deletado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar perfil.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Conta o total de perfis cadastrados
     */
    public static int contar() {
        String sql = "SELECT COUNT(*) FROM perfis";

        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar perfis.");
            e.printStackTrace();
        }

        return 0;
    }
}
