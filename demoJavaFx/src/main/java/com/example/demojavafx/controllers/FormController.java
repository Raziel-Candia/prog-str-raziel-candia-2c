package com.example.demojavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class FormController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEdad;
    @FXML
    private Label lblResultado;

    public void onValidate(ActionEvent event){
        String nombre= txtNombre.getText()== null ? "" : txtNombre.getText();
        String email= txtEmail.getText()== null ? "" : txtEmail.getText();
        String edad= txtEdad.getText()== null ? "" : txtEdad.getText();

        List<String> errores = new ArrayList<>();
        if (nombre.isBlank() || nombre.length()<3){
            errores.add("el nombre ingresado es invalido");
        }
        //validacion del null
        if (email.isBlank() || !email.contains("@") || !email.contains(".")){
            errores.add("el email es invalido");

        }
        int edadValida=0;
        try{
            edadValida=Integer.parseInt(edad);
            if (edadValida <= 0 || edadValida > 120) {

                errores.add("edad fuera de rango");
            }

        }catch (Exception e){
            errores.add("dato no numerico");

        }
        if (errores.isEmpty()){
            lblResultado.setText("el formulario es correcto");
            lblResultado.setStyle("-fx-text-fill:green");

        } else {
            String datos="";
            for (String dato : errores){
                datos+=dato;
            }
            lblResultado.setText(datos);
            lblResultado.setStyle("-fx-text-fill: red");
        }
    }


}
