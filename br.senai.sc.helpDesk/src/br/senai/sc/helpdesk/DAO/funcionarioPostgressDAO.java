/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class funcionarioPostgressDAO extends ConnectionFactory implements funcionarioDAO {

    @Override
    public void save(Funcionario funcionario) throws SQLException {
        String[] codigoGerado = {"codigo"};
        super.preparedStatementInitialize(
                "insert into funcionario (nome, email, senha, area, empresa) values (?,?,?,?,?)",
                codigoGerado);
        super.prepared.setString(1, funcionario.getNome());
        super.prepared.setString(2, funcionario.getEmail());
        super.prepared.setString(3, funcionario.getSenha());
        super.prepared.setString(4, funcionario.getArea());
        super.prepared.setString(5, funcionario.getEmpresa());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo funcionario");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            funcionario.setCodigo(resultSetRows.getInt("codigo"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        super.preparedStatementInitialize(
                "update funcionario set nome = ?, email = ?, senha = ?, area = ?, empresa= ? where codigo = ?");
        super.prepared.setString(1, funcionario.getNome());
        super.prepared.setString(2, funcionario.getEmail());
        super.prepared.setString(3, funcionario.getSenha());
        super.prepared.setString(4, funcionario.getArea());
        super.prepared.setString(5, funcionario.getEmpresa());
        super.prepared.setInt(6, funcionario.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do funcionario");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Funcionario funcionario) throws SQLException {
        super.preparedStatementInitialize(
                "delete from funcionario where codigo = ?");
        super.prepared.setInt(1, funcionario.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o funcionario");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Funcionario> getAll() throws SQLException {
        List<Funcionario> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from funcionario");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Funcionario(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha"),
                    resultSetRows.getString("area"),
                    resultSetRows.getString("empresa")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Funcionario getFuncionarioByEmail(String email) throws SQLException {
       Funcionario funcionario = null;
        super.preparedStatementInitialize("select * from funcionario where upper(email) like ?");
        super.prepared.setString(1,"%"+email.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            funcionario = (new Funcionario(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("senha"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("empresa"),
                    resultSetRows.getString("area")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return funcionario;
    }
    
}
