module org.uam.ejemplo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uam.ejemplo1 to javafx.fxml;
    exports org.uam.ejemplo1;
}