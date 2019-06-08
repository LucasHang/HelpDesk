/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
    
     public static Stage TheStage;
    
     private static Scene cliente;
     private static Scene cadastroCliente;
     private static Scene tecnico;
     private static Scene funcionario;
    
 
     
     
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
                cliente = new Scene(fxmlCliente, 800, 600);
                TheStage.setScene(cliente);
                TheStage.show();
                break;
            case "cadastroCliente" :
                Parent fxmlCadastroCliente = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/cadastroClienteSceneWindow.fxml"));
                cadastroCliente = new Scene(fxmlCadastroCliente, 800, 600);
                TheStage.setScene(cadastroCliente);
                TheStage.show();
                break;
            case "tecnico":
                Parent fxmlTecnico = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/tecnicoSceneWindow.fxml"));
                tecnico = new Scene(fxmlTecnico, 1280, 720);
                TheStage.setScene(tecnico);
                TheStage.show();
                break;
                
             case "funcionario" :
                Parent fxmlFuncionario = FXMLLoader.load(BrSenaiScHelpDesk.class.getResource("view/funcionarioSceneWindow.fxml"));
                funcionario = new Scene(fxmlFuncionario, 1280, 720);
                TheStage.close();
                TheStage.setScene(funcionario);
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
