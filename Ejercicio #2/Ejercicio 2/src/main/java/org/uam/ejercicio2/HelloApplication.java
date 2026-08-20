package org.uam.ejercicio2;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.function.Function;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        
        Label lblTitulo = new Label("Calculadora de salario mas alto, mas bajo, promedio y moda");
        lblTitulo.setFont(new javafx.scene.text.Font("Arial", 18));
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-text-alignment: center;");
        HBox titleBox = new HBox(lblTitulo);
        titleBox.setAlignment(Pos.CENTER);


        Label lblCantidadEmpleados = new Label("Cantidad de empleados:");
        lblCantidadEmpleados.setFont(new javafx.scene.text.Font("Arial", 14));
        lblCantidadEmpleados.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-text-alignment: center;");
        TextField txtCantidadEmpleados = new TextField();
        Button btnAgregarSalarios = new Button("Agregar Salarios");
        btnAgregarSalarios.setPrefWidth(135);
        btnAgregarSalarios.setPrefHeight(35);
        btnAgregarSalarios.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;");
        

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> {
            stage.close();
        });
        btnSalir.setPrefWidth(100);
        btnSalir.setPrefHeight(35);
        btnSalir.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;");

        HBox footerBox = new HBox(btnSalir);
        footerBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(footerBox, Priority.ALWAYS);


        List<Double> salarios = new ArrayList<>();

        btnAgregarSalarios.setOnAction(e -> {
        try {
            int cantidadEmpleados = Integer.parseInt(txtCantidadEmpleados.getText());

            for (int i = 0; i < cantidadEmpleados; i++) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Ingreso de Salario");
                dialog.setHeaderText("Empleado " + (i + 1) + " de " + cantidadEmpleados);
                dialog.setContentText("Ingrese el salario del empleado:");

                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    String texto = result.get().trim();
                    if (texto.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Campo Vacío");
                        alert.setHeaderText(null);
                        alert.setContentText("Debe ingresar un salario, no puede dejar el campo vacío.");
                        alert.showAndWait();
                        i--;
                    } else {
                        try {
                            double salario = Double.parseDouble(texto);
                            if (salario <= 0) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Salario Inválido");
                                alert.setHeaderText(null);
                                alert.setContentText("El salario debe ser un número mayor a 0.");
                                alert.showAndWait();
                                i--;
                            } else {
                                salarios.add(salario);
                            }
                        } catch (NumberFormatException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Formato Incorrecto");
                            alert.setHeaderText(null);
                            alert.setContentText("Por favor, ingrese un monto numérico válido.");
                            alert.showAndWait();
                            i--;
                        }
                    }
                } else {
                    break;
                }
            }

            

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cantidad Inválida");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese una cantidad válida de empleados (número entero).");
            alert.showAndWait();
        }
    });


        Label salarioMasAlto = new Label("Salario mas alto: ");
        Label salarioMasBajo = new Label("Salario mas bajo: ");
        Label promedio = new Label("Promedio: ");
        Label moda = new Label("Moda: ");

        VBox resultadosBox = new VBox(salarioMasAlto, salarioMasBajo, promedio, moda);
        resultadosBox.setPadding(new Insets(10, 0, 10, 0));
        resultadosBox.setSpacing(8);
        resultadosBox.setAlignment(Pos.CENTER_LEFT);
        resultadosBox.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-font-size: 14px;");
        
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(135);
        btnCalcular.setPrefHeight(35);
        btnCalcular.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;");

        btnCalcular.setOnAction(e -> {
            if (salarios.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Sin Datos");
                alert.setHeaderText(null);
                alert.setContentText("Primero debe agregar los salarios antes de calcular.");
                alert.showAndWait();
                return;
            }

            double salarioMAX = Collections.max(salarios);
            double salarioMIN = Collections.min(salarios);
            double suma = 0;
            for (double salario : salarios) {
                suma += salario;
            }
            double promedioCalculado = suma / salarios.size();

            Map<Double, Long> frecuencia = salarios.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            double Moda = frecuencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0.0);

            salarioMasAlto.setText(String.format("Salario mas alto: %.2f", salarioMAX));
            salarioMasBajo.setText(String.format("Salario mas bajo: %.2f", salarioMIN));
            promedio.setText(String.format("Promedio: %.2f", promedioCalculado));
            moda.setText(String.format("Moda: %.2f", Moda));
        });

        
        root.getChildren().addAll(titleBox, 
            lblCantidadEmpleados, txtCantidadEmpleados, btnAgregarSalarios, 
            btnCalcular, resultadosBox, footerBox);
        
        Scene scene = new Scene(root, 600, 480);
        stage.setTitle("Calculadora de salario mas alto, mas bajo, promedio y moda");
        stage.setScene(scene);
        stage.show();
    }
}
