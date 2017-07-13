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
import projetofinal.modelos.Profissional;

/**
 *
 * @author root
 */
public class ProfissionalControle extends PessoaControle {
    
    public List<Object[]> obterLista() throws SQLException {
        ResultSet result = obtemConsulta("SELECT PROFISSIONAL.ID, PESSOA.NOME, PROFISSIONAL.CONSELHO , PROFISSIONAL.CONSELHOREGIONAL "
                                        + " FROM PROFISSIONAL "
                                  + " INNER JOIN PESSOA USING(ID)"
                                  + " INNER JOIN PESSOAFISICA ON(PESSOA.ID = PESSOAFISICA.ID)");
        List<Object[]> dados = new ArrayList<Object[]>();
        while (result.next()) {
            Object[] row = {result.getObject("ID"), result.getObject("NOME"), result.getObject("CONSELHO"), result.getObject("CONSELHOREGIONAL")};  
            dados.add(row);
        }
        return dados;
    }
    
    public Integer excluir(Integer idProfissional) {
        try {
            Object[] parametros = {idProfissional};
            String sql = "DELETE FROM PROFISSIONAL WHERE ID = ?;";
            executaQuery(sql, parametros);

            String sqlPessoaFisica = "DELETE FROM PESSOAFISICA WHERE ID = ?;";
            executaQuery(sql, parametros);

            String sqlPessoa = "DELETE FROM PESSOA WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Profissional obterProfissional(Integer idProfissional) throws SQLException {
        Profissional profissional = new Profissional();
        Object[] parametros = {idProfissional};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM PROFISSIONAL "
                                  + " INNER JOIN PESSOA USING(ID)"
                                  + " INNER JOIN PESSOAFISICA ON(PESSOA.ID = PESSOAFISICA.ID)"
                                       + " WHERE PROFISSIONAL.ID = ?", 
                                        parametros);
        if (result.next()) {
            profissional.setId(result.getInt("ID"));
            profissional.setConselho(result.getString("CONSELHO"));
            profissional.setConselhoRegional(result.getString("CONSELHOREGIONAL"));
            profissional.setIdEstadoConselho(result.getInt("IDESTADOCONSELHO"));
            /*PESSOA FISICA*/
            profissional.setSexo(result.getString("SEXO").charAt(0));
            profissional.setDataNascimento(result.getDate("DATANASCIMENTO"));
            profissional.setMae(result.getString("MAE"));
            profissional.setPai(result.getString("PAI"));
            profissional.setRg(result.getString("RG"));
            profissional.setCpf(result.getString("CPF"));
            profissional.setIdCidadeNascimento(result.getInt("IDCIDADENASCIMENTO"));
            profissional.setIdEstadoCivil(result.getInt("IDESTADOCIVIL"));
            profissional.setIdTitulacao(result.getInt("IDTITULACAO"));
            /*PESSOA*/
            profissional.setLogin(result.getString("LOGIN"));
            profissional.setSenha(result.getString("SENHA"));
            profissional.setNome(result.getString("NOME"));
            profissional.setEmail(result.getString("EMAIL"));
            profissional.setTelefone(result.getString("TELEFONE"));
            profissional.setCelular(result.getString("CELULAR"));
            profissional.setCep(result.getString("CEP"));
            profissional.setObservacao(result.getString("OBSERVACAO"));
            profissional.setLogradouro(result.getString("LOGRADOURO"));
            profissional.setNumero(result.getInt("NUMERO"));
            profissional.setBairro(result.getString("BAIRRO"));
            profissional.setComplemento(result.getString("COMPLEMENTO"));
            profissional.setIdCidade(result.getInt("IDCIDADE"));
        }
        return profissional;
    }
    
    public Integer salvar(Profissional profissional) throws SQLException {
//        Integer id = super.savar(profissional);
//        if (profissional.getId() != null) {
//            String sql = "UPDATE PROFISSIONAL SET CONSELHO = ?, CONSELHOREGIONAL = ?, IDESTADOCONSELHO = ? WHERE ID = ?";
//            Object[] parametros = {profissional.getConselho(), profissional.getConselhoRegional(), profissional.getIdEstadoConselho(), profissional.getId()};
//            executaQuery(sql, parametros);
//            id = profissional.getId();
//        } else {
//            String sql = "INSERT INTO PROFISSIONAL (ID,CONSELHO,CONSELHOREGIONAL,IDESTADOCONSELHO) VALUES(?,?,?,?)";
//            Object[] parametros = {id, profissional.getConselho(), profissional.getConselhoRegional(), profissional.getIdEstadoConselho()};
//            executaQuery(sql, parametros);
//        }
        return 1;
    }
    
    public List<Profissional> obtemTodosTodosProfissionais() throws SQLException {
        List<Profissional> profissionais = new ArrayList<Profissional>();
        ResultSet result = obtemConsulta("SELECT PROFISSIONAL.ID,"
                                              + "PESSOA.NOME "
                                         + "FROM PROFISSIONAL "
                                   + "INNER JOIN PESSOA USING(ID) "
                                        + "ORDER BY PESSOA.NOME ASC;");
        while (result.next()) {
            Profissional profissional = new Profissional();
            profissional.setId(result.getInt("ID"));
            profissional.setNome(result.getString("NOME"));
            profissionais.add(profissional);
        }
        return profissionais;
    }
}
