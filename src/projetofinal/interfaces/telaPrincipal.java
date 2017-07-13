/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projetofinal.controles.ConsultaControle;
import projetofinal.controles.DataUtils;
import projetofinal.controles.MenuAction;
import projetofinal.controles.MenuControle;
import projetofinal.controles.ProfissionalControle;
import projetofinal.modelos.ComboItem;
import projetofinal.modelos.Consulta;
import projetofinal.modelos.Profissional;
import projetofinal.modelos.Menu;

/**
 *
 * @author root
 */
public class telaPrincipal extends javax.swing.JFrame {

    private ProfissionalControle profissionalControle;
    private ConsultaControle consultaControle;
    private MenuControle menuControle;
    DefaultTableModel modelConsultas;
    /**
     * Creates new form telaPrincipal
     */
    public telaPrincipal() {
        initComponents();
        iniciarControles();
        
        carregarFiltros();
        carregarLista();
        carregarMenu();
    }
    
    private void iniciarControles() {
        consultaControle = new ConsultaControle();
        profissionalControle = new ProfissionalControle();
        menuControle = new MenuControle();
    }
    
    private void carregarFiltros() {
        dataFiltro.setText(DataUtils.obtemDataFormatada(new Date()));
        try {
            profissionalFiltro.addItem(new ComboItem(null, "-- Selecione --"));
            List<Profissional> profissionais = profissionalControle.obtemTodosTodosProfissionais();
            
            for (Profissional profissional : profissionais) {
                ComboItem comboItem = new ComboItem(profissional.getId(), profissional.getNome());
                profissionalFiltro.addItem(comboItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void carregarLista() {
        try {
            modelConsultas = (DefaultTableModel) tabelaConsultas.getModel();
            for (Consulta consulta : consultaControle.obtemTodasConsultas()) {
                Object[] row  = {consulta.getId(), consulta.getDataFormatada(), consulta.getPaciente().getNome(), consulta.getProfissional().getNome(), consulta.getSituacao().getDescricao()};
                modelConsultas.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean validaRegistroSelecionado() {
        if (tabelaConsultas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
            return false;
        }
        return true;
    }
    
    private Integer obtemIdentificadorRegistroSelecionado() {
        Integer id = null;
        Object row = modelConsultas.getDataVector().elementAt(tabelaConsultas.getSelectedRow());
        id = (Integer) modelConsultas.getValueAt(tabelaConsultas.getSelectedRow(), 0);
        return id;
    }
    
    public void carregarMenu() {
        try {
            List<Menu> menus = menuControle.obterTodosMenus();
            for (Menu menu : menus) {
                JMenu jmenu = new JMenu();
                jmenu.setText(menu.getTitulo());
                for (Menu menuFilho : menu.getMenuFilhos()) {
                    JMenuItem menuItem = new JMenuItem();
                    menuItem.setText(menuFilho.getTitulo());
                    menuItem.addActionListener(new MenuAction(menuFilho.getRota()));
                    jmenu.add(menuItem);
                }
                barraMenu.add(jmenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void imprimirLista() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoPaciente = new javax.swing.JButton();
        botaoProfissional = new javax.swing.JButton();
        botaoConsulta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultas = new javax.swing.JTable();
        dataFiltro = new javax.swing.JTextField();
        profissionalFiltro = new javax.swing.JComboBox<>();
        imprimirLista = new javax.swing.JButton();
        excluirConsulta = new javax.swing.JButton();
        editarConsulta = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        botaoPaciente.setText("Paciente");
        botaoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPacienteActionPerformed(evt);
            }
        });

        botaoProfissional.setText("Profissional");
        botaoProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProfissionalActionPerformed(evt);
            }
        });

        botaoConsulta.setText("Consulta");
        botaoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConsultaActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));

        tabelaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Paciente", "Profissional", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaConsultas);

        dataFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataFiltroActionPerformed(evt);
            }
        });

        profissionalFiltro.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        profissionalFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profissional", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        profissionalFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profissionalFiltroActionPerformed(evt);
            }
        });

        imprimirLista.setText("Imprimir");
        imprimirLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirListaActionPerformed(evt);
            }
        });

        excluirConsulta.setText("Excluir");
        excluirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirConsultaActionPerformed(evt);
            }
        });

        editarConsulta.setText("Editar");
        editarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dataFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profissionalFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imprimirLista, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(profissionalFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(imprimirLista)
                        .addComponent(excluirConsulta)
                        .addComponent(editarConsulta))
                    .addComponent(dataFiltro))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        barraMenu.add(jMenu1);
        barraMenu.add(jMenu2);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConsultaActionPerformed
        // TODO add your handling code here:
        new consulta(this).setVisible(true);
    }//GEN-LAST:event_botaoConsultaActionPerformed

    private void botaoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPacienteActionPerformed
        // TODO add your handling code here:
        new pacienteLista().setVisible(true);
    }//GEN-LAST:event_botaoPacienteActionPerformed

    private void botaoProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProfissionalActionPerformed
        // TODO add your handling code here:
        new profissionalLista().setVisible(true);
    }//GEN-LAST:event_botaoProfissionalActionPerformed

    private void profissionalFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profissionalFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profissionalFiltroActionPerformed

    private void dataFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataFiltroActionPerformed

    private void imprimirListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirListaActionPerformed
        // TODO add your handling code here:
        try {
            if (tabelaConsultas.getSelectedRow() == -1) {
                
                JRResultSetDataSource relatResult = new JRResultSetDataSource(consultaControle.obtemConsultaRelatorio());
                JasperPrint jpPrint;
                jpPrint = JasperFillManager.fillReport("/home/nataniel/NetBeansProjects/ProjetoFinal/src/projetofinal/relatorios/listaConsulta.jasper", new HashMap(), relatResult);
                JasperViewer jpViewer = new JasperViewer (jpPrint , false); //false - não encerra a aplicação ao fechar a janela
                jpViewer.setVisible(true);
                jpViewer.toFront(); //apresenta o relatório acima das outras janelas
                return;
            }
        } catch (JRException ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_imprimirListaActionPerformed

    private void excluirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirConsultaActionPerformed
        // TODO add your handling code here:
        if (!validaRegistroSelecionado()) {
            return;
        }
        
        Integer opcao = JOptionPane.showConfirmDialog(null, "Você deseja mesmo excluir o registro selecionado?");
        if (opcao != null && opcao.equals(JOptionPane.OK_OPTION)) {
            consultaControle.excluir(obtemIdentificadorRegistroSelecionado());
            modelConsultas.removeRow(tabelaConsultas.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
        }
    }//GEN-LAST:event_excluirConsultaActionPerformed

    private void editarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarConsultaActionPerformed
        // TODO add your handling code here:
        if (!validaRegistroSelecionado()) {
            return;
        }
        
        new consulta(this, obtemIdentificadorRegistroSelecionado()).setVisible(true);
    }//GEN-LAST:event_editarConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new telaPrincipal().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton botaoConsulta;
    private javax.swing.JButton botaoPaciente;
    private javax.swing.JButton botaoProfissional;
    private javax.swing.JTextField dataFiltro;
    private javax.swing.JButton editarConsulta;
    private javax.swing.JButton excluirConsulta;
    private javax.swing.JButton imprimirLista;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Object> profissionalFiltro;
    private javax.swing.JTable tabelaConsultas;
    // End of variables declaration//GEN-END:variables
}
