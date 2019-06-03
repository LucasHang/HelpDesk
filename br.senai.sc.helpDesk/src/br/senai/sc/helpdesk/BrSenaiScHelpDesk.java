/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Senai
 */
public class BrSenaiScHelpDesk extends Application {
    
     private static Stage TheStage;
    
     private static Scene cliente;
     private static Scene cadastroCliente;
     private static Scene tecnico;
     private static Scene cadastroTecnico;
     
    @Override
    public void start(Stage stage) throws Exception {
        
        TheStage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("view/mainSceneWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    public static void mudarTela(String tela) throws IOException{
        switch(tela){
            case "cliente":
                Parent fxmlCliente = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/clienteSceneWindow.fxml"));
                cliente = new Scene(fxmlCliente, 700, 807);
                TheStage.setScene(cliente);
                TheStage.show();
                break;
            case "cadastroCliente" :
                Parent fxmlCadastroCliente = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/cadastroClienteSceneWindow.fxml"));
                cadastroCliente = new Scene(fxmlCadastroCliente, 700, 507);
                TheStage.setScene(cadastroCliente);
                TheStage.show();
                break;
            case "tecnico":
                Parent fxmlTecnico = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/tecnicoSceneWindow.fxml"));
                tecnico = new Scene(fxmlTecnico, 800, 607);
                TheStage.setScene(tecnico);
                TheStage.show();
                break;
            case "cadastroTecnico" :
                Parent fxmlCadastroTecnico = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/cadastroTecnicoSceneWindow.fxml"));
                cadastroTecnico = new Scene(fxmlCadastroTecnico, 700, 507);
                TheStage.setScene(cadastroTecnico);
                TheStage.show();
                break;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        launch(args);
    }
    
}
