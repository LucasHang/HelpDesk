/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.BrSenaiScHelpDesk;
import br.senai.sc.helpdesk.DAO.DAOFactory;
import br.senai.sc.helpdesk.MeuAlerta;
import br.senai.sc.helpdesk.model.Cliente;
import br.senai.sc.helpdesk.model.Funcionario;
import br.senai.sc.helpdesk.model.Tecnico;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Bratva
 */
public class mainSceneWindowController implements Initializable {
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCadastrarse;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton radioTecnico;
    @FXML
    private ToggleGroup TipoUsuario;
    @FXML
    private RadioButton radioFuncionario;
    
    MeuAlerta alerta;
    static String emailLogado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
 
    @FXML
    private void btnEntrarOnAction(ActionEvent event) throws IOException {
        
        
        try {
            if(loginVerificado()){
                emailLogado = txtEmail.getText();
                if(radioTecnico.isSelected()){
                    try {
                        BrSenaiScHelpDesk.mudarTela("tecnico");
                    } catch (IOException ex) {
                        Logger.getLogger(mainSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        alerta.alertaErro(ex.getMessage()).show();
                    }
                }else{
                    if(radioFuncionario.isSelected()){
                        try {
                            BrSenaiScHelpDesk.mudarTela("funcionario");
                        } catch (IOException ex) {
                            Logger.getLogger(mainSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
                            alerta.alertaErro(ex.getMessage()).show();
                        }
                    }else{
                        try {
                            BrSenaiScHelpDesk.mudarTela("cliente");
                        } catch (IOException ex) {
                            Logger.getLogger(mainSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
                            alerta.alertaErro(ex.getMessage()).show();
                        }
                    }
                }
            }else{
                alerta.alertaErro("Senha Incorreta").showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro("Email Incorreto").showAndWait();
        }
    }

    @FXML
    private void btnCadastrarseOnAction(ActionEvent event) {
        try {
            BrSenaiScHelpDesk.mudarTela("cadastroCliente");
        } catch (IOException ex) {
            Logger.getLogger(mainSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).show();
        }
    }
    
    public Boolean loginVerificado () throws SQLException{
        if(radioTecnico.isSelected()){
            Tecnico tecnico;
            tecnico = DAOFactory.getTecnicoDAO().getTecnicoByEmail(txtEmail.getText());

            if(tecnico != null){
                return tecnico.getSenha().equals(txtSenha.getText());
            }else{
                throw new SQLException("Técnico não encontrado");
            }
        }else{
            if(radioFuncionario.isSelected()){
                Funcionario funcionario;
                funcionario = DAOFactory.getFuncionarioDAO().getFuncionarioByEmail(txtEmail.getText());

                if(funcionario != null){
                    return funcionario.getSenha().equals(txtSenha.getText());
                }else{
                    throw new SQLException("Funcionário não encontrado");
                }
            }else{
                Cliente cliente;
                cliente = DAOFactory.getClienteDAO().getClienteByEmail(txtEmail.getText());

                if(cliente != null){
                    return cliente.getSenha().equals(txtSenha.getText());
                }else{
                    throw new SQLException("Cliente não encontrado");
                }
            }
        }
    }

    
}