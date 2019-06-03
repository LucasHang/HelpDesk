/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Tecnico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface tecnicoDAO {
    
    public void save(Tecnico conta)throws SQLException;
    
    public void update(Tecnico conta)throws SQLException;
    
    public void delete(Tecnico conta)throws SQLException;
    
    public List<Tecnico> getAll()throws SQLException;
    
    public Tecnico getTecnicoByEmail(String email)throws SQLException;
    
    public Tecnico getTecnicoByCodigo(Integer codigo)throws SQLException;
    
    public List<Tecnico> getTecnicoByNome(String nome)throws SQLException;
}
