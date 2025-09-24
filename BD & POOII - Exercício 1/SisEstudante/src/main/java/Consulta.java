import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consulta {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/curso";
        String usuario = "postgres";
        String senha = "0145";

        String sql = "SELECT * FROM Estudante WHERE estudante_id = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Conex�o realizada com sucesso!");

            // Definindo par�metro da consulta
            statement.setInt(1, 1);

            try (ResultSet result = statement.executeQuery()) {
                System.out.println("COD     IDADE   NOME");
                System.out.println("------  ------  -------");

                if (result.next()) {
                    Integer codigo = result.getInt("estudante_id");
                    Integer idade = result.getInt("idade");
                    String nome = result.getString("nome");
                    System.out.println(codigo + "\t" + idade + "\t" + nome);
                } else {
                    System.out.println("Nenhum estudante encontrado com o ID informado.");
                }
            }

            System.out.println("Consulta realizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            System.out.println("Detalhes do erro: " + e.getMessage());
        }
    }
}