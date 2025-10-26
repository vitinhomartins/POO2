package br.com.quiz.quiz.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MenuController {

    // Metodo 1: Inicia o Quiz (Troca a cena no Stage principal)
    @FXML
    private void iniciarQuiz(ActionEvent event) {
        try {
            // Carrega o FXML do Quiz
            Parent quizParent = FXMLLoader.load(getClass().getResource("/com/example/quiz/TelaQuiz.fxml"));
            Scene quizScene = new Scene(quizParent);

            // Obtém o Stage (Janela) atual
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            // Define a nova cena (troca a tela)
            window.setScene(quizScene);
            window.setTitle("POO Gênio Quiz - O Jogo");
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela do Quiz.");
        }
    }

    // Metodo 2: Abre o Ranking (Janela modal)
    @FXML
    private void abrirRanking(ActionEvent event) {
        abrirJanelaAuxiliar("/com/example/quiz/TelaRanking.fxml", "Ranking de Melhores Tempos");
    }

    // Metodo 3: Abre os Créditos (Janela modal)
    @FXML
    private void abrirCreditos(ActionEvent event) {
        abrirJanelaAuxiliar("/com/example/quiz/TelaCreditos.fxml", "Créditos do Projeto");
    }

    // Metodo auxiliar
    private void abrirJanelaAuxiliar(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));

            // Torna a janela modal
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar FXML: " + fxmlPath);
        }
    }
}