package br.com.quiz.quiz.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node; // Essencial para obter a Stage atual

import java.io.IOException;

// ... (imports existentes)
import br.com.quiz.quiz.dao.UsuarioDAO;
import br.com.quiz.quiz.dao.UsuarioDBDAO;
import br.com.quiz.quiz.model.Usuario;

public class LoginController {

    private UsuarioDAO usuarioDAO = new UsuarioDBDAO();

    @FXML private TextField txtUsername;
    @FXML private PasswordField pfPassword;
    @FXML private Label lblFeedback;

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = pfPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            lblFeedback.setText("Preencha todos os campos.");
            return;
        }

        try {
            Usuario usuarioLogado = ((UsuarioDBDAO) usuarioDAO).login(username, password); // Usando método login do DBDAO
            if (usuarioLogado != null) {
                Parent root = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaMenu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("POO Gênio Quiz - Menu Principal");
                stage.show();
            } else {
                lblFeedback.setText("Usuário ou senha inválidos.");
            }
        } catch (Exception e) {
            lblFeedback.setText("Erro ao fazer login.");
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirTelaCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaCadastro.fxml"));
            Stage cadastroStage = new Stage();
            cadastroStage.setTitle("POO Gênio Quiz - Criar Conta");
            cadastroStage.setScene(new Scene(root));
            cadastroStage.initModality(Modality.APPLICATION_MODAL);
            cadastroStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Cadastro.");
        }
    }
}