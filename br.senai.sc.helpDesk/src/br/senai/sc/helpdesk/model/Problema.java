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
public class Problema {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private Cliente requisitante = null;
    private final IntegerProperty dataEnvio = new SimpleIntegerProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    
    
    public Problema(){
        
    }
      
    public Problema(Integer codigo, Integer dataEnvio, String descricao, Cliente object){
        
        this.codigo.set(codigo);
        this.dataEnvio.set(dataEnvio);
        this.descricao.set(descricao);
        this.requisitante = object;
    }  
    
    
    public Cliente getCliente() {
        return this.requisitante;
    }

    public void setCliente(Cliente object) {
        this.requisitante = object;
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

    public StringProperty dataDescricaoProperty() {
        return this.descricao;
    }
}

