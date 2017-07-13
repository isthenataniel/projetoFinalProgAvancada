/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import projetofinal.controles.PacienteControle;
import projetofinal.modelos.Paciente;

/**
 *
 * @author nataniel
 */
public class pacienteLista extends javax.swing.JFrame {

    private PacienteControle pacienteControle;
    DefaultTableModel model;
    /**
     * Creates new form pais
     */
    public pacienteLista() {
        initComponents();
        pacienteControle = new PacienteControle();
        pacienteTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carregarLista();
    }
    
    private void carregarLista() {
        try {
            model = (DefaultTableModel) pacienteTabela.getModel();
            for (Object[] row : pacienteControle.obterLista()) {
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pacienteLista = new javax.swing.JPanel();
        editar = new javax.swing.JButton();
        incluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        excluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pacienteTabela = new javax.swing.JTable();
        fechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        incluir.setText("Incluir");
        incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incluirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Paciente");

        excluir.setText("Excluir");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        pacienteTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Data nascimento", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(pacienteTabela);

        fechar.setText("Voltar");
        fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pacienteListaLayout = new javax.swing.GroupLayout(pacienteLista);
        pacienteLista.setLayout(pacienteListaLayout);
        pacienteListaLayout.setHorizontalGroup(
            pacienteListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pacienteListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pacienteListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addGroup(pacienteListaLayout.createSequentialGroup()
                        .addGroup(pacienteListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pacienteListaLayout.createSequentialGroup()
                                .addComponent(incluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pacienteListaLayout.setVerticalGroup(
            pacienteListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pacienteListaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pacienteListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incluir)
                    .addComponent(editar)
                    .addComponent(excluir)
                    .addComponent(fechar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pacienteLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pacienteLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        if (!validaRegistroSelecionado()) {
            return;
        }
        new paciente(obtemIdentificadorRegistroSelecionado()).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_editarActionPerformed

    private boolean validaRegistroSelecionado() {
        if (pacienteTabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
            return false;
        }
        return true;
    }
    
    private Integer obtemIdentificadorRegistroSelecionado() {
        Integer id = null;
        Object row = model.getDataVector().elementAt(pacienteTabela.getSelectedRow());
        id = (Integer) model.getValueAt(pacienteTabela.getSelectedRow(), 0);
        return id;
    }
    
    private void incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirActionPerformed
        // TODO add your handling code here:
        new paciente().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_incluirActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        // TODO add your handling code here:
        if (!validaRegistroSelecionado()) {
            return;
        }
        
        try {
            List<Paciente> emUso = pacienteControle.obtemPacienteEmUso(obtemIdentificadorRegistroSelecionado());
            if (emUso != null && emUso.size() > 0) {
                JOptionPane.showMessageDialog(null, "Registro não pode ser excluído pois está em uso!");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Integer opcao = JOptionPane.showConfirmDialog(null, "Você deseja mesmo excluir o registro selecionado?");
        if (opcao != null && opcao.equals(JOptionPane.OK_OPTION)) {
            pacienteControle.excluir(obtemIdentificadorRegistroSelecionado());
            model.removeRow(pacienteTabela.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
        }
    }//GEN-LAST:event_excluirActionPerformed

    private void fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_fecharActionPerformed

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
            java.util.logging.Logger.getLogger(pacienteLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pacienteLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pacienteLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pacienteLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new pacienteLista().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editar;
    private javax.swing.JButton excluir;
    private javax.swing.JButton fechar;
    private javax.swing.JButton incluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pacienteLista;
    private javax.swing.JTable pacienteTabela;
    // End of variables declaration//GEN-END:variables
}