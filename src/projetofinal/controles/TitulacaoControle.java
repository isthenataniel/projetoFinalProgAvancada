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
import projetofinal.modelos.Titulacao;

/**
 *
 * @author root
 */
public class TitulacaoControle extends Controle {
    
    public Integer salvar(Titulacao titulacao) {
        try {
            if (titulacao.getId() != null) {
                String sql = "INSERT INTO TITULACAO (DESCRICAO) VALUES(?)";
                Object[] parametros = {titulacao.getDescricao()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE TITULACAO SET DESCRICAO = ? WHERE ID = ?";
                Object[] parametros = {titulacao.getDescricao(), titulacao.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Titulacao obterTitulacao(Integer idTitulacao) throws SQLException {
        Titulacao titulacao = new Titulacao();
        Object[] parametros = {idTitulacao};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM TITULACAO "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            titulacao.setId(result.getInt("ID"));
            titulacao.setDescricao(result.getString("DESCRICAO"));
        }
        return titulacao;
    }
    
    public Integer excluir(Integer idTitulacao) {
        try {
            Object[] parametros = {idTitulacao};
            String sql = "DELETE FROM TITULACAO WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Titulacao> obtemTodasTitulacoes() throws SQLException {
        List<Titulacao> titulacoes = new ArrayList<Titulacao>();
        ResultSet result = obtemConsulta("SELECT * FROM TITULACAO ORDER BY DESCRICAO ASC;");
        while (result.next()) {
            Titulacao titulacao = new Titulacao();
            titulacao.setId(result.getInt("ID"));
            titulacao.setDescricao(result.getString("DESCRICAO"));
            titulacoes.add(titulacao);
        }
        return titulacoes;
    }
}
