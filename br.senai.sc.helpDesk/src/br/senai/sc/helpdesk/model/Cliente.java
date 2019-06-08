/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Senai
 */
public class Cliente extends Usuario{
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    

    public Cliente(){
        
    }
      
    public Cliente(Integer codigo, String nome, String email, String senha){
        
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.senha.set(senha);
    }  
    
    public Integer getCodigo() {
        return this.codigo.get();
    }
    
    public void setCodigo(Integer value) {
        this.codigo.set(value);
    }
    
}
