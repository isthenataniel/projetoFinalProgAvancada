/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controles;

import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author root
 */
public class DataUtils {
    
    public static String obtemDataFormatada(Date data) {
        try {
            ResultSet result = null;
            Object[] parametros = {data};
            String sql = "SELECT TO_CHAR(?::DATE, 'DD/MM/YYYY'::VARCHAR) AS DATA";
            result = Controle.obtemConsulta(sql, parametros);
            if (result.next()) {
                return result.getString("DATA");
            }
        } catch (Exception e) {
        }
        return "";
    }
    
}
