package com.example.demolistviewfile.controllers;

import com.example.demolistviewfile.services.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class AppController {

    @FXML
    private Label lblMsg;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;

    private ObservableList<String> data = FXCollections.observableArrayList();
    PersonService service= new PersonService();

    @FXML
    public void initialize(){
        listView.setItems(data);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue,newValue)-> {
                    String[] parts = newValue.split("-");
                    txtName.setText(parts[0]);
                    txtEmail.setText(parts[1]);
                    txtAge.setText(parts[2]);
                }
        );
        loadFromFile();
    }

    @FXML
    public void onReload(){
        loadFromFile();
    }

    {
    }
    @FXML
    private TextField txtAge;

    @FXML
    public void onAddPerson() {
        try {
            String name = txtName.getText();
            String email = txtEmail.getText();


            String age = txtAge.getText();


            service.addPerson(name, email, age);

            lblMsg.setText("Usuario ingresado correctamente");
            lblMsg.setStyle("-fx-text-fill: green");


            txtName.clear();
            txtEmail.clear();
            txtAge.clear();
            loadFromFile();

        } catch (NumberFormatException e) {

            lblMsg.setText("Error");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IllegalArgumentException e) {

            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IOException e) {
            lblMsg.setText("Error: " + e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }
    @FXML
    public void onUpdate(){
        try{
            int index= listView.getSelectionModel().getSelectedIndex();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String age = txtAge.getText();
            service.updatePerson(index,name,email,age);
            loadFromFile();
            txtName.clear();
            txtEmail.clear();
            txtAge.clear();
            lblMsg.setText("se actualizo el registro correctamente");


        } catch (IllegalArgumentException | IOException e){
            lblMsg.setText("hubo un error con los datos" +e.getMessage());
        }
    }
    @FXML
    public void onDelete(){
        int index= listView.getSelectionModel().getSelectedIndex();
        try{
            service.delate(index);
            loadFromFile();
            lblMsg.setText("registro eliminado correctamente");
        }catch (IOException e) {
            lblMsg.setText("hubo un error");

        }catch (IllegalArgumentException error) {
            lblMsg.setText("hubo un error en los datos" +error.getMessage());
        }
    }

    private void loadFromFile(){
        try{
            List<String> items = service.loadForListView();
            data.setAll(items);
            lblMsg.setText("Datos cargados");
            lblMsg.setStyle("-fx-text-fill: green");
        } catch (IOException e) {
            lblMsg.setText("Error: "+e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }

}
