package org.uam.ejercicio1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);
        root.setStyle("-fx-background-color: #f2eafd;");

        Label lblTitle = new Label("Bienvenido!, Ingrese sus datos para calcular su salario");
        Label lblSalarioBruto = new Label("Salario Bruto:");
        TextField txtSalarioBruto = new TextField();
        Button btnCalcular = new Button("Calcular");

        Label lblResultado = new Label("Resultado:");

        Button btnSalir = new Button("Salir");

        btnCalcular.setPrefWidth(100);
        btnCalcular.setPrefHeight(35);
        btnCalcular.setStyle(
                "-fx-background-color: #4FE086; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;");

        btnCalcular.setOnAction(e -> {
            try {
                int salarioBruto = Integer.parseInt(txtSalarioBruto.getText());
                double Bono = 0;
                double seguroSocial = 0.07;
                if (salarioBruto < 12000) {
                    Bono = salarioBruto * 0.10;

                } else if (salarioBruto >= 12000 && salarioBruto <= 20000) {
                    Bono = salarioBruto * 0.05;
                } else if (salarioBruto > 20000) {
                    Bono = salarioBruto * 0.03;
                }

                double pagoSeguroSocial = salarioBruto * seguroSocial;
                double totalSalario = Bono + (salarioBruto - pagoSeguroSocial);

                lblResultado.setText(String.format(
                        "Salario Bruto: %d\nBono: %.2f\nSeguro Social: %.2f\nTotal Salario: %.2f",
                        salarioBruto, Bono, pagoSeguroSocial, totalSalario));
            } catch (NumberFormatException ex) {
            }
        });

        btnSalir.setOnAction(e -> {
            System.exit(0);
        });

        btnSalir.setPrefWidth(100);
        btnSalir.setPrefHeight(35);
        btnSalir.setStyle(
                "-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;");

        HBox footerBox = new HBox(btnSalir);
        footerBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(footerBox, Priority.ALWAYS);

        root.getChildren().addAll(lblTitle, lblSalarioBruto, txtSalarioBruto, btnCalcular, lblResultado, footerBox);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Calculadora de Salario");
        stage.setScene(scene);
        stage.show();
    }
}
