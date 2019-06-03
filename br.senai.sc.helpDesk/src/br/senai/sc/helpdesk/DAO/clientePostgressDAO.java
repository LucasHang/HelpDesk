/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.BancoDeDados;
import br.senai.sc.helpdesk.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class clientePostgressDAO extends ConnectionFactory implements clienteDAO{

    @Override
    public void save(Cliente cliente) throws SQLException {
        String[] codigoGerado = {"codigoCli"};
        super.preparedStatementInitialize(
                "insert into cliente (nome, email, senha) values (?,?,?)",
                codigoGerado);
        super.prepared.setString(1, cliente.getNome());
        super.prepared.setString(2, cliente.getEmail());
        super.prepared.setString(3, cliente.getSenha());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo cliente");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            cliente.setCodigo(resultSetRows.getInt("codigoCli"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "update cliente set nome = ?, email = ?, senha = ? where codigoCli = ?");
        super.prepared.setString(1, cliente.getNome());
        super.prepared.setString(2, cliente.getEmail());
        super.prepared.setString(3, cliente.getSenha());
        super.prepared.setInt(4, cliente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do cliente");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "delete from cliente where codigoCli = ?");
        super.prepared.setInt(1, cliente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o cliente");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        List<Cliente> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from cliente");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Cliente(resultSetRows.getInt("codigoCli"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<Cliente> getClienteByNome(String nome) throws SQLException {
        List<Cliente>  rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from cliente where upper(nome) like ?");
        super.prepared.setString(1,"%"+nome.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Cliente(resultSetRows.getInt("codigoCli"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Cliente getClienteByEmail(String email) throws SQLException {
        Cliente cliente = null;
        super.preparedStatementInitialize("select * from cliente where upper(email) like ?");
        super.prepared.setString(1,"%"+email.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            cliente = (new Cliente(resultSetRows.getInt("codigoCli"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return cliente;
    }

    @Override
    public Cliente getClienteByCodigo(Integer codigo) throws SQLException {
        Cliente cliente = null;
        super.preparedStatementInitialize("select * from cliente where codigoCli = ?");
        super.prepared.setInt(1, codigo);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            cliente = (new Cliente(resultSetRows.getInt("codigoCli"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return cliente;
    }
    
}
