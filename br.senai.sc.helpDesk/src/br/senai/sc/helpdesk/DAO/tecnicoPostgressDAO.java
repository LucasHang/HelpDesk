/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Tecnico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class tecnicoPostgressDAO extends ConnectionFactory implements tecnicoDAO{

    @Override
    public void save(Tecnico tecnico) throws SQLException {
        String[] codigoGerado = {"codigo"};
        super.preparedStatementInitialize(
                "insert into tecnico (nome, email, senha) values (?,?,?)",
                codigoGerado);
        super.prepared.setString(1, tecnico.getNome());
        super.prepared.setString(2, tecnico.getEmail());
        super.prepared.setString(3, tecnico.getSenha());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo tecnico");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            tecnico.setCodigo(resultSetRows.getInt("codigo"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Tecnico tecnico) throws SQLException {
        super.preparedStatementInitialize(
                "update tecnico set nome = ?, email = ?, senha = ? where codigo = ?");
        super.prepared.setString(1, tecnico.getNome());
        super.prepared.setString(2, tecnico.getEmail());
        super.prepared.setString(3, tecnico.getSenha());
        super.prepared.setInt(4, tecnico.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do tecnico");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Tecnico tecnico) throws SQLException {
        super.preparedStatementInitialize(
                "delete from tecnico where codigo = ?");
        super.prepared.setInt(1, tecnico.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o tecnico");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Tecnico> getAll() throws SQLException {
        List<Tecnico> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from tecnico");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Tecnico(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Tecnico getTecnicoByEmail(String email) throws SQLException {
        Tecnico tecnico = null;
        super.preparedStatementInitialize("select * from tecnico where upper(email) like ?");
        super.prepared.setString(1,"%"+email.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            tecnico = (new Tecnico(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return tecnico;
    }

    @Override
    public List<Tecnico> getTecnicoByNome(String nome) throws SQLException {
        List<Tecnico> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from tecnico where upper(nome) like ?");
        super.prepared.setString(1,"%"+nome.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Tecnico(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Tecnico getTecnicoByCodigo(Integer codigo) throws SQLException {
        Tecnico tecnico = null;
        super.preparedStatementInitialize("select * from tecnico where codigo = ?");
        super.prepared.setInt(1,codigo);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            tecnico = (new Tecnico(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("nome"),
                    resultSetRows.getString("email"),
                    resultSetRows.getString("senha")));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return tecnico;
    }
    
}
