/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Senai
 */
public class FuncionarioSceneWindowController implements Initializable {

    @FXML
    private Label lblNomeUsuario;
    @FXML
    private Label lblEmailUsuario;
    @FXML
    private Label lblTituloTecnico;
    @FXML
    private TableView<ProblemaResolvido> tblProblemasResolvidos;
    @FXML
    private TextArea txtDescResolucao;
    @FXML
    private ComboBox<String> comboStatus;   
    @FXML
    private TextField txtTecnico;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtDificuldade;
    @FXML
    private TextField txtUrgencia;
    @FXML
    private Pane paneOPane;
    @FXML
    private Button btnRelatar;
    
    
    List<String> status = Arrays.asList("Pendente","Em andamento","Resolvido");
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboStatus.setItems(FXCollections.observableArrayList(status));
    }    

    @FXML
    private void btnRelatarOnAction(ActionEvent event) {
    }
    
}
