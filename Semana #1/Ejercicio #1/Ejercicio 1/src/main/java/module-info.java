module org.uam.ejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uam.ejercicio1 to javafx.fxml;
    exports org.uam.ejercicio1;
}