/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.model.Problema;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bratva
 */
public class TecnicoSceneWindowController implements Initializable {
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private ComboBox<String> comboArea;
    @FXML
    private ComboBox<String> comboDificuldade;
    @FXML
    private ComboBox<String> comboUrgencia;
    @FXML
    private TextField txtHoraOcorrencia;
    @FXML
    private Button btnEnviar;
    @FXML
    private Label lblNomeUsuario;
    @FXML
    private Label lblEmailUsuario;
    @FXML
    private Label lblNomeCliente;
    @FXML
    private Label lblDescricaoProblema;
    @FXML
    private TableView<Problema> tableProblemas;

    
    List<String> tipos = Arrays.asList("TI", "DBA");
    List<String> area = Arrays.asList("Financeiro", "Cadastros");
    List<String> dificuldade = Arrays.asList("Fácil", "Dificil","Meu amigo...");
    List<String> urgencia = Arrays.asList("Urgente", "Pode esperar");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTipo.setItems(FXCollections.observableArrayList(tipos));
        comboArea.setItems(FXCollections.observableArrayList(area));
        comboDificuldade.setItems(FXCollections.observableArrayList(dificuldade));
        comboUrgencia.setItems(FXCollections.observableArrayList(urgencia));
    }    

    @FXML
    private void btnEnviarOnAction(ActionEvent event) {
    }
    
}
