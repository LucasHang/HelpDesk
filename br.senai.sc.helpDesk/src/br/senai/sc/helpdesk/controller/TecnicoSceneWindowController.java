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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Bratva
 */
public class TecnicoSceneWindowController implements Initializable {

    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtDificuldade;
    @FXML
    private TextField txtUrgencia;
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
    private Button btnRecarregar;
    @FXML
    private TableView<Problema> tblProblemas;
    @FXML
    private TableColumn<Problema, Integer> tblColumnData;

    MeuAlerta alerta;
    Problema problemaSelecionado;
    ProblemaResolvido novoProblemaResolvido;
    Tecnico tecnicoLogado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        novoProblemaResolvido = new ProblemaResolvido();

        try {
            tecnicoLogado = DAOFactory.getTecnicoDAO().getTecnicoByEmail(mainSceneWindowController.emailLogado);
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).showAndWait();
        }

        lblNomeUsuario.setText(tecnicoLogado.getNome());
        lblEmailUsuario.setText(tecnicoLogado.getEmail());

        try {
            btnRecarregarOnAction(null);
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).showAndWait();
        }

                
        tblColumnData.setCellFactory((TableColumn<Problema, Integer> param) -> {
            TableCell cell = new TableCell<Problema, Integer>() {

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

        tblProblemas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            lblNomeCliente.textProperty().unbind();
            lblDescricaoProblema.textProperty().unbind();
            
            lblNomeCliente.textProperty().bind(newValue.nomeCliProperty());
            lblDescricaoProblema.textProperty().bind(newValue.descricaoProperty());
            
            problemaSelecionado = newValue;
        });

        bindFields(novoProblemaResolvido);
    }

    @FXML

    private void btnEnviarOnAction(ActionEvent event) {

        if (verificaFields()) {
            return;
        }

        txtTipo.getStyleClass().remove("invalido");
        txtArea.getStyleClass().remove("invalido");
        txtDificuldade.getStyleClass().remove("invalido");
        txtUrgencia.getStyleClass().remove("invalido");
        tblProblemas.getStyleClass().remove("invalido");

        unbindFields(novoProblemaResolvido);

        try {
            novoProblemaResolvido.setProblema(problemaSelecionado);
            novoProblemaResolvido.setTecnico(tecnicoLogado);
            DAOFactory.getProblemaResolvidoDAO().save(novoProblemaResolvido);
            problemaSelecionado.setVerificado(true);
            DAOFactory.getProblemaDAO().update(problemaSelecionado);
            tblProblemas.getItems().remove(problemaSelecionado);
            btnRecarregarOnAction(null);
            clearFields();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteSceneWindowController.class.getName()).log(Level.SEVERE, null, ex);
            alerta.alertaErro(ex.getMessage()).show();
        }

    }

    @FXML
    private void btnRecarregarOnAction(ActionEvent event) throws SQLException {
        tblProblemas.setItems(FXCollections.observableArrayList(DAOFactory.getProblemaDAO().getAll())); 
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if (txtTipo.textProperty().isNull().get()) {
            txtTipo.getStyleClass().add("invalido");
            invalido = true;

        } else {
            txtTipo.getStyleClass().remove("invalido");
        }

        if (txtArea.textProperty().isNull().get()) {
            txtArea.getStyleClass().add("invalido");
            invalido = true;

        } else {
            txtArea.getStyleClass().remove("invalido");
        }

        if (txtDificuldade.textProperty().isNull().get()) {
            txtDificuldade.getStyleClass().add("invalido");
            invalido = true;

        } else {
            txtDificuldade.getStyleClass().remove("invalido");
        }

        if (txtUrgencia.textProperty().isNull().get()) {
            txtUrgencia.getStyleClass().add("invalido");
            invalido = true;

        } else {
            txtUrgencia.getStyleClass().remove("invalido");
        }

        if (problemaSelecionado == null) {
            tblProblemas.getStyleClass().add("invalido");
        } else {
            tblProblemas.getStyleClass().remove("invalido");
        }

        return invalido;
    }

    private void bindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            txtTipo.textProperty().bindBidirectional(problemaResolvido.tipoProperty());
            txtArea.textProperty().bindBidirectional(problemaResolvido.areaProperty());
            txtDificuldade.textProperty().bindBidirectional(problemaResolvido.dificuldadeProperty());
            txtUrgencia.textProperty().bindBidirectional(problemaResolvido.urgenciaProperty());
        }
    }

    private void unbindFields(ProblemaResolvido problemaResolvido) {
        if (problemaResolvido != null) {
            txtTipo.textProperty().unbindBidirectional(problemaResolvido.tipoProperty());
            txtArea.textProperty().unbindBidirectional(problemaResolvido.areaProperty());
            txtDificuldade.textProperty().unbindBidirectional(problemaResolvido.dificuldadeProperty());
            txtUrgencia.textProperty().unbindBidirectional(problemaResolvido.urgenciaProperty());
        }
    }

    private void clearFields() {
        txtTipo.clear();
        txtArea.clear();
        txtDificuldade.clear();
        txtUrgencia.clear();
    }

}
