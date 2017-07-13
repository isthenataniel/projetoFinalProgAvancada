/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static projetofinal.controles.Controle.obtemConsulta;
import projetofinal.modelos.Cidade;
import projetofinal.modelos.Pais;

/**
 *
 * @author root
 */
public class CidadeControle extends Controle<Cidade> {
    
    public CidadeControle() {
    }
    
    public Integer salvar(Cidade cidade) {
        try {
            if (cidade.getId() == null) {
                String sql = "INSERT INTO CIDADE2 (NOME, ESTADO_ID, IBGEID) VALUES(?,?,?)";
                Object[] parametros = {cidade.getNome(), cidade.getEstado_id(), cidade.getIbgeid()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE CIDADE2 SET NOME = ?, ESTADO_ID = ?, IBGEID = ? WHERE ID = ?";
                Object[] parametros = {cidade.getNome(), cidade.getEstado_id(), cidade.getIbgeid(), cidade.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Cidade obterCidade(Integer idCidade) throws SQLException {
        Cidade cidade = new Cidade();
        Object[] parametros = {idCidade};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM CIDADE2 "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            cidade.setId(result.getInt("ID"));
            cidade.setNome(result.getString("NOME"));
            cidade.setEstado_id(result.getInt("ESTADO_ID"));
            cidade.setIbgeid(result.getInt("IBGEID"));
        }
        return cidade;
    }
    
    public Integer excluir(Integer idCidade) {
        try {
            Object[] parametros = {idCidade};
            String sql = "DELETE FROM CIDADE2 WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Cidade> obtemTodasCidades() throws SQLException {
        List<Cidade> cidades = new ArrayList<Cidade>();
        ResultSet result = obtemConsulta("SELECT CIDADE2.*, ESTADO.NOME AS ESTADO_NOME FROM CIDADE2 INNER JOIN ESTADO ON ESTADO_ID = ESTADO.ID ORDER BY NOME ASC;");
        while (result.next()) {
            Cidade cidade = new Cidade();
            cidade.setId(result.getInt("ID"));
            cidade.setNome(result.getString("NOME"));
            cidade.setEstado_id(result.getInt("ESTADO_ID"));
            cidade.setIbgeid(result.getInt("IBGEID"));
            cidade.setEstadoNome(result.getString("ESTADO_NOME"));
            cidades.add(cidade);
        }
        return cidades;
    }
    
//    public void gravar(Cidade cidade) {
//        Session sessao = null;
//        try {
//            sessao = NewHibernateUtil.getSessionFactory().openSession();
//            Transaction transacao = sessao.beginTransaction();
//            System.out.println(cidade.getId());
//            System.out.println(cidade.getNome());
//            sessao.save(cidade);
//            transacao.commit();
//        } catch (Exception hibEx) {
//            hibEx.printStackTrace();
//        } finally {
//            sessao.close();
//        }
//    }
    
    public List<Cidade> obtemCidadeEmUso(Integer idCidade) throws SQLException {
        List<Cidade> cidades = new ArrayList<Cidade>();
        Object[] parametros = {idCidade};
        ResultSet result = obtemConsulta("SELECT * FROM PESSOAFISICA WHERE IDCIDADENASCIMENTO = ?", parametros);
        if (result != null) {
            while (result.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(result.getInt("ID"));
                cidades.add(cidade);
            }
        }
        return cidades;
    }
}
