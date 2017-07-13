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
import projetofinal.modelos.EstadoCivil;

/**
 *
 * @author root
 */
public class EstadoCivilControle extends Controle {
    
    public Integer salvar(EstadoCivil estadoCivil) {
        try {
            if (estadoCivil.getId() != null) {
                String sql = "INSERT INTO ESTADOCIVIL (DESCRICAO) VALUES(?)";
                Object[] parametros = {estadoCivil.getDescricao()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE ESTADOCIVIL SET DESCRICAO = ? WHERE ID = ?";
                Object[] parametros = {estadoCivil.getDescricao(), estadoCivil.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public EstadoCivil obterEstadoCivil(Integer idEstadoCivil) throws SQLException {
        EstadoCivil estadoCivil = new EstadoCivil();
        Object[] parametros = {idEstadoCivil};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM ESTADOCIVIL "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            estadoCivil.setId(result.getInt("ID"));
            estadoCivil.setDescricao(result.getString("DESCRICAO"));
        }
        return estadoCivil;
    }
    
    public Integer excluir(Integer idEstadoCivil) {
        try {
            Object[] parametros = {idEstadoCivil};
            String sql = "DELETE FROM ESTADOCIVIL WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<EstadoCivil> obtemTodosEstadosCivil() throws SQLException {
        List<EstadoCivil> titulacoes = new ArrayList<EstadoCivil>();
        ResultSet result = obtemConsulta("SELECT * FROM ESTADOCIVIL ORDER BY DESCRICAO ASC;");
        while (result.next()) {
            EstadoCivil estadoCivil = new EstadoCivil();
            estadoCivil.setId(result.getInt("ID"));
            estadoCivil.setDescricao(result.getString("DESCRICAO"));
            titulacoes.add(estadoCivil);
        }
        return titulacoes;
    }
}
