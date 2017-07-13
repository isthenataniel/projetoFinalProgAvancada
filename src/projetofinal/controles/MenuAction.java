/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class MenuAction implements ActionListener {

    String menu;

    public MenuAction(String menu) {
        this.menu = "projetofinal.interfaces."+menu+"Lista";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            javax.swing.JFrame classeLista = (javax.swing.JFrame) Class.forName(menu).newInstance();
            classeLista.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MenuAction.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
