/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Senai
 */
public class Problema {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private Cliente requisitante = null;

    private final IntegerProperty dataEnvio = new SimpleIntegerProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    private final StringProperty empresa = new SimpleStringProperty();
    private final StringProperty urgencia = new SimpleStringProperty();
    private final BooleanProperty verificado = new SimpleBooleanProperty(false);

    private final StringProperty nomeCli = new SimpleStringProperty();
    private final StringProperty emailCli = new SimpleStringProperty();
    
    public Problema(){
        
    }
      
    public Problema(Integer codigo, Integer dataEnvio, String descricao, String empresa, Cliente object,String urgencia,Boolean verificado){
        
        this.codigo.set(codigo);
        this.dataEnvio.set(dataEnvio);
        this.descricao.set(descricao);
        this.empresa.set(empresa);
        this.requisitante = object;
        this.nomeCli.set(object.getNome());
        this.emailCli.set(object.getEmail());
        this.urgencia.set(urgencia);
        this.verificado.set(verificado);
        
    }  
    
    public boolean isVerificado() {
        return verificado.get();
    }

    public void setVerificado(boolean value) {
        verificado.set(value);
    }

    public BooleanProperty verificadoProperty() {
        return verificado;
    }
    
    
    public String getUrgencia() {
        return urgencia.get();
    }

    public void setUrgencia(String value) {
        urgencia.set(value);
    }

    public StringProperty urgenciaProperty() {
        return urgencia;
    }
    
    public String getEmpresa() {
        return this.empresa.get();
    }

    public void setEmpresa(String value) {
        this.empresa.set(value);
    }

    public StringProperty empresaProperty() {
        return this.empresa;
    }
    
    public Cliente getCliente() {
        return this.requisitante;
    }

    public void setCliente(Cliente object) {
        this.requisitante = object;
        this.nomeCli.set(object.getNome());
        this.emailCli.set(object.getEmail());
    }
    
    public String getNomeCli() {
        return this.nomeCli.get();
    }
    
    public void setNomeCli(String value) {
         this.nomeCli.set(value);
    }

    public StringProperty nomeCliProperty() {
        return this.nomeCli;
    }
    
     public String getEmailCli() {
        return this.emailCli.get();
    }

     public void setEmailCli(String value) {
         this.emailCli.set(value);
    }
     
    public StringProperty emailCliProperty() {
        return this.emailCli;
    }
    
    public Integer getCodigo() {
        return this.codigo.get();
    }

    public void setCodigo(Integer data) {
        this.codigo.set(data);
    }

    public IntegerProperty codigoPropery() {
        return this.codigo;
    }
    
    public Integer getDataEnvio() {
        return this.dataEnvio.get();
    }

    public void setDataEnvio(Integer data) {
        this.dataEnvio.set(data);
    }

    public IntegerProperty dataEnvioPropery() {
        return this.dataEnvio;
    }
    
    public String getDescricao() {
        return this.descricao.get();
    }

    public void setDescricao(String data) {
        this.descricao.set(data);
    }

    public StringProperty descricaoProperty() {
        return this.descricao;
    }
}

