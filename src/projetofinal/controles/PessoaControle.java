/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import projetofinal.modelos.Pessoa;

/**
 *
 * @author root
 */
public class PessoaControle extends Controle<Pessoa> {
    
    public boolean verificaPermissaoUsuario(String login, String senha) {
        String sql = "SELECT COUNT(*) AS PERMISSAO  FROM Pessoa WHERE LOGIN = '"+login+"' AND SENHA = MD5('"+senha+"')";
        Object objeto = null;
        Session sessao = null;
        try {
            sessao = NewHibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery(sql);
            objeto = query.uniqueResult();
            transacao.commit();
        } catch (Exception hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
        
        return (objeto != null && (Long)objeto == 1);
    }
    
    @SuppressWarnings("empty-statement")
    protected Integer savar(Pessoa pessoa) throws SQLException {
        String sql = "";
        Integer id = null;
        if (pessoa.getId() != null) {
            sql = "UPDATE PESSOA SET LOGIN = ?,"
                                + " NOME = ?,"
                                + " SENHA = ?,"
                                + " EMAIL = ?,"
                                + " TELEFONE = ?,"
                                + " CELULAR = ?,"
                                + " CEP  = ?,"
                                + " OBSERVACAO = ?,"
                                + " LOGRADOURO = ?,"
                                + " NUMERO = ?,"
                                + " BAIRRO = ?,"
                                + " COMPLEMENTO = ?,"
                                + " IDCIDADE = ?"
                                + " WHERE ID = ?";
            Object[] parametros = {pessoa.getLogin(), pessoa.getNome(), pessoa.getSenha(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getCelular(), pessoa.getCep(), pessoa.getObservacao(), pessoa.getLogradouro(), pessoa.getNumero(), pessoa.getBairro(), pessoa.getComplemento(), pessoa.getIdCidade(), pessoa.getId()};
            executaQuery(sql, parametros);
        } else {
            ResultSet result = obtemConsulta("SELECT nextval('pessoa_id_seq') AS ID");
            id = result.next() ? result.getInt("ID") : 1;
            sql = "INSERT INTO PESSOA (ID,LOGIN,NOME,SENHA,EMAIL,TELEFONE,CELULAR,CEP,OBSERVACAO,LOGRADOURO,NUMERO,BAIRRO,COMPLEMENTO,IDCIDADE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Object[] parametros = {id,pessoa.getLogin(), pessoa.getNome(), pessoa.getSenha(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getCelular(), pessoa.getCep(), pessoa.getObservacao(), pessoa.getLogradouro(), pessoa.getNumero(), pessoa.getBairro(), pessoa.getComplemento(), pessoa.getIdCidade()};
            executaQuery(sql, parametros);
        }
        return savarPessoaFisica(pessoa, id);
    } 
    
    @SuppressWarnings("empty-statement")
    protected Integer savarPessoaFisica(Pessoa pessoa, Integer id) {
        String sql = "";
        if (pessoa.getId() != null) {
            sql = "UPDATE PESSOAFISICA SET SEXO = ?,"
                                        + " DATANASCIMENTO = ?,"
                                        + " MAE = ?,"
                                        + " PAI = ?,"
                                        + " RG = ?,"
                                        + " CPF = ?,"
                                        + " IDCIDADENASCIMENTO = ?,"
                                        + " IDESTADOCIVIL = ?,"
                                        + " IDTITULACAO = ?"
                                        + " WHERE ID = ?";
            Object[] parametros = {pessoa.getSexo(), pessoa.getDataNascimento(), pessoa.getMae(), pessoa.getPai(), pessoa.getRg(), pessoa.getCpf(), pessoa.getIdCidadeNascimento(), pessoa.getIdEstadoCivil(), pessoa.getIdTitulacao(), pessoa.getId()};
            executaQuery(sql, parametros);
            return pessoa.getId();
        } else {
            sql = "INSERT INTO PESSOAFISICA (ID,SEXO,DATANASCIMENTO,MAE,PAI,RG,CPF,IDCIDADENASCIMENTO,IDESTADOCIVIL,IDTITULACAO) VALUES(?,?,?,?,?,?,?,?,?,?)";
            Object[] parametros = {id, pessoa.getSexo(), pessoa.getDataNascimento(), pessoa.getMae(), pessoa.getPai(), pessoa.getRg(), pessoa.getCpf(), pessoa.getIdCidadeNascimento(), pessoa.getIdEstadoCivil(), pessoa.getIdTitulacao()};
            executaQuery(sql, parametros);
            return id;
        }
    }
}
