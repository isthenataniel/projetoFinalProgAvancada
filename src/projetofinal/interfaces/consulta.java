/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projetofinal.controles.ConsultaControle;
import projetofinal.controles.Controle;
import projetofinal.controles.DataUtils;
import projetofinal.controles.ExameControle;
import projetofinal.controles.HorarioProfissionalControle;
import projetofinal.controles.PacienteControle;
import projetofinal.controles.ProfissionalControle;
import projetofinal.controles.SituacaoControle;
import projetofinal.modelos.ComboItem;
import projetofinal.modelos.Consulta;
import projetofinal.modelos.Exame;
import projetofinal.modelos.HorarioProfissional;
import projetofinal.modelos.Paciente;
import projetofinal.modelos.Profissional;
import projetofinal.modelos.Situacao;

/**
 *
 * @author nataniel
 */
public class consulta extends javax.swing.JFrame {

    private PacienteControle pacienteControle;
    private ProfissionalControle profissionalControle;
    private HorarioProfissionalControle horarioProfissionalControle;
    private ExameControle exameControle;
    private ConsultaControle consultaControle;
    private SituacaoControle situacaoControle;
    private Consulta consulta;
    DefaultTableModel modelHorario;
    public List<Object> horarios;
    private telaPrincipal tlPrincipal;
    
    /**
     * Creates new form pais
     */
    public consulta() {
        initComponents();
        dataConsulta.setText(DataUtils.obtemDataFormatada(new Date()));
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskHora = new MaskFormatter("##:##:##");
            maskData.install(dataExame);
        } catch (ParseException ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        iniciaControles();
        carregaCombos();
        carregarExames();
    }
    
    public consulta(telaPrincipal telaPrin) {
        initComponents();
        setTlPrincipal(telaPrin);
        dataConsulta.setText(DataUtils.obtemDataFormatada(new Date()));
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskHora = new MaskFormatter("##:##:##");
            maskData.install(dataExame);
        } catch (ParseException ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        iniciaControles();
        carregaCombos();
        carregarExames();
    }
    
    public consulta(telaPrincipal telaPrin, Integer idConsulta) {
        initComponents();
        setTlPrincipal(telaPrin);
        iniciaControles();
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskHora = new MaskFormatter("##:##:##");
            maskData.install(dataExame);
            
            consulta = consultaControle.obterConsulta(idConsulta);
            if (consulta.getData() != null) {
                dataConsulta.setText(DataUtils.obtemDataFormatada(consulta.getData()));
            }
            motivo.setText(consulta.getMotivo());
            diagnostico.setText(consulta.getDiagnostico());
            observacao.setText(consulta.getObservacao());
        } catch (Exception ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregaCombos();
        carregarExames();
    }
    
    private void iniciaControles() {
        pacienteControle = new PacienteControle();
        profissionalControle = new ProfissionalControle();
        horarioProfissionalControle = new HorarioProfissionalControle();
        exameControle = new ExameControle();
        consultaControle = new ConsultaControle();
        situacaoControle = new SituacaoControle();
        
        consulta = new Consulta();
//        dataExame.setText(DataUtils.obtemDataFormatada(new Date()));
    }
    
