/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Senai
 */
public class Usuario {
    
    
    public final StringProperty nome = new SimpleStringProperty(); 
    public final StringProperty email = new SimpleStringProperty();
    public final StringProperty senha = new SimpleStringProperty();

    public String getNome() {
        return this.nome.get();
    }
    
    public void setNome(String nome) {
        this.nome.set(nome);
    }
    
    public StringProperty getNomeProperty() {
        return this.nome;
    }

    public String getEmail() {
        return this.email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty getEmailProperty() {
        return this.email;
    }

    public String getSenha() {
        return this.senha.get();
    }
    
    public void setSenha(String senha) {
        this.senha.set(senha);
    }
    
    public StringProperty getSenhaProperty() {
        return this.senha;
    }
     
     
    
}
