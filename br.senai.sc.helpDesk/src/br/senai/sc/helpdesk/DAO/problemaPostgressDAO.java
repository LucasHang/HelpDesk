/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Problema;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class problemaPostgressDAO extends ConnectionFactory implements problemaDAO{

    @Override
    public void save(Problema problema)throws SQLException {
        String[] codigoGerado = {"codigoPro"};
        super.preparedStatementInitialize(
                "insert into problema (codigoCli, dataEnvio, descricao) values (?,?,?)",
                codigoGerado);
        super.prepared.setInt(1, problema.getCliente().getCodigo());
        super.prepared.setInt(2, problema.getDataEnvio());
        super.prepared.setString(3, problema.getDescricao());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo cliente");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            problema.setCodigo(resultSetRows.getInt("codigoPro"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Problema problema) throws SQLException{
        super.preparedStatementInitialize(
                "update problema set codigoCli = ?, dataEnvio = ?, descricao = ? where codigoPro = ?");
        super.prepared.setInt(1, problema.getCliente().getCodigo());
        super.prepared.setInt(2, problema.getDataEnvio());
        super.prepared.setString(3, problema.getDescricao());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do cliente");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Problema problema)throws SQLException {
        super.preparedStatementInitialize(
                "delete from problema where codigoPro = ?");
        super.prepared.setInt(1, problema.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o cliente");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Problema> getAll()throws SQLException {
        List<Problema> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problema");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Problema(resultSetRows.getInt("codigoPro"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<Problema> getProblemaByClienteNome(String nome)throws SQLException {
        List<Problema>  rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problema a, cliente b where a.codigoCli = b.codigoCli and upper(b.nome) like ?");
        super.prepared.setString(1,"%"+nome.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Problema(resultSetRows.getInt("codigoPro"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Problema getProblemaByClienteEmail(String email) throws SQLException {
        Problema  problema = null;
        super.preparedStatementInitialize("select * from problema a, cliente b where a.codigoCli = b.codigoCli and upper(b.email) like ?");
        super.prepared.setString(1,"%"+email.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            problema = (new Problema(resultSetRows.getInt("codigoPro"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return problema;
    }

    @Override
    public List<Problema> getProblemaByDataEnvio(Integer data) throws SQLException {
        List<Problema>  rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problema where dataEnvio = ?");
        super.prepared.setInt(1, data);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Problema(resultSetRows.getInt("codigoPro"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Problema getProblemaByCodigo(Integer codigo) throws SQLException {
        Problema problema = null;
        super.preparedStatementInitialize("select * from cliente where codigoCli = ?");
        super.prepared.setInt(1, codigo);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            problema = (new Problema(resultSetRows.getInt("codigoPro"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return problema;
    }
    
}