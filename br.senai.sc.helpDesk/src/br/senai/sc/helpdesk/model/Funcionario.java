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
public class Funcionario extends Usuario {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final StringProperty empresa = new SimpleStringProperty();
    private final StringProperty area = new SimpleStringProperty();

    
    public Funcionario(){
        
    }
    
    public Funcionario(Integer codigo,String nome,String senha,String email, String empresa,String area){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.senha.set(senha);
        this.email.set(email);
        this.area.set(area);
        this.empresa.set(empresa);
    }
    
    
    public String getArea() {
        return area.get();
    }

    public void setArea(String value) {
        area.set(value);
    }

    public StringProperty areaProperty() {
        return area;
    }
       
    public Integer getCodigo() {
        return this.codigo.get();
    }

    public void setCodigo(Integer value) {
        this.codigo.set(value);
    }

    public IntegerProperty codigoProperty() {
        return this.codigo;
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
}
