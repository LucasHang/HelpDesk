/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk.DAO;

import br.senai.sc.helpdesk.model.Cliente;
import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class problemaResolvidoPostgressDAO extends ConnectionFactory implements problemaResolvidoDAO {

    @Override
    public void save(ProblemaResolvido problema) throws SQLException {
         String[] codigoGerado = {"codigo"};
        super.preparedStatementInitialize(
                "insert into problemaResolvido (codigoPro, status, codigoTec, tipo, area, dificuldade, urgencia, descResolucao) values (?,?,?,?,?,?,?,?)",
                codigoGerado);
        super.prepared.setInt(1, problema.getProblema().getCodigo());
        super.prepared.setInt(2, problema.getTecnico().getCodigo());
        super.prepared.setString(3, problema.getTipo());
        super.prepared.setString(4, problema.getArea());
        super.prepared.setString(5, problema.getDiculdade());
        super.prepared.setString(6, problema.getUrgencia());
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
    public void update(ProblemaResolvido problema) throws SQLException {
        super.preparedStatementInitialize(
                "update problemaResolvido set codigoPro = ?, codigoTec = ?, status = ?, tipo = ?, area = ?, dificuldade = ?, "
                        + "urgencia = ?, descResolucao = ? where codigo = ?");
        super.prepared.setInt(1, problema.getProblema().getCodigo());
        super.prepared.setInt(2, problema.getTecnico().getCodigo());
        super.prepared.setString(3, problema.getStatus());
        super.prepared.setString(4, problema.getTipo());
        super.prepared.setString(5, problema.getArea());
        super.prepared.setString(6, problema.getDiculdade());
        super.prepared.setString(7, problema.getDescResolucao());
        super.prepared.setString(8, problema.getUrgencia());
        super.prepared.setInt(9, problema.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível aletrar as informações do problema");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(ProblemaResolvido problema) throws SQLException {
        super.preparedStatementInitialize(
                "delete from problemaResolvido where codigo = ?");
        super.prepared.setInt(1, problema.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o problema");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<ProblemaResolvido> getAll() throws SQLException {
        List<ProblemaResolvido> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problemaResolvido");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ProblemaResolvido(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("tipo"),
                    resultSetRows.getString("area"),
                    resultSetRows.getString("dificuldade"),
                    resultSetRows.getString("urgencia"),
                    resultSetRows.getString("descResolucao"),
                    resultSetRows.getString("status"),
                    DAOFactory.getTecnicoDAO().getTecnicoByCodigo(resultSetRows.getInt("codigoTec")),
                    DAOFactory.getProblemaDAO().getProblemaByCodigo(resultSetRows.getInt("codigoPro"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<ProblemaResolvido> getProblemaResolvidoByTecnicoNome(String nome) throws SQLException {
        List<ProblemaResolvido> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problemaResolvido a, tecnico b where a.codigoTec = b.codigo and upper(b.nome) like ?");
        super.prepared.setString(1,"%"+nome+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ProblemaResolvido(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("tipo"),
                    resultSetRows.getString("area"),
                    resultSetRows.getString("dificuldade"),
                    resultSetRows.getString("urgencia"),
                    resultSetRows.getString("descResolucao"),
                    resultSetRows.getString("status"),
                    DAOFactory.getTecnicoDAO().getTecnicoByCodigo(resultSetRows.getInt("codigoTec")),
                    DAOFactory.getProblemaDAO().getProblemaByCodigo(resultSetRows.getInt("codigoPro"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<ProblemaResolvido> getProblemaResolvidoByUrgencia(String urgencia) throws SQLException {
        List<ProblemaResolvido> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problemaResolvido where urgencia like ?");
        super.prepared.setString(1,"%"+urgencia+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ProblemaResolvido(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("tipo"),
                    resultSetRows.getString("area"),
                    resultSetRows.getString("dificuldade"),
                    resultSetRows.getString("urgencia"),
                    resultSetRows.getString("descResolucao"),
                    resultSetRows.getString("status"),
                    DAOFactory.getTecnicoDAO().getTecnicoByCodigo(resultSetRows.getInt("codigoTec")),
                    DAOFactory.getProblemaDAO().getProblemaByCodigo(resultSetRows.getInt("codigoPro"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }

    @Override
    public List<ProblemaResolvido> getProblemaResolvidoByEmpresaEArea(String empresa, String area) throws SQLException {
        List<ProblemaResolvido>  rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from problema a, problemaResolvido b where a.codigo = b.codigoPro and upper(a.empresa) like ? and b.area like ?");
        super.prepared.setString(1, empresa.toUpperCase());
        super.prepared.setString(2, area);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ProblemaResolvido(resultSetRows.getInt("codigo"),
                    resultSetRows.getString("tipo"),
                    resultSetRows.getString("area"),
                    resultSetRows.getString("dificuldade"),
                    resultSetRows.getString("urgencia"),
                    resultSetRows.getString("descResolucao"),
                    resultSetRows.getString("status"),
                    DAOFactory.getTecnicoDAO().getTecnicoByCodigo(resultSetRows.getInt("codigoTec")),
                    DAOFactory.getProblemaDAO().getProblemaByCodigo(resultSetRows.getInt("codigoPro"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();

        return rows;
    }
    
    
}
