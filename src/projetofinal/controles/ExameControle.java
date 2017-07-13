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
import projetofinal.modelos.Exame;

/**
 *
 * @author root
 */
public class ExameControle extends Controle<Exame> {
    
    public Integer salvar(Exame exame) {
        try {
            if (exame.getId() == null) {
                String sql = "INSERT INTO EXAME (NOME, CODIGO) VALUES(?,?)";
                Object[] parametros = {exame.getNome(), exame.getCodigo()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE EXAME SET NOME = ?, CODIGO = ? WHERE ID = ?";
                Object[] parametros = {exame.getNome(), exame.getCodigo(), exame.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Exame obterExame(Integer idExame) throws SQLException {
        Exame exame = new Exame();
        Object[] parametros = {idExame};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM EXAME "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            exame.setId(result.getInt("ID"));
            exame.setNome(result.getString("NOME"));
            exame.setCodigo(result.getString("CODIGO"));
        }
        return exame;
    }
    
    public Integer excluir(Integer idExame) {
        try {
            Object[] parametros = {idExame};
            String sql = "DELETE FROM EXAME WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Exame> obtemTodosExames() throws SQLException {
        List<Exame> exames = new ArrayList<Exame>();
        ResultSet result = obtemConsulta("SELECT * FROM EXAME ORDER BY NOME ASC;");
        while (result.next()) {
            Exame exame = new Exame();
            exame.setId(result.getInt("ID"));
            exame.setNome(result.getString("NOME"));
            exame.setCodigo(result.getString("CODIGO"));
            exames.add(exame);
        }
        return exames;
    }
    
    public List<Exame> obtemExameEmUso(Integer idExame) throws SQLException {
        List<Exame> exames = new ArrayList<Exame>();
        Object[] parametros = {idExame};
        ResultSet result = obtemConsulta("SELECT * FROM EXAMEPACIENTE WHERE IDEXAME = ?", parametros);
        if (result != null) {
            while (result.next()) {
                Exame exame = new Exame();
                exame.setId(result.getInt("ID"));
                exames.add(exame);
            }
        }
        return exames;
    }
}
