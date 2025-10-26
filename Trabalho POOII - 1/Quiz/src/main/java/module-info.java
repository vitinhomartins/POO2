module br.com.quiz.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.com.quiz.quiz to javafx.fxml;
    exports br.com.quiz.quiz;
}