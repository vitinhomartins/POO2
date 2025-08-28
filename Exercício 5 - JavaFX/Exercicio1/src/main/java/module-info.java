module com.exemplo.exercicio1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exemplo.exercicio1 to javafx.fxml;
    exports com.exemplo.exercicio1;
}