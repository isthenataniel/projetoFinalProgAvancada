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
import projetofinal.modelos.Menu;

/**
 *
 * @author root
 */
public class MenuControle extends Controle {
    
    public List<Menu> obterTodosMenus() throws SQLException {
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM MENU"
                                        + " WHERE ATIVO IS TRUE AND IDMENUPAI IS NULL"
                                        + " ORDER BY TITULO, IDMENUPAI");
        List<Menu> menus = new ArrayList<Menu>(); 
        while (result.next()) {
            Menu menu = new Menu();
            menu.setId(result.getInt("ID"));
            menu.setIdMenuPai(result.getInt("IDMENUPAI"));
            menu.setTitulo(result.getString("TITULO"));
            menu.setRota(result.getString("ROTA"));
            menu.setMenuFilhos(obterTodosMenusFilhos(menu.getId()));
            menus.add(menu);
        }
        
        
        return menus;
    }
    
    public List<Menu> obterTodosMenusFilhos(Integer idMenu) throws SQLException {
        Object[] parametros = {idMenu};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM MENU"
                                        + " WHERE ATIVO IS TRUE AND IDMENUPAI = ?"
                                        + " ORDER BY TITULO, IDMENUPAI", parametros);
        List<Menu> menus = new ArrayList<Menu>(); 
        while (result.next()) {
            Menu menu = new Menu();
            menu.setId(result.getInt("ID"));
            menu.setIdMenuPai(result.getInt("IDMENUPAI"));
            menu.setTitulo(result.getString("TITULO"));
            menu.setRota(result.getString("ROTA"));
            menus.add(menu);
        }
        
        return menus;
    }

}
