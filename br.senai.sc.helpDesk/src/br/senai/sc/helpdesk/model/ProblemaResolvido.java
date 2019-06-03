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
public class ProblemaResolvido{
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private Problema problema = null;
    private Tecnico requerente = null;
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty area = new SimpleStringProperty();
    private final StringProperty dificuldade = new SimpleStringProperty();
    private final StringProperty urgencia = new SimpleStringProperty();
    private final IntegerProperty horaDeOcorrencia = new SimpleIntegerProperty();

    
    
    public ProblemaResolvido(){
        
    }
      
    public ProblemaResolvido(Integer codigo, String tipo, String area, String dificuldade,
            String urgencia, Integer horaDeOcorrencia, Tecnico objectTec,Problema objectPro){
        
        this.codigo.set(codigo);
        this.tipo.set(tipo);
        this.area.set(area);
        this.dificuldade.set(dificuldade);
        this.urgencia.set(urgencia);
        this.horaDeOcorrencia.set(horaDeOcorrencia);
        this.requerente = objectTec;
        this.problema = objectPro;
    }  
    
    public int getCodigo() {
        return this.codigo.get();
    }

    public void setCodigo(int value) {
        this.codigo.set(value);
    }

    public IntegerProperty codigoProperty() {
        return this.codigo;
    }
    
    public Problema getProblema() {
        return this.problema;
    }

    public void setProblema(Problema object) {
        this.problema = object;
    }
    
    public Tecnico getTecnico() {
        return this.requerente;
    }

    public void setTecnico(Tecnico tecnico) {
        this.requerente = tecnico;
    }
    
    
    public String getTipo() {
        return this.tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public StringProperty tipoProperty() {
        return this.tipo;
    }
    
    public String getArea() {
        return this.area.get();
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public StringProperty areaProperty() {
        return this.area;
    }
    
    public String getDiculdade() {
        return this.dificuldade.get();
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade.set(dificuldade);
    }

    public StringProperty dificuldadeProperty() {
        return this.dificuldade;
    }
    
    public String getUrgencia() {
        return this.urgencia.get();
    }

    public void setUrgencia(String urgencia) {
        this.urgencia.set(urgencia);
    }

    public StringProperty urgenciaProperty() {
        return this.urgencia;
    }
    
    public Integer getHoraDeOcorrencia() {
        return this.horaDeOcorrencia.get();
    }

    public void setHoraDeOcorrencia(Integer horaDeOcorrencia) {
        this.horaDeOcorrencia.set(horaDeOcorrencia);
    }

    public IntegerProperty horaDeOcorrenciaProperty() {
        return this.horaDeOcorrencia;
    }
}
