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
        String[] codigoGerado = {"codigo"};
        super.preparedStatementInitialize(
                "insert into problema (codigoCli, empresa, dataEnvio, descricao) values (?,?,?,?)",
                codigoGerado);
        super.prepared.setInt(1, problema.getCliente().getCodigo());
        super.prepared.setString(2, problema.getEmpresa());
        super.prepared.setInt(3, problema.getDataEnvio());
        super.prepared.setString(4, problema.getDescricao());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo problema");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            problema.setCodigo(resultSetRows.getInt("codigo"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Problema problema) throws SQLException{
        super.preparedStatementInitialize(
                "update problema set codigoCli = ?, empresa = ? ,dataEnvio = ?, descricao = ? where codigo = ?");
        super.prepared.setInt(1, problema.getCliente().getCodigo());
        super.prepared.setString(2, problema.getEmpresa());
        super.prepared.setInt(3, problema.getDataEnvio());
        super.prepared.setString(4, problema.getDescricao());
        super.prepared.setInt(5, problema.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do problema");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Problema problema)throws SQLException {
        super.preparedStatementInitialize(
                "delete from problema where codigo = ?");
        super.prepared.setInt(1, problema.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o problema");
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
            rows.add(new Problema(resultSetRows.getInt("codigo"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    resultSetRows.getString("empresa"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getNome(),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getEmail()));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<Problema> getProblemaByClienteNome(String nome)throws SQLException {
        List<Problema>  rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problema a, cliente b where a.codigoCli = b.codigo and upper(b.nome) like ?");
        super.prepared.setString(1,"%"+nome.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Problema(resultSetRows.getInt("codigo"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    resultSetRows.getString("empresa"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getNome(),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getEmail()));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Problema getProblemaByClienteEmail(String email) throws SQLException {
        Problema  problema = null;
        super.preparedStatementInitialize("select * from problema a, cliente b where a.codigoCli = b.codigo and upper(b.email) like ?");
        super.prepared.setString(1,"%"+email.toUpperCase()+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            problema = (new Problema(resultSetRows.getInt("codigo"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    resultSetRows.getString("empresa"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getNome(),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getEmail()));
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
            rows.add(new Problema(resultSetRows.getInt("codigo"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    resultSetRows.getString("empresa"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigo")),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getNome(),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getEmail()));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public Problema getProblemaByCodigo(Integer codigo) throws SQLException {
        Problema problema = null;
        super.preparedStatementInitialize("select * from problema where codigo = ?");
        super.prepared.setInt(1, codigo);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            problema = (new Problema(resultSetRows.getInt("codigo"),
                    resultSetRows.getInt("dataEnvio"),
                    resultSetRows.getString("descricao"),
                    resultSetRows.getString("empresa"),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getNome(),
                    DAOFactory.getClienteDAO().getClienteByCodigo(resultSetRows.getInt("codigoCli")).getEmail()));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return problema;
    }

    
    
}
