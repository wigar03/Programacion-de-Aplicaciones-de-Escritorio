module org.uam.ejemplo3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uam.ejemplo3 to javafx.fxml;
    exports org.uam.ejemplo3;
}