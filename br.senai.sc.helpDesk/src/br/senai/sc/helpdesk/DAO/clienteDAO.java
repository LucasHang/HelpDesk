/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface clienteDAO {
    
    public void save(Cliente conta)throws SQLException;
    
    public void update(Cliente conta)throws SQLException;
    
    public void delete(Cliente conta)throws SQLException;
    
    public List<Cliente> getAll()throws SQLException;
    
    public List<Cliente> getClienteByNome(String nome)throws SQLException;
    
    public Cliente getClienteByCodigo(Integer codigo)throws SQLException;
    
    public Cliente getClienteByEmail(String email)throws SQLException;
}
