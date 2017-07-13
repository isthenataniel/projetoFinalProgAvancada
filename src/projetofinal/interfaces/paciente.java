/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;
import projetofinal.controles.CidadeControle;
import projetofinal.controles.DataUtils;
import projetofinal.controles.EstadoCivilControle;
import projetofinal.controles.PacienteControle;
import projetofinal.controles.TitulacaoControle;
import projetofinal.modelos.Cidade;
import projetofinal.modelos.Paciente;
import projetofinal.modelos.ComboItem;
import projetofinal.modelos.EstadoCivil;
import projetofinal.modelos.Titulacao;

/**
 *
 * @author nataniel
 */
public class paciente extends javax.swing.JFrame {

    public Paciente paciente;
    private PacienteControle pacienteControle;
    private CidadeControle cidadeControle;
    private EstadoCivilControle estadoCivilControle;
    private TitulacaoControle titulacaoControle;
    /**
     * Creates new form pais
     */
    public paciente() {
        initComponents();
        paciente = new Paciente();
        paciente.setData(new Date());
        pacienteControle = new PacienteControle();
        cidadeControle = new CidadeControle();
        estadoCivilControle = new EstadoCivilControle();
        titulacaoControle = new TitulacaoControle();
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.install(dataNascimento);
        } catch (ParseException ex) {
            Logger.getLogger(paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregaCombos();
    }
    
    public paciente(Integer idPaciente) {
        initComponents();
        pacienteControle = new PacienteControle();
        cidadeControle = new CidadeControle();
        estadoCivilControle = new EstadoCivilControle();
        titulacaoControle = new TitulacaoControle();
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.install(dataNascimento);
            
            paciente = pacienteControle.obterPaciente(idPaciente);
            
            if (paciente.getDataNascimento() != null) {
                dataNascimento.setText(DataUtils.obtemDataFormatada(paciente.getDataNascimento()));
            }
            
            nome.setText(paciente.getNome());
            sexo.setSelectedIndex(paciente.getSexo().equals('F') ? 0 : 1);
            rg.setText(paciente.getRg());
            cpf.setText(paciente.getCpf());
            mae.setText(paciente.getMae());
            pai.setText(paciente.getPai());
            logradouro.setText(paciente.getLogradouro());
            numero.setText(paciente.getNumero() != null ? paciente.getNumero().toString() : "");
            bairro.setText(paciente.getBairro());
            cep.setText(paciente.getComplemento());
            celular.setText(paciente.getCelular());
            telefone.setText(paciente.getTelefone());
            email.setText(paciente.getEmail());
            observacao.setText(paciente.getObservacao());
            cep.setText(paciente.getCep());
        } catch (Exception ex) {
            Logger.getLogger(paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregaCombos();
    }
    
    public void carregaCombos() {
        try {
            idCidadeNascimento.addItem(new ComboItem(null, "-- Selecione --"));
            idCidade.addItem(new ComboItem(null, "-- Selecione --"));
            idEstadoCivil.addItem(new ComboItem(null, "-- Selecione --"));
            idTitulacao.addItem(new ComboItem(null, "-- Selecione --"));
            
            List<Cidade> cidades = cidadeControle.obtemTodasCidades();
            for (Cidade cidade : cidades) {
                ComboItem comboItem = new ComboItem(cidade.getId(), cidade.getNome());
                idCidadeNascimento.addItem(comboItem);
                idCidade.addItem(comboItem);
                if (paciente.getIdCidadeNascimento() != null && paciente.getIdCidadeNascimento().equals(cidade.getId())) {
                    idCidadeNascimento.setSelectedItem(comboItem);
                }
                if (paciente.getIdCidade() != null && paciente.getIdCidade().equals(cidade.getId())) {
                    idCidade.setSelectedItem(comboItem);
                }
            }
            List<EstadoCivil> estados = estadoCivilControle.obtemTodosEstadosCivil();
            for (EstadoCivil estadoCivil : estados) {
                ComboItem comboItem = new ComboItem(estadoCivil.getId(), estadoCivil.getDescricao());
                idEstadoCivil.addItem(comboItem);
                if (paciente.getIdEstadoCivil() != null && paciente.getIdEstadoCivil().equals(estadoCivil.getId())) {
                    idEstadoCivil.setSelectedItem(comboItem);
                }
            }
            List<Titulacao> titulacoes = titulacaoControle.obtemTodasTitulacoes();
            for (Titulacao titulacao : titulacoes) {
                ComboItem comboItem = new ComboItem(titulacao.getId(), titulacao.getDescricao());
                idTitulacao.addItem(comboItem);
                if (paciente.getIdTitulacao() != null && paciente.getIdTitulacao().equals(titulacao.getId())) {
                    idTitulacao.setSelectedItem(comboItem);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(paciente.class.getName()).log(Level.SEVERE, null, ex);
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
        rg = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox<>();
        dataNascimento = new javax.swing.JFormattedTextField();
        nome = new javax.swing.JTextField();
        pai = new javax.swing.JTextField();
        idCidadeNascimento = new javax.swing.JComboBox<>();
        cpf = new javax.swing.JTextField();
        mae = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        logradouro = new javax.swing.JTextField();
        bairro = new javax.swing.JTextField();
        cep = new javax.swing.JTextField();
        idCidade = new javax.swing.JComboBox<>();
        numero = new javax.swing.JFormattedTextField();
        complemento1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        telefone = new javax.swing.JTextField();
        celular = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        idEstadoCivil = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacao = new javax.swing.JTextArea();
        idTitulacao = new javax.swing.JComboBox<>();
        cancelarPaciente = new javax.swing.JButton();
        botaoSalvarPaciente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Paciente");

        rg.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rgActionPerformed(evt);
            }
        });

        sexo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feninino", "Masculino" }));
        sexo.setBorder(javax.swing.BorderFactory.createTitledBorder("Sexo"));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        dataNascimento.setBorder(javax.swing.BorderFactory.createTitledBorder("Data nascimento"));
        dataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        dataNascimento.setToolTipText("");
        dataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNascimentoActionPerformed(evt);
            }
        });

