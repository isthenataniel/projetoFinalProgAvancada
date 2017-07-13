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
import projetofinal.modelos.Estado;

/**
 *
 * @author root
 */
public class EstadoControle extends Controle<Estado> {
    
    public Integer salvar(Estado estado) {
        try {
            if (estado.getId() != null) {
                String sql = "INSERT INTO ESTADO (NOME, UF, PAIS_ID) VALUES(?,?,?)";
                Object[] parametros = {estado.getNome(), estado.getUf(), estado.getPais_id()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE ESTADO SET NOME = ?, UF = ?, PAIS_ID = ? WHERE ID = ?";
                Object[] parametros = {estado.getNome(), estado.getUf(), estado.getPais_id(), estado.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Estado obterEstado(Integer idEstado) throws SQLException {
        Estado estado = new Estado();
        Object[] parametros = {idEstado};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM ESTADO "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            estado.setId(result.getInt("ID"));
            estado.setNome(result.getString("NOME"));
            estado.setPais_id(result.getInt("PAIS_ID"));
            estado.setUf(result.getString("UF"));
        }
        return estado;
    }
    
    public Integer excluir(Integer idEstado) {
        try {
            Object[] parametros = {idEstado};
            String sql = "DELETE FROM ESTADO WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Estado> obtemTodosEstados() throws SQLException {
        List<Estado> estados = new ArrayList<Estado>();
        ResultSet result = obtemConsulta("SELECT ESTADO.*, PAIS.NOME AS PAIS_NOME FROM ESTADO INNER JOIN PAIS ON PAIS_ID = PAIS.ID ORDER BY NOME ASC;");
        while (result.next()) {
            Estado estado = new Estado();
            estado.setId(result.getInt("ID"));
            estado.setNome(result.getString("NOME"));
            estado.setPais_id(result.getInt("PAIS_ID"));
            estado.setUf(result.getString("UF"));
            estado.setPaisNome(result.getString("PAIS_NOME"));
            estados.add(estado);
        }
        return estados;
    }
    
    public List<Estado> obtemEstadoEmUso(Integer idEstado) throws SQLException {
        List<Estado> estados = new ArrayList<Estado>();
        Object[] parametros = {idEstado};
        ResultSet result = obtemConsulta("SELECT * FROM CIDADE WHERE ESTADO_ID = ?", parametros);
        while (result.next()) {
            Estado estado = new Estado();
            estado.setId(result.getInt("ID"));
            estados.add(estado);
        }
        return estados;
    }
}
