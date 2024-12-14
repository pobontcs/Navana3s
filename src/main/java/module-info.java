module com.example.navana3s {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.navana3s to javafx.fxml;
    exports com.example.navana3s;
}