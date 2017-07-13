/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.interfaces;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import projetofinal.controles.CidadeControle;
import projetofinal.controles.DataUtils;
import projetofinal.controles.EstadoCivilControle;
import projetofinal.controles.EstadoControle;
import projetofinal.controles.HorarioProfissionalControle;
import projetofinal.controles.ProfissionalControle;
import projetofinal.controles.TitulacaoControle;
import projetofinal.modelos.Cidade;
import projetofinal.modelos.ComboItem;
import projetofinal.modelos.Estado;
import projetofinal.modelos.EstadoCivil;
import projetofinal.modelos.HorarioProfissional;
import projetofinal.modelos.Profissional;
import projetofinal.modelos.Titulacao;

/**
 *
 * @author nataniel
 */
public class profissional extends javax.swing.JFrame {

    private Profissional profissional;
    private CidadeControle cidadeControle;
    private EstadoCivilControle estadoCivilControle;
    private TitulacaoControle titulacaoControle;
    private ProfissionalControle profissionalControle;
    private EstadoControle estadoControle;
    private HorarioProfissionalControle horarioProfissionalControle;
    DefaultTableModel modelHorario;
    public List<Object> horarios;
    public List<ComboItem> comboDias;
    
    /**
     * Creates new form pais
     */
    public profissional() {
        initDias();
        initComponents();
        profissional = new Profissional();
        profissionalControle = new ProfissionalControle();
        cidadeControle = new CidadeControle();
        estadoCivilControle = new EstadoCivilControle();
        titulacaoControle = new TitulacaoControle();
        estadoControle = new EstadoControle();
        horarioProfissionalControle = new HorarioProfissionalControle();
        tabelaHorarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskHora = new MaskFormatter("##:##:##");
            maskData.install(dataNascimento);
            maskData = new MaskFormatter("##/##/####");
            maskData.install(dataHorario);
            maskHora.install(inicio);
            maskHora = new MaskFormatter("##:##:##");
            maskHora.install(fim);
            maskHora = new MaskFormatter("##:##:##");
            maskHora.install(intervalo);
        } catch (ParseException ex) {
            Logger.getLogger(profissional.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregaCombos();
        carregarHorarios();
    }
    
    public profissional(Integer idProfissional) {
        initDias();
        initComponents();
        profissional = new Profissional();
        profissionalControle = new ProfissionalControle();
        cidadeControle = new CidadeControle();
        estadoCivilControle = new EstadoCivilControle();
        titulacaoControle = new TitulacaoControle();
        estadoControle = new EstadoControle();
        horarioProfissionalControle = new HorarioProfissionalControle();
        
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskHora = new MaskFormatter("##:##:##");
            maskData.install(dataNascimento);
            maskData = new MaskFormatter("##/##/####");
            maskData.install(dataHorario);
            maskHora.install(inicio);
            maskHora = new MaskFormatter("##:##:##");
            maskHora.install(fim);
            maskHora = new MaskFormatter("##:##:##");
            maskHora.install(intervalo);
            
            profissional = profissionalControle.obterProfissional(idProfissional);
            if (profissional.getDataNascimento() != null) {
                dataNascimento.setText(DataUtils.obtemDataFormatada(profissional.getDataNascimento()));
            }
            conselho.setText(profissional.getConselho());
            conselhoRegional.setText(profissional.getConselhoRegional());
            nome.setText(profissional.getNome());
            sexo.setSelectedIndex(profissional.getSexo().equals('F') ? 0 : 1);
            rg.setText(profissional.getRg());
            cpf.setText(profissional.getCpf());
            mae.setText(profissional.getMae());
            pai.setText(profissional.getPai());
            logradouro.setText(profissional.getLogradouro());
            numero.setText(profissional.getNumero() != null ? profissional.getNumero().toString() : "");
            bairro.setText(profissional.getBairro());
            cep.setText(profissional.getComplemento());
            celular.setText(profissional.getCelular());
            telefone.setText(profissional.getTelefone());
            email.setText(profissional.getEmail());
            observacao.setText(profissional.getObservacao());
            cep.setText(profissional.getCep());
        } catch (Exception ex) {
            Logger.getLogger(profissional.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregaCombos();
        carregarHorarios();
    }
    
    private void initDias() {
        comboDias = new ArrayList<ComboItem>();
        comboDias.add(new ComboItem(0, "Domingo"));
        comboDias.add(new ComboItem(1, "Segunda"));
        comboDias.add(new ComboItem(2, "Terça"));
        comboDias.add(new ComboItem(3, "Quarta"));
        comboDias.add(new ComboItem(4, "Quinta"));
        comboDias.add(new ComboItem(5, "Sexta"));
        comboDias.add(new ComboItem(6, "Sábado"));
    }
    
    public void carregarHorarios() {
        try {
            this.horarios = new ArrayList<Object>();
            modelHorario = (DefaultTableModel) tabelaHorarios.getModel();
            if (profissional.getId() != null) {
                for (HorarioProfissional row : horarioProfissionalControle.obtemHorariosDoProfissional(profissional.getId())) {
                    horarios.add(row);
                    Object[] linha = {row.getDia(), 
                                      row.getInicio(), 
                                      row.getFim(), 
                                      row.getIntervalo(), 
                                      row.getData(),
                                      row.isHorarioDiferenciado(),
                                      row.isHorarioSuspenso(),
                                      row.getId()};
                    modelHorario.addRow(linha);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void salvarHorarios() throws Exception {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
        SimpleDateFormat hr = new SimpleDateFormat("HH:mm:ss");
        
        for (HorarioProfissional horarioP : horarioProfissionalControle.obtemHorariosDoProfissional(profissional.getId())) {
            horarioP.setAtivo(false);
            horarioProfissionalControle.salvar(horarioP);
        }
        
        for (int i = 0; i < modelHorario.getRowCount(); i++ ) {
            Object row = modelHorario.getDataVector().get(i);
            String[] objetos = row.toString().replace("[", "").replace("]", "").split(",");
            
            HorarioProfissional hpp = new HorarioProfissional();
            hpp.setIdProfissional(profissional.getId());
            if(objetos[0] != null) {
                Character dia = null;
                if (objetos[0].toString().trim() != "-") {
                    for (ComboItem ccDia : comboDias) {
                        if (ccDia.getValue().equals(objetos[0].toString())) {
                            dia = ccDia.getKey().toString().charAt(0);
                        }
                    }                    
                }  
                hpp.setDia(dia);
            }
            Date dtInicio = null;
            if (objetos[1].toString().trim() != "-") {
                dtInicio = hr.parse(objetos[1].toString());
            }
            hpp.setInicio(dtInicio);
            
            Date dtFim = null;
            if (objetos[2].toString().trim() != "-") {
                dtFim = hr.parse(objetos[2].toString());
            }
            hpp.setFim(dtFim);
            
            Date dtIntervalo = null;
            if (objetos[3].toString().trim() != "-") {
                dtIntervalo = hr.parse(objetos[3].toString());
            }
            hpp.setIntervalo(dtIntervalo);
            
            Date dtDt = null;
            if (objetos[4] != null && objetos[4].trim().length() > 9) {
                dtDt = dt1.parse(objetos[4].toString());
            }
            hpp.setData(dtDt);
            
            hpp.setHorarioDiferenciado(objetos[5] == "Sim");
            hpp.setHorarioSuspenso(objetos[6] == "Sim");
            hpp.setAtivo(true);
            if (objetos.length > 7 && objetos[7] != null) {
                hpp.setId(Integer.parseInt(objetos[7]));
            }
            horarioProfissionalControle.salvar(hpp);
        }
    }
    
    private void removeHorario() {
        if (tabelaHorarios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
            return;    
        }
        System.out.println(tabelaHorarios.getSelectedRow());
        modelHorario.getDataVector().remove(tabelaHorarios.getSelectedRow());
        tabelaHorarios.setModel(modelHorario);
    }
    
    private void adicionaHorarios() {
        if ((diaSemana.getSelectedIndex() == -1) &&
            (dataHorario.getText() == null || dataHorario.getText().length() < 10) ) {
            JOptionPane.showMessageDialog(null, "Selecione um dia da semana ou uma data!");
            return;
        }
        String diaSemanaLista = "-", inicioLista = "-", fimLista = "-", intervaloLista = "-", dataLista = "-", diferenciado = "Não", suspenso = "Não";
        if (diaSemana.getSelectedIndex() != -1) {
            for (ComboItem itemCombo : comboDias) {
               if (itemCombo.getKey().equals(diaSemana.getSelectedIndex())) {
                   diaSemanaLista = itemCombo.getValue();
               }
            }
        }
        if (!inicio.getText().trim().equals(":  :") ) {
            inicioLista = inicio.getText();
        }
        if (!fim.getText().trim().equals(":  :")) {
            fimLista = fim.getText(); 
        }
        if (!intervalo.getText().trim().equals(":  :")) {
            intervaloLista = intervalo.getText();
        }
        System.out.println(dataHorario.getText());
        if (!dataHorario.getText().trim().equals("/  /")) {
            dataLista = dataHorario.getText();
            diaSemanaLista = "-";
        }
        if (horarioDiferenciado.isSelected()) {
            diferenciado = "Sim";
        }
        if (horarioSuspenso.isSelected()) {
            suspenso = "Sim";
        }
        Object[] row = new Object[]{diaSemanaLista, 
                                    inicioLista, 
                                    fimLista, 
                                    intervaloLista,
                                    dataLista,
                                    diferenciado,
                                    suspenso};
        
        modelHorario.addRow(row);
    }

    public void carregaCombos() {
        try {
            idCidadeNascimento.addItem(new ComboItem(null, "-- Selecione --"));
            idCidade.addItem(new ComboItem(null, "-- Selecione --"));
            idEstadoCivil.addItem(new ComboItem(null, "-- Selecione --"));
            idTitulacao.addItem(new ComboItem(null, "-- Selecione --"));
            idEstadoConselho.addItem(new ComboItem(null, "-- Selecione --"));
            diaSemana.addItem(new ComboItem(-1, "-- Selecione --"));
            for (ComboItem itemCombo : comboDias) {
                diaSemana.addItem(itemCombo);
            }
            
            List<Cidade> cidades = cidadeControle.obtemTodasCidades();
            for (Cidade cidade : cidades) {
                ComboItem comboItem = new ComboItem(cidade.getId(), cidade.getNome());
                idCidadeNascimento.addItem(comboItem);
                idCidade.addItem(comboItem);
                if (profissional.getIdCidadeNascimento() != null && profissional.getIdCidadeNascimento().equals(cidade.getId())) {
                    idCidadeNascimento.setSelectedItem(comboItem);
                }
                if (profissional.getIdCidade() != null && profissional.getIdCidade().equals(cidade.getId())) {
                    idCidade.setSelectedItem(comboItem);
                }
            }
            List<EstadoCivil> estadosCivis = estadoCivilControle.obtemTodosEstadosCivil();
            for (EstadoCivil estadoCivil : estadosCivis) {
                ComboItem comboItem = new ComboItem(estadoCivil.getId(), estadoCivil.getDescricao());
                idEstadoCivil.addItem(comboItem);
                if (profissional.getIdEstadoCivil() != null && profissional.getIdEstadoCivil().equals(estadoCivil.getId())) {
                    idEstadoCivil.setSelectedItem(comboItem);
                }
            }
            List<Titulacao> titulacoes = titulacaoControle.obtemTodasTitulacoes();
            for (Titulacao titulacao : titulacoes) {
                ComboItem comboItem = new ComboItem(titulacao.getId(), titulacao.getDescricao());
                idTitulacao.addItem(comboItem);
                if (profissional.getIdTitulacao() != null && profissional.getIdTitulacao().equals(titulacao.getId())) {
                    idTitulacao.setSelectedItem(comboItem);
                }
            }
            List<Estado> estados = estadoControle.obtemTodosEstados();
            for (Estado estado : estados) {
                ComboItem comboItem = new ComboItem(estado.getId(), estado.getNome());
                idEstadoConselho.addItem(comboItem);
                if (profissional.getIdEstadoConselho() != null && profissional.getIdEstadoConselho().equals(estado.getId())) {
                    idEstadoConselho.setSelectedItem(comboItem);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(profissional.class.getName()).log(Level.SEVERE, null, ex);
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
        conselho = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox<>();
        dataNascimento = new javax.swing.JFormattedTextField();
        idCidadeNascimento = new javax.swing.JComboBox<>();
        rg = new javax.swing.JTextField();
        cpf = new javax.swing.JTextField();
        mae = new javax.swing.JTextField();
        pai = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        conselhoRegional = new javax.swing.JTextField();
        idEstadoConselho = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaHorarios = new javax.swing.JTable();
        removeHorario = new javax.swing.JButton();
        addHorario = new javax.swing.JButton();
        horarioSuspenso = new javax.swing.JCheckBox();
        horarioDiferenciado = new javax.swing.JCheckBox();
        inicio = new javax.swing.JFormattedTextField();
        fim = new javax.swing.JFormattedTextField();
        dataHorario = new javax.swing.JFormattedTextField();
        diaSemana = new javax.swing.JComboBox<>();
        intervalo = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        idCidade = new javax.swing.JComboBox<>();
        logradouro = new javax.swing.JTextField();
        numero = new javax.swing.JFormattedTextField();
        bairro = new javax.swing.JTextField();
        complemento1 = new javax.swing.JTextField();
        cep = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        idEstadoCivil = new javax.swing.JComboBox<>();
        celular = new javax.swing.JTextField();
        telefone = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        idTitulacao = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacao = new javax.swing.JTextArea();
        botaoSalvarPaciente = new javax.swing.JButton();
        cancelarPaciente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Profissional");

        conselho.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conselho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        conselho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conselhoActionPerformed(evt);
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

        idCidadeNascimento.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idCidadeNascimento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade Nascimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idCidadeNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCidadeNascimentoActionPerformed(evt);
            }
        });

        rg.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rgActionPerformed(evt);
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

        pai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pai", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        pai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paiActionPerformed(evt);
            }
        });

        nome.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        conselhoRegional.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conselho Regional", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        conselhoRegional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conselhoRegionalActionPerformed(evt);
            }
        });

        idEstadoConselho.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idEstadoConselho.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado Conselho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idEstadoConselho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idEstadoConselhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idEstadoConselho, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rg)
                        .addComponent(dataNascimento)
                        .addComponent(pai)
                        .addComponent(conselho)
                        .addComponent(idCidadeNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpf)
                        .addComponent(mae, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nome)
                        .addComponent(conselhoRegional)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conselho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conselhoRegional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idEstadoConselho, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(130, 130, 130))
        );

        jTabbedPane2.addTab("Dados", jPanel2);

        tabelaHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Início", "Fim", "Intervalo", "Data", "Diferenciado", "Suspenso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaHorarios);

        removeHorario.setText("Excluir Horário");
        removeHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeHorarioActionPerformed(evt);
            }
        });

        addHorario.setText("Adicionar Horário");
        addHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHorarioActionPerformed(evt);
            }
        });

        horarioSuspenso.setText("Horário Suspenso");

        horarioDiferenciado.setText("Horário Diferenciado");
        horarioDiferenciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioDiferenciadoActionPerformed(evt);
            }
        });

        inicio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Início", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        inicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });

        fim.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        fim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        fim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fimActionPerformed(evt);
            }
        });

        dataHorario.setBorder(javax.swing.BorderFactory.createTitledBorder("Horário Diferenciado/Suspenso"));
        dataHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        dataHorario.setToolTipText("");
        dataHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataHorarioActionPerformed(evt);
            }
        });

        diaSemana.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        diaSemana.setBorder(javax.swing.BorderFactory.createTitledBorder("Dia da Semana"));
        diaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaSemanaActionPerformed(evt);
            }
        });

        intervalo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intervalo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        intervalo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        intervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervaloActionPerformed(evt);
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(diaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataHorario))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(288, 288, 288)
                                .addComponent(addHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(horarioSuspenso)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fim, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(intervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(horarioDiferenciado))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(removeHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(diaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horarioDiferenciado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horarioSuspenso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addHorario)
                    .addComponent(removeHorario))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Horários", jPanel4);

        idCidade.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCidadeActionPerformed(evt);
            }
        });

        logradouro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logradouro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        logradouro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logradouroActionPerformed(evt);
            }
        });

        numero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        bairro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bairro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        bairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairroActionPerformed(evt);
            }
        });

        complemento1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complemento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        complemento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complemento1ActionPerformed(evt);
            }
        });

        cep.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cepActionPerformed(evt);
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
                .addContainerGap(424, Short.MAX_VALUE))
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
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Endereço", jPanel1);

        idEstadoCivil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idEstadoCivil.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado civil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idEstadoCivilActionPerformed(evt);
            }
        });

        celular.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Celular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });

        telefone.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefoneActionPerformed(evt);
            }
        });

        email.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        idTitulacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idTitulacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titulação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        idTitulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTitulacaoActionPerformed(evt);
            }
        });

        observacao.setColumns(20);
        observacao.setRows(5);
        observacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jScrollPane1.setViewportView(observacao);

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
                .addContainerGap(424, Short.MAX_VALUE))
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
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Complementar", jPanel3);

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

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioActionPerformed

    private void conselhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conselhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conselhoActionPerformed

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void dataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNascimentoActionPerformed

    private void idCidadeNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCidadeNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCidadeNascimentoActionPerformed

    private void rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rgActionPerformed

    private void cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfActionPerformed

    private void maeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maeActionPerformed

    private void paiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paiActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void botaoSalvarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarPacienteActionPerformed
        // TODO add your handling code here:
        try {
            if (!validaCampos()) {
                return;
            }
            profissional.setId(profissionalControle.salvar(profissional));
            salvarHorarios();
            // SALVO COM SUCESSO
            JOptionPane.showMessageDialog(null, "PROFISSIONAL SALVO COM SUCESSO!");
            this.setVisible(false);
            new profissionalLista().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o profissional!");
        }

    }//GEN-LAST:event_botaoSalvarPacienteActionPerformed

    private boolean validaCampos() throws ParseException {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
        // Aba 1
        profissional.setNome(nome.getText());
        profissional.setConselho(conselho.getText());
        profissional.setConselhoRegional(conselhoRegional.getText());
        if (idEstadoConselho.getSelectedItem() != null) {
            profissional.setIdEstadoConselho(((ComboItem) idEstadoConselho.getSelectedItem()).getKey());
        }
        profissional.setSexo(sexo.getSelectedIndex() == 0 ? 'F' : 'M');
        if (dataNascimento.getText() != null && dataNascimento.getText().trim().length() > 9) {
            profissional.setDataNascimento(dt1.parse(dataNascimento.getText()));
        }
        if (idCidadeNascimento.getSelectedItem() != null) {
            profissional.setIdCidadeNascimento(((ComboItem)idCidadeNascimento.getSelectedItem()).getKey());
        }
        profissional.setRg(rg.getText());
        profissional.setCpf(cpf.getText());
        profissional.setMae(mae.getText());
        profissional.setPai(pai.getText());
        // Aba 2
        if (idCidade.getSelectedItem() != null) {
            profissional.setIdCidade(((ComboItem)idCidade.getSelectedItem()).getKey());
        }
        profissional.setLogradouro(logradouro.getText());
        if (numero.getText() != null && !numero.getText().equals("")) {
            profissional.setNumero(Integer.parseInt(numero.getText()));
        } 
        profissional.setBairro(bairro.getText());
        profissional.setComplemento(cep.getText());
        profissional.setCep(cep.getText());
        // Aba 3
        if (idEstadoCivil.getSelectedItem() != null) {
            profissional.setIdEstadoCivil(((ComboItem)idEstadoCivil.getSelectedItem()).getKey());
        }
        profissional.setCelular(celular.getText());
        profissional.setTelefone(telefone.getText());
        profissional.setEmail(email.getText());
        if (idTitulacao.getSelectedItem() != null) {
            profissional.setIdTitulacao(((ComboItem)idTitulacao.getSelectedItem()).getKey());
        }
        profissional.setComplemento(cep.getText());
        
        if (profissional.getNome() == null || profissional.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' deve ser preenchido!");
            return false;
        }
        if (profissional.getSexo() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Sexo' deve ser preenchido!");
            return false;
        }
        if (profissional.getDataNascimento() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data Nascimento' deve ser preenchido!");
            return false;
        }
        return true;
    }
    
    private void cancelarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPacienteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new profissionalLista().setVisible(true);
    }//GEN-LAST:event_cancelarPacienteActionPerformed

    private void conselhoRegionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conselhoRegionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conselhoRegionalActionPerformed

    private void idEstadoConselhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idEstadoConselhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idEstadoConselhoActionPerformed

    private void idCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCidadeActionPerformed

    private void logradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logradouroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logradouroActionPerformed

    private void bairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bairroActionPerformed

    private void complemento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complemento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_complemento1ActionPerformed

    private void cepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cepActionPerformed

    private void idEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idEstadoCivilActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularActionPerformed

    private void telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void idTitulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTitulacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTitulacaoActionPerformed

    private void intervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervaloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervaloActionPerformed

    private void fimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fimActionPerformed

    private void diaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaSemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaSemanaActionPerformed

    private void dataHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataHorarioActionPerformed

    private void addHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHorarioActionPerformed
        // TODO add your handling code here:
        try {
            adicionaHorarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addHorarioActionPerformed

    private void horarioDiferenciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horarioDiferenciadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horarioDiferenciadoActionPerformed

    private void removeHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeHorarioActionPerformed
        // TODO add your handling code here:
        removeHorario();
    }//GEN-LAST:event_removeHorarioActionPerformed

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
            java.util.logging.Logger.getLogger(profissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new profissional().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addHorario;
    private javax.swing.JTextField bairro;
    private javax.swing.JButton botaoSalvarPaciente;
    private javax.swing.JButton cancelarPaciente;
    private javax.swing.JTextField celular;
    private javax.swing.JTextField cep;
    private javax.swing.JTextField complemento1;
    private javax.swing.JTextField conselho;
    private javax.swing.JTextField conselhoRegional;
    private javax.swing.JTextField cpf;
    private javax.swing.JFormattedTextField dataHorario;
    private javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JComboBox<Object> diaSemana;
    private javax.swing.JTextField email;
    private javax.swing.JFormattedTextField fim;
    private javax.swing.JCheckBox horarioDiferenciado;
    private javax.swing.JCheckBox horarioSuspenso;
    private javax.swing.JComboBox<Object> idCidade;
    private javax.swing.JComboBox<Object> idCidadeNascimento;
    private javax.swing.JComboBox<Object> idEstadoCivil;
    private javax.swing.JComboBox<Object> idEstadoConselho;
    private javax.swing.JComboBox<Object> idTitulacao;
    private javax.swing.JFormattedTextField inicio;
    private javax.swing.JFormattedTextField intervalo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField logradouro;
    private javax.swing.JTextField mae;
    private javax.swing.JTextField nome;
    private javax.swing.JFormattedTextField numero;
    private javax.swing.JTextArea observacao;
    private javax.swing.JTextField pai;
    private javax.swing.JButton removeHorario;
    private javax.swing.JTextField rg;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTable tabelaHorarios;
    private javax.swing.JTextField telefone;
    // End of variables declaration//GEN-END:variables
}
