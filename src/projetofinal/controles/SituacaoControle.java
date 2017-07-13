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
import projetofinal.modelos.Situacao;

/**
 *
 * @author root
 */
public class SituacaoControle extends Controle {
    
    public Integer salvar(Situacao situacao) {
        try {
            if (situacao.getId() == null) {
                String sql = "INSERT INTO SITUACAO (DESCRICAO) VALUES(?)";
                Object[] parametros = {situacao.getDescricao()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE SITUACAO SET DESCRICAO = ? WHERE ID = ?";
                Object[] parametros = {situacao.getDescricao(), situacao.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Situacao obterSituacao(Integer idSituacao) throws SQLException {
        Situacao situacao = new Situacao();
        Object[] parametros = {idSituacao};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM SITUACAO "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            situacao.setId(result.getInt("ID"));
            situacao.setDescricao(result.getString("DESCRICAO"));
        }
        return situacao;
    }
    
    public Integer excluir(Integer idSituacao) {
        try {
            Object[] parametros = {idSituacao};
            String sql = "DELETE FROM SITUACAO WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Situacao> obtemTodasSituacoes() throws SQLException {
        List<Situacao> situacaos = new ArrayList<Situacao>();
        ResultSet result = obtemConsulta("SELECT * FROM SITUACAO ORDER BY DESCRICAO ASC;");
        while (result.next()) {
            Situacao situacao = new Situacao();
            situacao.setId(result.getInt("ID"));
            situacao.setDescricao(result.getString("DESCRICAO"));
            situacaos.add(situacao);
        }
        return situacaos;
    }
}
