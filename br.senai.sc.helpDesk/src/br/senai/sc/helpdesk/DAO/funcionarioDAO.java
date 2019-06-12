/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface funcionarioDAO {
    
    public void save(Funcionario conta)throws SQLException;
    
    public void update(Funcionario conta)throws SQLException;
    
    public void delete(Funcionario conta)throws SQLException;
    
    public List<Funcionario> getAll()throws SQLException;
    
    public Funcionario getFuncionarioByEmail(String email)throws SQLException;
    
    public Funcionario getFuncionarioByCodigo(Integer codigo)throws SQLException;
}
