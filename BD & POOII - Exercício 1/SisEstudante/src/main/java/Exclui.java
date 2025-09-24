import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Exclui {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/curso";
        String usuario = "postgres";
        String senha = "0145";

        String sql = "DELETE FROM Estudante WHERE estudante_id = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Conex�o realizada com sucesso!");

            // Define o ID do estudante que ser� exclu�do
            int idParaExcluir = 1;
            statement.setInt(1, idParaExcluir);

            // Executa a exclus�o
            int linhasExcluidas = statement.executeUpdate();

            if (linhasExcluidas > 0) {
                System.out.println("Estudante com ID " + idParaExcluir + " exclu�do com sucesso!");
            } else {
                System.out.println("Nenhum estudante encontrado com ID " + idParaExcluir);
            }

        } catch (SQLException e) {
            System.out.println("Erro na opera��o com o banco de dados!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }
}