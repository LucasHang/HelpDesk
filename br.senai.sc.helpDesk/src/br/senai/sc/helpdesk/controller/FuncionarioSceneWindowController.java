/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.controller;

import br.senai.sc.helpdesk.DAO.DAOFactory;
import br.senai.sc.helpdesk.MeuAlerta;
import br.senai.sc.helpdesk.model.Funcionario;
import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
    private TableView<ProblemaResolvido> tblProblemasResolvidos;
    @FXML
    private TextArea txtDescResolucao;   
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
    @FXML
    private TextField txtStatus;
    @FXML
    private TableColumn<ProblemaResolvido, Integer> tblColumnData;
   
    MeuAlerta alerta;
    ProblemaResolvido problemaSelecionado;
    Funcionario funcionarioLogado;
    @FXML
    private Button btnRecarregar;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            funcionarioLogado = DAOFactory.getFuncionarioDAO().getFuncionarioByEmail(mainSceneWindowController.emailLogado);
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).showAndWait();
        }
        
        lblNomeUsuario.setText(funcionarioLogado.getNome());
        lblEmailUsuario.setText(funcionarioLogado.getEmail());
        
        try {
            btnRecarregarOnAction(null);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).showAndWait();
        }
                
        tblColumnData.setCellFactory((TableColumn<ProblemaResolvido, Integer> param) -> {
            TableCell cell = new TableCell<ProblemaResolvido, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            setText("");
                        } else {
                            String dataString = "";
                            List<String> aux = Arrays.asList(item.toString().split(""));
                            for (String num: aux.subList(6, 8)){
                            dataString +=  num;
                            }
                            dataString += "/";
                            for (String num: aux.subList(4, 6)){
                            dataString +=  num;
                            }
                            dataString += "/";
                            for (String num: aux.subList(0, 4)){
                            dataString +=  num;
                            }
                            setText(dataString);
                        }

                    }
                }

                @Override
                public void updateSelected(boolean upd) {
                    super.updateSelected(upd);
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            return cell;
        });
        
        
        
        
        tblProblemasResolvidos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            unbindFields(oldValue);
            bindFields(newValue);  
            problemaSelecionado = newValue;
        });
        
    }    

    @FXML
    private void btnRelatarOnAction(ActionEvent event) {
        if(verificaFields()){
            return;
        }
        
        txtStatus.getStyleClass().remove("invalido");
        txtDescResolucao.getStyleClass().remove("invalido");
        
        unbindFields(problemaSelecionado);
        
        try {
            problemaSelecionado.setFuncionario(funcionarioLogado);
            DAOFactory.getProblemaResolvidoDAO().update(problemaSelecionado);
            clearFields();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).showAndWait();
        }
    }
 
    
    @FXML
    private void btnRecarregarOnAction(ActionEvent event) throws SQLException {
         tblProblemasResolvidos.setItems(FXCollections.observableArrayList(DAOFactory.getProblemaResolvidoDAO().
            getProblemaResolvidoByEmpresaEArea(funcionarioLogado.getEmpresa(), funcionarioLogado.getArea())));
    }
    
    private Boolean verificaFields(){
        Boolean invalido = false;
        
        if(txtDescResolucao.textProperty().isNull().get()){
            txtDescResolucao.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            txtDescResolucao.getStyleClass().remove("invalido");
        }
        
        
        if(txtStatus.textProperty().isNull().get()){
            txtStatus.getStyleClass().add("invalido");
            invalido = true;
            
        }else{
            txtStatus.getStyleClass().remove("invalido");
        }
        
        return invalido;
    }
    
    private void bindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            txtStatus.textProperty().bindBidirectional(problemaResolvido.statusProperty());
            txtDescResolucao.textProperty().bindBidirectional(problemaResolvido.descResolucaoProperty());
            txtTecnico.textProperty().bind(problemaResolvido.getTecnico().nomeProperty());
            txtDescricao.textProperty().bind(problemaResolvido.getProblema().descricaoProperty());
            txtArea.textProperty().bind(problemaResolvido.areaProperty());
            txtDificuldade.textProperty().bind(problemaResolvido.dificuldadeProperty());
            txtTipo.textProperty().bind(problemaResolvido.tipoProperty());
            txtUrgencia.textProperty().bind(problemaResolvido.urgenciaProperty());
        }
    }

    private void unbindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            txtStatus.textProperty().unbindBidirectional(problemaResolvido.statusProperty());
            txtDescResolucao.textProperty().unbindBidirectional(problemaResolvido.descResolucaoProperty());
            txtTecnico.textProperty().unbind();
            txtDescricao.textProperty().unbind();
            txtArea.textProperty().unbind();
            txtDificuldade.textProperty().unbind();
            txtTipo.textProperty().unbind();
            txtUrgencia.textProperty().unbind();
        }
    }
    
    private void clearFields(){
        txtStatus.clear();
        txtDescResolucao.clear();
        txtTecnico.clear();
        txtDescricao.clear();
        txtArea.clear();
        txtDificuldade.clear();
        txtTipo.clear();
        txtUrgencia.clear();
    }

}
