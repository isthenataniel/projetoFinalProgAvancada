/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import projetofinal.controles.CidadeControle;
import projetofinal.controles.EstadoControle;
import projetofinal.modelos.Cidade;
import projetofinal.modelos.ComboItem;
import projetofinal.modelos.Estado;

/**
 *
 * @author nataniel
 */
public class cidade extends javax.swing.JFrame {

    private Cidade cidade;
    private CidadeControle cidadeControle;
    private EstadoControle estadoControle;
    /**
     * Creates new form pais
     */
    public cidade() {
        initComponents();
        carregarControles();
        carregaCombo();
    }
    
    public cidade(Integer idCidade) {
        initComponents();
        carregarControles();
        try {
            cidade = cidadeControle.obterCidade(idCidade);
            nome.setText(cidade.getNome());
            ibgeid.setText(cidade.getIbgeid().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        carregaCombo();
    }

    private void carregarControles() {
        cidade = new Cidade();
        cidadeControle = new CidadeControle();
        estadoControle = new EstadoControle();
    }
    
    public void carregaCombo() {
        try {
            idEstado.addItem(new ComboItem(null, "-- Selecione --"));
            List<Estado> estados = estadoControle.obtemTodosEstados();
            for (Estado estado : estados) {
                ComboItem comboItem = new ComboItem(estado.getId(), estado.getNome());
                idEstado.addItem(comboItem);
                if (cidade.getEstado_id()!= null && cidade.getEstado_id().equals(estado.getId())) {
                    idEstado.setSelectedItem(comboItem);
                }
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

        jPanel1 = new javax.swing.JPanel();
        nome = new javax.swing.JTextField();
        ibgeid = new javax.swing.JTextField();
        cenelar = new javax.swing.JButton();
        salvar = new javax.swing.JButton();
        idEstado = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nome", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        ibgeid.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IBGE", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        ibgeid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ibgeidActionPerformed(evt);
            }
        });

        cenelar.setText("Cancelar");
        cenelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cenelarActionPerformed(evt);
            }
        });

        salvar.setText("Salvar");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });

        idEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Cidade");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cenelar))
                    .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(ibgeid, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(146, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ibgeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cenelar)
                    .addComponent(salvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void ibgeidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ibgeidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ibgeidActionPerformed

    private void cenelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cenelarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new cidadeLista().setVisible(true);
    }//GEN-LAST:event_cenelarActionPerformed

    private boolean validaCampos() {
        if (nome.getText() == null || nome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' deve ser preenchido!");
            return false;
        }
        if (ibgeid.getText() == null || ibgeid.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'IBGE' deve ser preenchido!");
            return false;
        }
        System.out.println(ibgeid.getText().matches("[0-9]*"));
        if (!ibgeid.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "O campo 'IBGE' deve ser preenchido somente com números!");
            return false;
        }
        if (idEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "O campo 'Estado' deve ser preenchido!");
            return false;
        }
        return true;
    }
    
    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed
        // TODO add your handling code here:
        if (!validaCampos()) {
            return;
        }
        
        cidade.setNome(nome.getText());
        cidade.setIbgeid(Integer.valueOf(ibgeid.getText()));
        ComboItem itemEstado = (ComboItem) idEstado.getSelectedItem();
        cidade.setEstado_id(itemEstado.getKey());
        cidadeControle.gravar(cidade);

        JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso!");
        
        setVisible(true);
        new cidadeLista().setVisible(true);
    }//GEN-LAST:event_salvarActionPerformed

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
            java.util.logging.Logger.getLogger(cidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new cidade().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cenelar;
    private javax.swing.JTextField ibgeid;
    private javax.swing.JComboBox<Object> idEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nome;
    private javax.swing.JButton salvar;
    // End of variables declaration//GEN-END:variables
}
