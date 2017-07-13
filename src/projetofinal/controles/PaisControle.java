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
import projetofinal.modelos.Pais;

/**
 *
 * @author root
 */
public class PaisControle extends Controle<Pais> {
    
    public Integer salvar(Pais pais) {
        try {
            if (pais.getId() != null) {
                String sql = "INSERT INTO PAIS (NOME, NACIONALIDADE) VALUES(?,?)";
                Object[] parametros = {pais.getNome(), pais.getNacionalidade()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE PAIS SET NOME = ?, NACIONALIDADE = ? WHERE ID = ?";
                Object[] parametros = {pais.getNome(), pais.getNacionalidade(), pais.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Pais obterPais(Integer idPais) throws SQLException {
        Pais pais = new Pais();
        Object[] parametros = {idPais};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM PAIS "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            pais.setId(result.getInt("ID"));
            pais.setNome(result.getString("NOME"));
            pais.setNacionalidade(result.getString("NACIONALIDADE"));
        }
        return pais;
    }
    
    public Integer excluir(Integer idPais) {
        try {
            Object[] parametros = {idPais};
            String sql = "DELETE FROM PAIS WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Pais> obtemTodosPaiss() throws SQLException {
        List<Pais> paiss = new ArrayList<Pais>();
        ResultSet result = obtemConsulta("SELECT * FROM PAIS ORDER BY NOME ASC;");
        while (result.next()) {
            Pais pais = new Pais();
            pais.setId(result.getInt("ID"));
            pais.setNome(result.getString("NOME"));
            pais.setNacionalidade(result.getString("NACIONALIDADE"));
            paiss.add(pais);
        }
        return paiss;
    }
    
    public List<Pais> obtemPaisEmUso(Integer idPais) throws SQLException {
        List<Pais> paiss = new ArrayList<Pais>();
        Object[] parametros = {idPais};
        ResultSet result = obtemConsulta("SELECT * FROM ESTADO WHERE PAIS_ID = ?", parametros);
        while (result.next()) {
            Pais pais = new Pais();
            pais.setId(result.getInt("ID"));
            paiss.add(pais);
        }
        return paiss;
    }
}
