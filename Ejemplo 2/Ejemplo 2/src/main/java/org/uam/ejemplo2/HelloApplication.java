package org.uam.ejemplo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitle = new Label("Calculadora");
        Label lblNumber1 = new Label("Numero 1");
        TextField tfNumber1 = new TextField();
        Label lblNumber2 = new Label("Numero 2");
        TextField tfNumber2 = new TextField();

        Button btnSuma = new Button("+");
        Button btnResta = new Button("-");
        Button btnMultiplicacion = new Button("*");
        Button btnDivision = new Button("/");
        
        Label lblResultado = new Label("Resultado");
                
        btnSuma.setOnAction(e -> {
            int num1 = Integer.parseInt(tfNumber1.getText());
            int num2 = Integer.parseInt(tfNumber2.getText());
            int resultado = num1 + num2;
            lblResultado.setText(String.valueOf(resultado));
        });
        
        btnResta.setOnAction(e -> {
            int num1 = Integer.parseInt(tfNumber1.getText());
            int num2 = Integer.parseInt(tfNumber2.getText());
            int resultado = num1 - num2;
            lblResultado.setText(String.valueOf(resultado));
        });
        
        btnMultiplicacion.setOnAction(e -> {
            int num1 = Integer.parseInt(tfNumber1.getText());
            int num2 = Integer.parseInt(tfNumber2.getText());
            int resultado = num1 * num2;
            lblResultado.setText(String.valueOf(resultado));
        });
        
        btnDivision.setOnAction(e -> {
            int num1 = Integer.parseInt(tfNumber1.getText());
            int num2 = Integer.parseInt(tfNumber2.getText());
            int resultado = num1 / num2;
            lblResultado.setText(String.valueOf(resultado));
        });

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(15));
        buttons.getChildren().addAll(btnSuma, btnResta, btnMultiplicacion, btnDivision);

        
        root.getChildren().addAll(lblTitle,lblNumber1,
                tfNumber1,lblNumber2, tfNumber2, buttons, lblResultado);

        Scene scene = new Scene(root,600,400);
        stage.setTitle("UAM");
        stage.setScene(scene);
        stage.show();
    }
}