        nome.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        pai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pai", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        pai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paiActionPerformed(evt);
            }
        });

        idCidadeNascimento.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idCidadeNascimento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade Nascimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idCidadeNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCidadeNascimentoActionPerformed(evt);
            }
        });

        cpf.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CPF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfActionPerformed(evt);
            }
        });

        mae.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mãe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        mae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rg)
                    .addComponent(dataNascimento)
                    .addComponent(pai)
                    .addComponent(nome)
                    .addComponent(idCidadeNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpf)
                    .addComponent(mae))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idCidadeNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Dados", jPanel2);

        logradouro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logradouro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        logradouro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logradouroActionPerformed(evt);
            }
        });

        bairro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bairro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        bairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairroActionPerformed(evt);
            }
        });

        cep.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cepActionPerformed(evt);
            }
        });

        idCidade.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCidadeActionPerformed(evt);
            }
        });

        numero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        complemento1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complemento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        complemento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complemento1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logradouro)
                    .addComponent(cep, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(idCidade, 0, 394, Short.MAX_VALUE)
                    .addComponent(numero)
                    .addComponent(complemento1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(idCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(complemento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Endereço", jPanel1);

        telefone.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefoneActionPerformed(evt);
            }
        });

        celular.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Celular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });

        email.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        idEstadoCivil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idEstadoCivil.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado civil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idEstadoCivilActionPerformed(evt);
            }
        });

        observacao.setColumns(20);
        observacao.setRows(5);
        observacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jScrollPane1.setViewportView(observacao);

        idTitulacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idTitulacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titulação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idTitulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTitulacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(celular)
                        .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                        .addComponent(telefone, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                        .addComponent(idEstadoCivil, 0, 394, Short.MAX_VALUE)
                        .addComponent(idTitulacao, 0, 394, Short.MAX_VALUE)))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(idEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idTitulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Complementar", jPanel3);

        cancelarPaciente.setText("Cancelar");
        cancelarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarPacienteActionPerformed(evt);
            }
        });

        botaoSalvarPaciente.setText("Salvar");
        botaoSalvarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 263, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(botaoSalvarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarPaciente)
                    .addComponent(cancelarPaciente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rgActionPerformed

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void dataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNascimentoActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void paiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paiActionPerformed

    private void logradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logradouroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logradouroActionPerformed

    private void bairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bairroActionPerformed

    private void cepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cepActionPerformed

    private void idCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCidadeActionPerformed

    private void telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void idEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idEstadoCivilActionPerformed

    private void cancelarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPacienteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new pacienteLista().setVisible(true);
    }//GEN-LAST:event_cancelarPacienteActionPerformed

    private void botaoSalvarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarPacienteActionPerformed
        // TODO add your handling code here:
        try {
            if (!validaCampos()) {
                return;
            }
            pacienteControle.salvar(paciente);
            // SALVO COM SUCESSO
            JOptionPane.showMessageDialog(null, "PACIENTE SALVO COM SUCESSO!");
            this.setVisible(false);
            new pacienteLista().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o paciente!");
        }
        
    }//GEN-LAST:event_botaoSalvarPacienteActionPerformed

    private void idCidadeNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCidadeNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCidadeNascimentoActionPerformed

    private void cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfActionPerformed

    private void maeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maeActionPerformed

    private void idTitulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTitulacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTitulacaoActionPerformed

    private void complemento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complemento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_complemento1ActionPerformed

    private boolean validaCampos() throws ParseException {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
        // Aba 1
        paciente.setNome(nome.getText());
        paciente.setSexo(sexo.getSelectedIndex() == 0 ? 'F' : 'M');
        if (dataNascimento.getText() != null && dataNascimento.getText().trim().length() > 9) {
            paciente.setDataNascimento(dt1.parse(dataNascimento.getText()));
        }
        if (idCidadeNascimento.getSelectedItem() != null) {
            paciente.setIdCidadeNascimento(((ComboItem)idCidadeNascimento.getSelectedItem()).getKey());
        }
        paciente.setRg(rg.getText());
        paciente.setCpf(cpf.getText());
        paciente.setMae(mae.getText());
        paciente.setPai(pai.getText());
        // Aba 2
        if (idCidade.getSelectedItem() != null) {
            paciente.setIdCidade(((ComboItem)idCidade.getSelectedItem()).getKey());
        }
        paciente.setLogradouro(logradouro.getText());
        if (numero.getText() != null && !numero.getText().equals("")) {
            paciente.setNumero(Integer.parseInt(numero.getText()));
        } 
        paciente.setBairro(bairro.getText());
        paciente.setComplemento(cep.getText());
        paciente.setCep(cep.getText());
        // Aba 3
        if (idEstadoCivil.getSelectedItem() != null) {
            paciente.setIdEstadoCivil(((ComboItem)idEstadoCivil.getSelectedItem()).getKey());
        }
        paciente.setCelular(celular.getText());
        paciente.setTelefone(telefone.getText());
        paciente.setEmail(email.getText());
        if (idTitulacao.getSelectedItem() != null) {
            paciente.setIdTitulacao(((ComboItem)idTitulacao.getSelectedItem()).getKey());
        }
        paciente.setComplemento(cep.getText());
        
        if (paciente.getNome() == null || paciente.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' deve ser preenchido!");
            return false;
        }
        if (paciente.getSexo() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Sexo' deve ser preenchido!");
            return false;
        }
        if (paciente.getDataNascimento() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data Nascimento' deve ser preenchido!");
            return false;
        }
        return true;
    }
    
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
            java.util.logging.Logger.getLogger(paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new paciente().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro;
    private javax.swing.JButton botaoSalvarPaciente;
    private javax.swing.JButton cancelarPaciente;
    private javax.swing.JTextField celular;
    private javax.swing.JTextField cep;
    private javax.swing.JTextField complemento1;
    private javax.swing.JTextField cpf;
    private javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<Object> idCidade;
    private javax.swing.JComboBox<Object> idCidadeNascimento;
    private javax.swing.JComboBox<Object> idEstadoCivil;
    private javax.swing.JComboBox<Object> idTitulacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField logradouro;
    private javax.swing.JTextField mae;
    private javax.swing.JTextField nome;
    private javax.swing.JFormattedTextField numero;
    private javax.swing.JTextArea observacao;
    private javax.swing.JTextField pai;
    private javax.swing.JTextField rg;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTextField telefone;
    // End of variables declaration//GEN-END:variables
}
