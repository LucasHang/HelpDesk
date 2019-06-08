/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.DAO.DAOFactory;
import br.senai.sc.helpdesk.MeuAlerta;
import br.senai.sc.helpdesk.model.Problema;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import br.senai.sc.helpdesk.controller.mainSceneWindowController;
import br.senai.sc.helpdesk.model.Cliente;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField txtEmpresa;

    Cliente clienteLogado;
    MeuAlerta alerta;
    Problema novoProblema;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            clienteLogado = DAOFactory.getClienteDAO().getClienteByEmail(mainSceneWindowController.emailLogado);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage());
        }
        lblEmailUsuario.setText(clienteLogado.getEmail());
        lblNomeUsuario.setText(clienteLogado.getNome());
        
        novoProblema = new Problema();
        bindFields(novoProblema);
    }    

    @FXML
    private void btnEnviarProblemaOnAction(ActionEvent event) {
        
        if(verificaFields()){
            return;
        }
        
        txtDescricaoProblema.getStyleClass().remove("invalido");
        txtEmpresa.getStyleClass().remove("invalido");
        
        unBindFields(novoProblema);
        
        try {
            novoProblema.setCliente(clienteLogado);
            novoProblema.setDataEnvio(takeActualDate());
            
            DAOFactory.getProblemaDAO().save(novoProblema);
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage());
        }
        
    }
    
    private Boolean verificaFields(){
        Boolean invalido = false;
        
        if(txtDescricaoProblema.textProperty().isNull().get()){
            txtDescricaoProblema.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            txtDescricaoProblema.getStyleClass().remove("invalido");
        }
        
         if(txtEmpresa.textProperty().isNull().get()){
            txtEmpresa.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            txtEmpresa.getStyleClass().remove("invalido");
        }
        
        return invalido;
    }
    
    private void bindFields(Problema problema){
        if(problema != null){
            txtDescricaoProblema.textProperty().bindBidirectional(problema.descricaoProperty());
            txtEmpresa.textProperty().bindBidirectional(problema.empresaProperty());
        }
        
    }
    
    private void unBindFields(Problema problema){
        if(problema != null){
            txtDescricaoProblema.textProperty().bindBidirectional(problema.descricaoProperty());
            txtEmpresa.textProperty().unbindBidirectional(problema.empresaProperty());
        }
    }
    
    private Integer takeActualDate(){

        Date date = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat();
        Integer dataNumeric = parseInt(dataFormat.format(date));
        return dataNumeric;
    }
    
}
