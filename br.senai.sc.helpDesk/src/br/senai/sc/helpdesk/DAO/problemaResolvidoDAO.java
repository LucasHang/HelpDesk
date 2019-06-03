/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface problemaResolvidoDAO {
    
    public void save(ProblemaResolvido problema) throws SQLException;
    
    public void update(ProblemaResolvido problema)throws SQLException;
    
    public void delete(ProblemaResolvido problema)throws SQLException;
    
    public List<ProblemaResolvido> getAll()throws SQLException;
    
    public List<ProblemaResolvido> getProblemaResolvidoByTecnicoNome(String nome)throws SQLException;
    
    public List<ProblemaResolvido> getProblemaResolvidoByUrgencia(String urgencia)throws SQLException;
}
