package com.exemplo.exercicio1;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class exercicio1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        GridPane grid = new GridPane();
        grid.setHgap(7);
        grid.setVgap(7);

        Label lblNome = new Label("Nome: ");
        TextField txtNome = new TextField();
        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);

        Label lblEmail = new Label("Email: ");
        TextField txtEmail = new TextField();
        grid.add(lblEmail, 0, 1);
        grid.add(txtEmail, 1, 1);

        Label lblTurno = new Label("Turno: ");
        grid.add(lblTurno, 0, 2);
        RadioButton btnInt = new RadioButton("Integral");
        btnInt.setSelected(true);
        RadioButton btnNot = new RadioButton("Noturno");

        ToggleGroup tgpTurno = new ToggleGroup();
        btnInt.setToggleGroup(tgpTurno);
        btnNot.setToggleGroup(tgpTurno);

        HBox hboxRadioBtn = new HBox(5.0, btnInt, btnNot);
        grid.add(hboxRadioBtn, 1, 2);

        Label lblCurso = new Label("Curso");
        grid.add(lblCurso, 0, 3);
        ComboBox<String> chcCursos = new ComboBox<String>();
        ObservableList<String> cursos = FXCollections.observableArrayList("Ci�ncia da Computa��o", "Matem�tica",
                "Matem�tica Aplicada", "Engenharia de Alimentos");
        chcCursos.setItems(cursos);
        chcCursos.getSelectionModel().select(0);
        grid.add(chcCursos, 1, 3);

        root.setCenter(grid);

        // Inserir o c�digo solicitado no exerc�cio aqui

        Button btnGravar = new Button("Gravar");
        Button btnSair = new Button("Sair");
        Label lblMsg = new Label();

        btnGravar.setOnAction(event -> {
            String nome = txtNome.getText();
            lblMsg.setText("Informações foram salvas para " + nome);
        });

        btnSair.setOnAction(e -> {
            Platform.exit();
        });

        // Finaliza��o da inser��o do c�digo
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(btnGravar, btnSair, lblMsg);

        root.setBottom(hbox);

        root.setCenter(grid);
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

}
