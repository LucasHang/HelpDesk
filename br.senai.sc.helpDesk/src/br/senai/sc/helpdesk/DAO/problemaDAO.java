/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Problema;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface problemaDAO {
    
    public void save(Problema conta) throws SQLException;
    
    public void update(Problema conta)throws SQLException;
    
    public void delete(Problema conta)throws SQLException;
    
    public List<Problema> getAll()throws SQLException;
    
    public List<Problema> getProblemaByClienteNome(String nome)throws SQLException;
    
    public Problema getProblemaByClienteEmail(String email)throws SQLException;
    
    public Problema getProblemaByCodigo(Integer codigo)throws SQLException;
    
    public List<Problema> getProblemaByDataEnvio(Integer data)throws SQLException;
   
}
