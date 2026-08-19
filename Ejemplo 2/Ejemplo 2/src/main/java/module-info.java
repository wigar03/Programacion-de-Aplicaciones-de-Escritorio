module org.uam.ejemplo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.uam.ejemplo2 to javafx.fxml;
    exports org.uam.ejemplo2;
}