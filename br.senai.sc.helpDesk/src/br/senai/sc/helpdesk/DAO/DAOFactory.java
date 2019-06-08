/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

/**
 *
 * @author Senai
 */
public class DAOFactory {
    
    public static clienteDAO getClienteDAO(){
        return new clientePostgressDAO();
    }
     public static tecnicoDAO getTecnicoDAO(){
        return new tecnicoPostgressDAO();
    }
      public static problemaDAO getProblemaDAO(){
        return new problemaPostgressDAO();
    }
      public static problemaResolvidoDAO getProblemaResolvidoDAO(){
        return new problemaResolvidoPostgressDAO();
    }
      public static funcionarioDAO getFuncionarioDAO(){
        return new funcionarioPostgressDAO();
    }
}
