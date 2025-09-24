import java.sql.*;
import java.util.Scanner;

public class Atualiza {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/curso";
        String usuario = "postgres";
        String senha = "0145";
        String sql = "UPDATE estudante SET nome = ?, idade = ? WHERE estudante_id = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conexão realizada com sucesso!");


            System.out.print("Digite o ID do estudante a ser alterado: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o novo nome do estudante: ");
            String name = scanner.nextLine();

            System.out.print("Digite a nova idade do estudante: ");
            int age = scanner.nextInt();

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, id);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Estudante alterado com sucesso!");
            } else {
                System.out.println("Nenhum estudante encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro na operação com o banco de dados!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }
}
