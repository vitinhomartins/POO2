package br.com.quiz.quiz.Controller;

import br.com.quiz.quiz.dao.UsuarioDAO;
import br.com.quiz.quiz.dao.UsuarioDBDAO;
import br.com.quiz.quiz.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CadastroController {

    private UsuarioDAO usuarioDAO = new UsuarioDBDAO();

    @FXML private TextField txtCadUsername;
    @FXML private PasswordField pfCadPassword;
    @FXML private PasswordField pfCadConfirmPassword;
    @FXML private Label lblFeedback;

    @FXML
    public void handleCadastre(ActionEvent event) {
        lblFeedback.setText("");
        String username = txtCadUsername.getText();
        String password = pfCadPassword.getText();
        String confirmPassword = pfCadConfirmPassword.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            lblFeedback.setText("Preencha todos os campos.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            lblFeedback.setText("As senhas não coincidem!");
            return;
        }

        try {
            Usuario novoUsuario = new Usuario(0, username, password, 0.0f); // ID será gerado pelo DB
            usuarioDAO.insere(novoUsuario);
            lblFeedback.setText("Conta criada com sucesso!");
            lblFeedback.setStyle("-fx-text-fill: green;");

            txtCadUsername.clear();
            pfCadPassword.clear();
            pfCadConfirmPassword.clear();

            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                javafx.application.Platform.runLater(() -> {
                    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
                });
            }).start();
        } catch (Exception e) {
            lblFeedback.setText("Erro: Nome de usuário indisponível ou falha no cadastro.");
            lblFeedback.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}