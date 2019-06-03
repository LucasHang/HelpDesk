/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Bratva
 */
public class ClienteSceneWindowController implements Initializable {
    @FXML
    private TextArea txtDescricaoProblema;
    @FXML
    private Button btnEnviarProblema;
    @FXML
    private Label lblNomeUsuario;
    @FXML
    private Label lblEmailUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEnviarProblemaOnAction(ActionEvent event) {
    }
    
}
