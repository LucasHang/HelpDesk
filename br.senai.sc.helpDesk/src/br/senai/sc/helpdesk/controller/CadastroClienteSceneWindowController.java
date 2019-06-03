/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.DAO.DAOFactory;
import br.senai.sc.helpdesk.MeuAlerta;
import br.senai.sc.helpdesk.model.Cliente;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Senai
 */
public class CadastroClienteSceneWindowController implements Initializable {
    @FXML
    private Button btnCadastrarCliente;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenhaDeNovo;

    Cliente novoCliente = null;
    MeuAlerta alerta = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        novoCliente = new Cliente();
        bindFields();
    }    

    @FXML
    private void btnCadastrarClienteOnAction(ActionEvent event) {
        
        if(verificaFields()){
            return;
        }
        
        txtNome.getStyleClass().remove("invalido");
        txtEmail.getStyleClass().remove("invalido");
        txtSenha.getStyleClass().remove("invalido");
        txtSenhaDeNovo.getStyleClass().remove("invalido");
        
        unBindFields();
        
        try {
            DAOFactory.getClienteDAO().save(novoCliente);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage());
        }
    }
    
    private Boolean verificaFields(){
        Boolean invalido = false;
        
        if(txtNome.textProperty().isNull().get()){
            txtNome.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            txtNome.getStyleClass().remove("invalido");
        }
        
        if(txtEmail.textProperty().isNull().get()){
            txtEmail.getStyleClass().add("invalido");
            invalido = true;   
        }else{
            txtEmail.getStyleClass().remove("invalido");
        }
        
        if(txtSenha.textProperty().isNull().get() && !txtSenha.getText().equals(txtSenhaDeNovo.getText())){
            txtSenha.getStyleClass().add("invalido");
            txtSenhaDeNovo.getStyleClass().add("invalido");
            invalido = true;
        }else{
            txtSenha.getStyleClass().remove("invalido");
            txtSenhaDeNovo.getStyleClass().remove("invalido");
        } 
        
        return invalido;
    }
    
    private void bindFields(){
        txtNome.textProperty().bindBidirectional(novoCliente.getNomeProperty());
        txtEmail.textProperty().bindBidirectional(novoCliente.getEmailProperty());
        txtSenha.textProperty().bindBidirectional(novoCliente.getSenhaProperty());
    }
    
    private void unBindFields(){
        txtNome.textProperty().unbindBidirectional(novoCliente.getNomeProperty());
        txtEmail.textProperty().unbindBidirectional(novoCliente.getEmailProperty());
        txtSenha.textProperty().unbindBidirectional(novoCliente.getSenhaProperty());
    }
}
