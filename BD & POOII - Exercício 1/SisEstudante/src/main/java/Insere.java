import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Insere {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/curso";
        String usuario = "postgres";
        String senha = "0145";

        // RETURNING retorna o estudante_id gerado
        String sql = "INSERT INTO estudante (nome, idade) VALUES (?, ?) RETURNING estudante_id";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Conex�o realizada com sucesso!");

            statement.setString(1, "Vitor");
            statement.setInt(2, 20);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int idGerado = rs.getInt("estudante_id");
                    System.out.println("Estudante inserido com ID: " + idGerado);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na opera��o com o banco de dados!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }
}