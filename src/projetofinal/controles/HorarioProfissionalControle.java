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
import projetofinal.modelos.HorarioProfissional;

/**
 *
 * @author root
 */
public class HorarioProfissionalControle extends PessoaControle {
    
    public Integer excluir(Integer idHorarioProfissional) {
        try {
            Object[] parametros = {false, idHorarioProfissional};

            String sql = "UPDATE HORARIOPROFISSIONAL SET ATIVO = ? WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public HorarioProfissional obterHorarioProfissional(Integer idHorarioProfissional) throws SQLException {
        HorarioProfissional horarioProfissional = new HorarioProfissional();
        Object[] parametros = {idHorarioProfissional};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM HORARIOPROFISSIONAL "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            horarioProfissional.setId(result.getInt("ID"));
            horarioProfissional.setIdProfissional(result.getInt("IDPROFISSIONAL"));
            horarioProfissional.setDia(result.getString("DIA").charAt(0));
            horarioProfissional.setInicio(result.getTime("INICIO"));
            horarioProfissional.setFim(result.getTime("FIM"));
            horarioProfissional.setIntervalo(result.getTime("INTERVALO"));
            horarioProfissional.setData(result.getDate("DATA"));
            horarioProfissional.setAtivo(result.getBoolean("ATIVO"));
            horarioProfissional.setHorarioSuspenso(result.getBoolean("HORARIOSUSPENSO"));
            horarioProfissional.setHorarioDiferenciado(result.getBoolean("HORARIODIFERENCIADO"));
        }
        return horarioProfissional;
    }
    
    public List<HorarioProfissional> obtemHorariosDoProfissional(Integer idProfissional) throws SQLException {
        List<HorarioProfissional> horarios = new ArrayList<HorarioProfissional>();
        Object[] parametros = {idProfissional};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM HORARIOPROFISSIONAL "
                                       + " WHERE IDPROFISSIONAL = ?"
                                          + "AND (ATIVO IS TRUE OR ATIVO IS NULL)", 
                                        parametros);
        
        while (result.next()) {
            HorarioProfissional horarioProfissional = new HorarioProfissional();
            horarioProfissional.setId(result.getInt("ID"));
            horarioProfissional.setIdProfissional(result.getInt("IDPROFISSIONAL"));
            horarioProfissional.setDia(result.getString("DIA") != null ? result.getString("DIA").charAt(0) : null);
            horarioProfissional.setInicio(result.getTime("INICIO"));
            horarioProfissional.setFim(result.getTime("FIM"));
            horarioProfissional.setIntervalo(result.getTime("INTERVALO"));
            horarioProfissional.setData(result.getDate("DATA"));
            horarioProfissional.setAtivo(result.getBoolean("ATIVO"));
            horarioProfissional.setHorarioSuspenso(result.getBoolean("HORARIOSUSPENSO"));
            horarioProfissional.setHorarioDiferenciado(result.getBoolean("HORARIODIFERENCIADO"));
            horarios.add(horarioProfissional);
        }
        return horarios;
    }
    
    public Integer salvar(HorarioProfissional horarioProfissional) throws SQLException {
        if (horarioProfissional.getId() != null) {
            String sql = "UPDATE HORARIOPROFISSIONAL SET IDPROFISSIONAL = ?,"
                                                    + "DIA = ?,"
                                                    + "INICIO = ?,"
                                                    + "FIM = ?,"
                                                    + "INTERVALO = ?,"
                                                    + "DATA = ?,"
                                                    + "ATIVO = ?,"
                                                    + "HORARIOSUSPENSO = ?,"
                                                    + "HORARIODIFERENCIADO = ? "
                                              + "WHERE ID = ?";
            Object[] parametros = {horarioProfissional.getIdProfissional(), 
                                   horarioProfissional.getDia(), 
                                   horarioProfissional.getInicio(),
                                   horarioProfissional.getFim(),
                                   horarioProfissional.getIntervalo(),
                                   horarioProfissional.getData(),
                                   horarioProfissional.isAtivo(),
                                   horarioProfissional.isHorarioSuspenso(),
                                   horarioProfissional.isHorarioDiferenciado(),
                                   horarioProfissional.getId()};
            return executaQuery(sql, parametros);
        } else {
            String sql = "INSERT INTO HORARIOPROFISSIONAL (IDPROFISSIONAL,DIA,INICIO,FIM,INTERVALO,DATA,ATIVO,HORARIOSUSPENSO,HORARIODIFERENCIADO) VALUES(?,?,?,?,?,?,?,?,?)";
            Object[] parametros = {horarioProfissional.getIdProfissional(), 
                                   horarioProfissional.getDia(), 
                                   horarioProfissional.getInicio(),
                                   horarioProfissional.getFim(),
                                   horarioProfissional.getIntervalo(),
                                   horarioProfissional.getData(),
                                   horarioProfissional.isAtivo(),
                                   horarioProfissional.isHorarioSuspenso(),
                                   horarioProfissional.isHorarioDiferenciado()};
            
            return executaQuery(sql, parametros);
        }
    }
}