    public void carregarExames() {
        try {
//            this.horarios = new ArrayList<Object>();
//            modelHorario = (DefaultTableModel) tabelaHorarios.getModel();
//            if (profissional.getId() != null) {
//                for (HorarioProfissional row : horarioProfissionalControle.obtemHorariosDoProfissional(profissional.getId())) {
//                    horarios.add(row);
//                    Object[] linha = {row.getDia(), 
//                                      row.getInicio(), 
//                                      row.getFim(), 
//                                      row.getIntervalo(), 
//                                      row.getData(),
//                                      row.isHorarioDiferenciado(),
//                                      row.isHorarioSuspenso(),
//                                      row.getId()};
//                    modelHorario.addRow(linha);
//                }
//            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void salvarExames() throws Exception {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
        SimpleDateFormat hr = new SimpleDateFormat("HH:mm:ss");
        
        for (HorarioProfissional horarioP : horarioProfissionalControle.obtemHorariosDoProfissional(null)) {
            horarioP.setAtivo(false);
            horarioProfissionalControle.salvar(horarioP);
        }
        
        for (int i = 0; i < modelHorario.getRowCount(); i++ ) {
            Object row = modelHorario.getDataVector().get(i);
            String[] objetos = row.toString().replace("[", "").replace("]", "").split(",");
            
            HorarioProfissional hpp = new HorarioProfissional();
//            hpp.setIdProfissional(profissional.getId());
            if(objetos[0] != null) {
                hpp.setDia(objetos[0].toString().charAt(0));
            }
            if (objetos[1] != null) {
                hpp.setInicio(hr.parse(objetos[1].toString()));
            }
            if (objetos[2] != null) {
                hpp.setFim(hr.parse(objetos[2].toString()));
            }
            if (objetos[3] != null) {
                hpp.setIntervalo(hr.parse(objetos[3].toString()));
            }
            if (objetos[4] != null && objetos[4].trim().length() > 9) {
                hpp.setData(dt1.parse(objetos[4].toString()));
            }
            hpp.setHorarioDiferenciado(objetos[5] != null);
            hpp.setHorarioSuspenso(objetos[6] != null);
            hpp.setAtivo(true);
            if (objetos.length > 7 && objetos[7] != null) {
                hpp.setId(Integer.parseInt(objetos[7]));
            }
            hpp.setAtivo(true);
            horarioProfissionalControle.salvar(hpp);
        }
    }
    
    private void removeExame() {
        if (tabelaExames.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
            return;    
        }
        System.out.println(tabelaExames.getSelectedRow());
        modelHorario.getDataVector().remove(tabelaExames.getSelectedRow());
        tabelaExames.setModel(modelHorario);
    }
    
    private void adicionaHorarios() {
//        System.out.println(idExame.getSelectedIndex());
//        if ((idExame.getSelectedIndex() == -1) &&
//            (dataExame.getText() == null || dataExame.getText().length() < 10) ) {
//            JOptionPane.showMessageDialog(null, "Selecione um dia da semana ou uma data!");
//            return;
//        }
//        Object[] row = new Object[]{idExame.getSelectedIndex(), 
//                                    inicio.getText(), 
//                                    fim.getText(), 
//                                    intervalo.getText(),
//                                    dataExame.getText(),
//                                    horarioDiferenciado.getText(),
//                                    horarioSuspenso.getText()};
        
//        modelHorario.addRow(row);
    }

    public void carregaCombos() {
        try {
            idPaciente.addItem(new ComboItem(null, "-- Selecione --"));
            idProfissional.addItem(new ComboItem(null, "-- Selecione --"));
            status.addItem(new ComboItem(null, "-- Selecione --"));
            idExame.addItem(new ComboItem(null, "-- Selecione --"));
            
            List<Paciente> pacientes = pacienteControle.obterTodosPacientes();
            for (Paciente paciente : pacientes) {
                ComboItem comboItem = new ComboItem(paciente.getId(), paciente.getNome());
                idPaciente.addItem(comboItem);
                if (consulta.getIdPaciente() != null && consulta.getIdPaciente().equals(paciente.getId())) {
                    idPaciente.setSelectedItem(comboItem);
                }
            }
            List<Profissional> profissionais = profissionalControle.obtemTodosTodosProfissionais();
            for (Profissional profissional : profissionais) {
                ComboItem comboItem = new ComboItem(profissional.getId(), profissional.getNome());
                idProfissional.addItem(comboItem);
                if (consulta.getIdProfissional() != null && consulta.getIdProfissional().equals(profissional.getId())) {
                    idProfissional.setSelectedItem(comboItem);
                }
            }
            List<Exame> exames = exameControle.obtemTodosExames();
            for (Exame exame : exames) {
                ComboItem comboItem = new ComboItem(exame.getId(), exame.getNome());
                idExame.addItem(comboItem);
            }
            List<Situacao> situacoes = situacaoControle.obtemTodasSituacoes();
            for (Situacao situacao : situacoes) {
                ComboItem comboItem = new ComboItem(situacao.getId(), situacao.getDescricao());
                status.addItem(comboItem);
                if (consulta.getStatus() != null && Integer.valueOf(consulta.getStatus().toString()).equals(situacao.getId())) {
                    status.setSelectedItem(comboItem);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        dataConsulta = new javax.swing.JTextField();
        idPaciente = new javax.swing.JComboBox<>();
        idProfissional = new javax.swing.JComboBox<>();
        status = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnostico = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        motivo = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        observacao = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaExames = new javax.swing.JTable();
        editarExame = new javax.swing.JButton();
        addExame = new javax.swing.JButton();
        dataExame = new javax.swing.JFormattedTextField();
        idExame = new javax.swing.JComboBox<>();
        resultado = new javax.swing.JTextField();
        imprimirReqExame = new javax.swing.JButton();
        botaoSalvarPaciente = new javax.swing.JButton();
        cancelarPaciente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Consulta");

        dataConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataConsultaActionPerformed(evt);
            }
        });

        idPaciente.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Paciente"));
        idPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPacienteActionPerformed(evt);
            }
        });

        idProfissional.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idProfissional.setBorder(javax.swing.BorderFactory.createTitledBorder("Profissional"));
        idProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idProfissionalActionPerformed(evt);
            }
        });

        status.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        status.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idPaciente, 0, 299, Short.MAX_VALUE)
                    .addComponent(idProfissional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(dataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consulta", jPanel2);

        diagnostico.setColumns(20);
        diagnostico.setRows(5);
        diagnostico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diagnótisco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jScrollPane1.setViewportView(diagnostico);

        motivo.setColumns(20);
        motivo.setRows(5);
        motivo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Motivo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jScrollPane3.setViewportView(motivo);

        observacao.setColumns(20);
        observacao.setRows(5);
        observacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jScrollPane4.setViewportView(observacao);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Prontuário", jPanel3);

        tabelaExames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exame", "Data", "Realizado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaExames);

        editarExame.setText("Editar Exame");
        editarExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarExameActionPerformed(evt);
            }
        });

        addExame.setText("Adicionar Exame");
        addExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExameActionPerformed(evt);
            }
        });

        dataExame.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataExame.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        dataExame.setToolTipText("");
        dataExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataExameActionPerformed(evt);
            }
        });

        idExame.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idExame.setBorder(javax.swing.BorderFactory.createTitledBorder("Exame"));
        idExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idExameActionPerformed(evt);
            }
        });

        resultado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        imprimirReqExame.setText("Imp. Req. Exame");
        imprimirReqExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirReqExameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dataExame, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idExame, 0, 254, Short.MAX_VALUE)
                            .addComponent(resultado))
                        .addGap(0, 560, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(addExame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editarExame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirReqExame)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(dataExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idExame, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addExame)
                    .addComponent(editarExame)
                    .addComponent(imprimirReqExame))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Exames", jPanel4);

        botaoSalvarPaciente.setText("Salvar");
        botaoSalvarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarPacienteActionPerformed(evt);
            }
        });

        cancelarPaciente.setText("Cancelar");
        cancelarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 26, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(botaoSalvarPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarPaciente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarPaciente)
                    .addComponent(cancelarPaciente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarPacienteActionPerformed
        // TODO add your handling code here:
        try {
            if (!validaCampos()) {
                return;
            }
            consulta.setMotivo(motivo.getText());
            consulta.setDiagnostico(diagnostico.getText());
            consulta.setObservacao(observacao.getText());
            consultaControle.gravar(consulta);
//            salvarExames();
            
            JOptionPane.showMessageDialog(null, "CONSULTA SALVA COM SUCESSO!");
            this.setVisible(false);
            
            for (int i = 0; i < tlPrincipal.modelConsultas.getRowCount(); i++) {
                tlPrincipal.modelConsultas.removeRow(i);
                i--;
            }
            tlPrincipal.carregarLista();
                        
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o profissional!");
        }

    }//GEN-LAST:event_botaoSalvarPacienteActionPerformed

    private boolean validaCampos() throws ParseException {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
        // Aba 1
        consulta.setData(dt1.parse(dataConsulta.getText()));
        if (idPaciente.getSelectedIndex() != -1) {
            consulta.setIdPaciente(((ComboItem) idPaciente.getSelectedItem()).getKey());
        }
        if (idProfissional.getSelectedIndex() != -1) {
            consulta.setIdProfissional(((ComboItem) idProfissional.getSelectedItem()).getKey());
        }
        if (status.getSelectedIndex() != -1) {
            consulta.setStatus(((ComboItem) status.getSelectedItem()).getKey().toString().charAt(0));
        }
        if (consulta.getData() == null) {
            JOptionPane.showMessageDialog(null, "A Data deve ser informada!");
            return false;
        }
        if (consulta.getIdPaciente() == null) {
            JOptionPane.showMessageDialog(null, "O Paciente deve ser informada!");
            return false;
        }
        if (consulta.getIdProfissional() == null) {
            JOptionPane.showMessageDialog(null, "O Profissional deve ser informada!");
            return false;
        }
        if (consulta.getStatus() == null) {
            JOptionPane.showMessageDialog(null, "O Status deve ser informada!");
            return false;
        }
        return true;
    }
    
    private void cancelarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPacienteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancelarPacienteActionPerformed

    private void idExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idExameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idExameActionPerformed

    private void dataExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataExameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataExameActionPerformed

    private void addExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addExameActionPerformed
        // TODO add your handling code here:
        try {
            adicionaHorarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addExameActionPerformed

    private void editarExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarExameActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_editarExameActionPerformed

    private void idPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPacienteActionPerformed

    private void idProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idProfissionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idProfissionalActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void imprimirReqExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirReqExameActionPerformed
        // TODO add your handling code here:
        try {
            if (tabelaExames.getSelectedRow() == -1) {
                Integer option = JOptionPane.showConfirmDialog(null, "Nenhum exame selecionado! Será gerado a requisição padrão de exame. ");
                if (option.equals(JOptionPane.OK_OPTION)) {
                    JRResultSetDataSource relatResult = new JRResultSetDataSource(Controle.obtemConsulta("SELECT 1"));
                    JasperPrint jpPrint;
                    jpPrint = JasperFillManager.fillReport("/home/nataniel/NetBeansProjects/ProjetoFinal/src/projetofinal/relatorios/requisicaoExame.jasper", new HashMap(), relatResult);
                    JasperViewer jpViewer = new JasperViewer (jpPrint , false); //false - não encerra a aplicação ao fechar a janela
                    jpViewer.setVisible(true);
                    jpViewer.toFront(); //apresenta o relatório acima das outras janelas
                }
                return;
            }
        } catch (JRException ex) {
            Logger.getLogger(consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imprimirReqExameActionPerformed

    private void dataConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataConsultaActionPerformed

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
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new consulta().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addExame;
    private javax.swing.JButton botaoSalvarPaciente;
    private javax.swing.JButton cancelarPaciente;
    private javax.swing.JTextField dataConsulta;
    private javax.swing.JFormattedTextField dataExame;
    private javax.swing.JTextArea diagnostico;
    private javax.swing.JButton editarExame;
    private javax.swing.JComboBox<Object> idExame;
    private javax.swing.JComboBox<Object> idPaciente;
    private javax.swing.JComboBox<Object> idProfissional;
    private javax.swing.JButton imprimirReqExame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea motivo;
    private javax.swing.JTextArea observacao;
    private javax.swing.JTextField resultado;
    private javax.swing.JComboBox<Object> status;
    private javax.swing.JTable tabelaExames;
    // End of variables declaration//GEN-END:variables

    public telaPrincipal getTlPrincipal() {
        return tlPrincipal;
    }

    public void setTlPrincipal(telaPrincipal tlPrincipal) {
        this.tlPrincipal = tlPrincipal;
    }
}
