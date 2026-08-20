module org.uam.ejercicio2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uam.ejercicio2 to javafx.fxml;
    exports org.uam.ejercicio2;
}