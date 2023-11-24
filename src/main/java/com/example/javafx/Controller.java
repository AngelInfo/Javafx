package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private CheckBox ordenA;
    @FXML
    private CheckBox mayoresEdad;

    @FXML
    private TextField DNI;

    @FXML
    private Button agregar;

    @FXML
    private Button eliminar;

    @FXML
    private Button mostrar;
    @FXML
    private Button buscar;

    @FXML
    private TableColumn<Persona, String> columnaDNI;

    @FXML
    private TableColumn<Persona, Integer> columnaEdad;

    @FXML
    private TableColumn<Persona, String> columnaNombre;

    @FXML
    private TableColumn<Persona, Integer> columnaTlf;

    @FXML
    private TextField edad;

    @FXML
    private TextField nombre;

    @FXML
    private TableView<Persona> tabla;

    @FXML
    private TextField tlf;
    ObservableList<Persona> lista;


    @FXML
    void agregarPersona(ActionEvent event){
        if (DNI.getText() == null || nombre.getText() == null || tlf.getText() == null || edad.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Rellena todos los elementos para añdir persona");
            alert.showAndWait();
        }else {
            if (DNI.getText().matches("[0-9]{8}[A-Z]")){
                if (tlf.getText().matches("^[1-9]\\d{8}$")){
                    if (edad.getText().matches("^[1-9]\\d*$")){
                        Persona p = new Persona(nombre.getText(),DNI.getText(),Integer.parseInt(tlf.getText()),Integer.parseInt(edad.getText()));
                        lista.add(p);
                        tabla.setItems(lista);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Edad incorrecta debe ser un número entero positivo");
                        alert.showAndWait();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Telefóno incorrecto debe tener 9 dígitos");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El DNI no es válido, tiene que cumplir el formato DNI(ejemplo:12345678M)");
                alert.showAndWait();
            }


        }
    }

    @FXML
    void eliminarPersona(ActionEvent event){
        tabla.setItems(lista);
        if(tabla.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Elige un elemento de la tabla");
            alert.showAndWait();
        }else {
            lista.remove(tabla.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    void buscarPersona(ActionEvent event){
        ObservableList<Persona> listaDNI = FXCollections.observableArrayList();
        for (Persona p: lista){
            System.out.println("Comparando DNI: " + p.getDNI() + " con " + DNI.getText());
            if (p.getDNI().trim().equalsIgnoreCase(DNI.getText().trim())){
                listaDNI.add(p);
            }
        }
        if (listaDNI.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("DNI no encontrado");
            alert.showAndWait();
        }else {
            tabla.setItems(listaDNI);
        }
    }

    @FXML
    void mostrarPersonas(ActionEvent event){
        tabla.setItems(lista);
    }

    @FXML
    public void ordenarAlfabeticamente(ActionEvent event) {
        mayoresEdad.setSelected(false);
        lista.sort(new Comparator<Persona>() {

            @Override
            public int compare(Persona o1, Persona o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        tabla.setItems(lista);
    }

    @FXML
    public void mostrarMayoresEdad(ActionEvent event) {
        ordenA.setSelected(false);
        ObservableList<Persona> listaEdad = FXCollections.observableArrayList();
        for (Persona p: lista){
            if (p.getEdad() >= 18){
                listaEdad.add(p);
            }
        }
        tabla.setItems(listaEdad);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lista = FXCollections.observableArrayList();
        columnaDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaTlf.setCellValueFactory(new PropertyValueFactory<>("Tlf"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("Edad"));


    }
}

