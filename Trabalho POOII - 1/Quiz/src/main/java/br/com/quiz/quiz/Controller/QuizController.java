package br.com.quiz.quiz.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;

import br.com.quiz.quiz.dao.PerguntaDAO;
import br.com.quiz.quiz.dao.PerguntaDBDAO;
import br.com.quiz.quiz.model.Pergunta;

import java.io.IOException;
import java.util.List;

public class QuizController {

    private PerguntaDAO perguntaDAO = new PerguntaDBDAO();
    private List<Pergunta> perguntas;
    private int perguntaAtual = 0;
    private Timeline cronometro;
    private double tempoRestante = 60.0; // 60 segundos por pergunta, ajuste conforme necessário

    @FXML private Label lblTemporizador;
    @FXML private Label lblNumeroPergunta;
    @FXML private TextArea txtPergunta;
    @FXML private Button btnAlternativaA;
    @FXML private Button btnAlternativaB;
    @FXML private Button btnAlternativaC;
    @FXML private Button btnAlternativaD;
    @FXML private Label lblFeedbackQuiz;

    @FXML
    public void initialize() {
        try {
            perguntas = perguntaDAO.listaTodos();
            if (!perguntas.isEmpty()) {
                iniciarCronometro();
                carregarProximaPergunta();
            } else {
                lblFeedbackQuiz.setText("Nenhuma pergunta encontrada.");
            }
        } catch (Exception e) {
            lblFeedbackQuiz.setText("Erro ao carregar perguntas.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleResponder(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        char respostaEscolhida = sourceButton.getText().charAt(0); // Assume A, B, C, D
        cronometro.stop();

        boolean correta = validarResposta(perguntas.get(perguntaAtual), respostaEscolhida);

        if (correta) {
            lblFeedbackQuiz.setText("Correto!");
            lblFeedbackQuiz.setStyle("-fx-text-fill: green;");
            perguntaAtual++;
            if (perguntaAtual < perguntas.size()) {
                new Thread(() -> {
                    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                    javafx.application.Platform.runLater(() -> {
                        tempoRestante = 60.0;
                        iniciarCronometro();
                        carregarProximaPergunta();
                    });
                }).start();
            } else {
                abrirTelaVitoria(event);
            }
        } else {
            lblFeedbackQuiz.setText("Errado! Fim de jogo.");
            lblFeedbackQuiz.setStyle("-fx-text-fill: red;");
            abrirTelaGameOver(event);
        }
    }

    private void carregarProximaPergunta() {
        if (perguntaAtual < perguntas.size()) {
            Pergunta p = perguntas.get(perguntaAtual);
            lblNumeroPergunta.setText("Pergunta " + (perguntaAtual + 1));
            txtPergunta.setText(p.getTextoPergunta());
            btnAlternativaA.setText("A) " + p.getAlternativas()[0]);
            btnAlternativaB.setText("B) " + p.getAlternativas()[1]);
            btnAlternativaC.setText("C) " + p.getAlternativas()[2]);
            btnAlternativaD.setText("D) " + p.getAlternativas()[3]);
            lblFeedbackQuiz.setText("");
        }
    }

    private void iniciarCronometro() {
        cronometro = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            tempoRestante -= 0.1;
            lblTemporizador.setText(String.format("%.1fs", tempoRestante));
            if (tempoRestante <= 0) {
                cronometro.stop();
                lblFeedbackQuiz.setText("Tempo esgotado! Fim de jogo.");
                lblFeedbackQuiz.setStyle("-fx-text-fill: red;");
                abrirTelaGameOver(null); // Chamada sem event, pois é automática
            }
        }));
        cronometro.setCycleCount(Timeline.INDEFINITE);
        cronometro.play();
    }

    private boolean validarResposta(Pergunta p, char resposta) {
        // Adicione campo resposta_correta ao modelo Pergunta e DB para validação real
        // Por enquanto, simule (ex.: resposta 'A' é correta para todas)
        return resposta == 'A'; // Ajuste conforme DB
    }

    @FXML
    private void abrirTelaVitoria(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaVitoria.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("POO Gênio Quiz - Vitória!");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Vitória.");
        }
    }

    @FXML
    private void abrirTelaGameOver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaGameOver.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("POO Gênio Quiz - Fim de Jogo");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Game Over.");
        }
    }
}
