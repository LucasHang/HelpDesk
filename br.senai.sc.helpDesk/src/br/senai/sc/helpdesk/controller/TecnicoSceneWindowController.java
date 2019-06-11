/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.DAO.DAOFactory;
import br.senai.sc.helpdesk.MeuAlerta;
import br.senai.sc.helpdesk.model.Problema;
import br.senai.sc.helpdesk.model.Tecnico;
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
import br.senai.sc.helpdesk.controller.mainSceneWindowController;
import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.NumberStringConverter;

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
    List<String> area = Arrays.asList("Financeiro", "Cadastros","Design");
    List<String> dificuldade = Arrays.asList("Fácil", "Dificil","Meu amigo...");
    List<String> urgencia = Arrays.asList("Urgente", "Pode esperar");
    
    MeuAlerta alerta;
    Problema problemaSelecionado;
    ProblemaResolvido novoProblemaResolvido;
    Tecnico tecnicoLogado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        novoProblemaResolvido = new ProblemaResolvido();
        
        comboTipo.setItems(FXCollections.observableArrayList(tipos));
        comboArea.setItems(FXCollections.observableArrayList(area));
        comboDificuldade.setItems(FXCollections.observableArrayList(dificuldade));
        comboUrgencia.setItems(FXCollections.observableArrayList(urgencia));
        
        try {
            tecnicoLogado = DAOFactory.getTecnicoDAO().getTecnicoByEmail(mainSceneWindowController.emailLogado);
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).show();
        }
        
        lblNomeUsuario.setText(tecnicoLogado.getNome());
        lblEmailUsuario.setText(tecnicoLogado.getEmail());
        
        
        
        try {
            tableProblemas.setItems(FXCollections.observableArrayList(DAOFactory.getProblemaDAO().getAll()));
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).show();
        }
 
        
        
        tableProblemas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            unbindLabels(oldValue);
            bindLabels(newValue);
            problemaSelecionado = newValue;
        });
        
        bindFields(novoProblemaResolvido);
    }    

    @FXML
    private void btnEnviarOnAction(ActionEvent event) {
        
        if(verificaFields()){
            return;
        }
        
        comboTipo.getStyleClass().remove("invalido");
        comboArea.getStyleClass().remove("invalido");
        comboDificuldade.getStyleClass().remove("invalido");
        comboUrgencia.getStyleClass().remove("invalido");
        tableProblemas.getStyleClass().remove("invalido");
        
        unbindFields(novoProblemaResolvido);
        
        try {
            novoProblemaResolvido.setProblema(problemaSelecionado);
            novoProblemaResolvido.setTecnico(tecnicoLogado);
            DAOFactory.getProblemaResolvidoDAO().save(novoProblemaResolvido);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).show();
        }
        
    }
    
    private Boolean verificaFields(){
        Boolean invalido = false;
        
        if(comboTipo.accessibleTextProperty().isNull().get()){
            comboTipo.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            comboTipo.getStyleClass().remove("invalido");
        }
        
        if(comboArea.accessibleTextProperty().isNull().get()){
            comboArea.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            comboArea.getStyleClass().remove("invalido");
        }
        
        if(comboDificuldade.accessibleTextProperty().isNull().get()){
            comboDificuldade.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            comboDificuldade.getStyleClass().remove("invalido");
        }
        
        if(comboUrgencia.accessibleTextProperty().isNull().get()){
            comboUrgencia.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            comboUrgencia.getStyleClass().remove("invalido");
        }
        
        if(problemaSelecionado == null){
            tableProblemas.getStyleClass().add("invalido");
        }else{
            tableProblemas.getStyleClass().remove("invalido");
        }
        
        return invalido;
    }
    
    private void bindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            comboTipo.accessibleTextProperty().bindBidirectional(problemaResolvido.tipoProperty());
            comboArea.accessibleTextProperty().bindBidirectional(problemaResolvido.areaProperty());
            comboDificuldade.accessibleTextProperty().bindBidirectional(problemaResolvido.dificuldadeProperty());
            comboUrgencia.accessibleTextProperty().bindBidirectional(problemaResolvido.urgenciaProperty());
        }
    }

    private void unbindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            comboTipo.accessibleTextProperty().unbindBidirectional(problemaResolvido.tipoProperty());
            comboArea.accessibleTextProperty().unbindBidirectional(problemaResolvido.areaProperty());
            comboDificuldade.accessibleTextProperty().unbindBidirectional(problemaResolvido.dificuldadeProperty());
            comboUrgencia.accessibleTextProperty().unbindBidirectional(problemaResolvido.urgenciaProperty());
        }
    }
    
    private void bindLabels(Problema problemaSelecionado){
        lblNomeCliente.textProperty().bind(problemaSelecionado.getCliente().nomeProperty());
        lblDescricaoProblema.textProperty().bind(problemaSelecionado.descricaoProperty());
    }
    
    private void unbindLabels(Problema problemaSelecionado){
        problemaSelecionado.getCliente().nomeProperty().unbind();
        problemaSelecionado.descricaoProperty().unbind();
    }
}
