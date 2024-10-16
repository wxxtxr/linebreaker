module com.example.linebreaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.linebreaker to javafx.fxml;
    exports com.example.linebreaker;
}