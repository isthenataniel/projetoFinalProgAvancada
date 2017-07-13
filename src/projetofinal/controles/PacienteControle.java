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
import projetofinal.modelos.Paciente;

/**
 *
 * @author root
 */
public class PacienteControle extends PessoaControle {
    
    public Integer salvar(Paciente paciente) throws SQLException {
        Integer id = super.savar(paciente);
        if (paciente.getId() != null) {
            String sql = "UPDATE PACIENTE SET DATA = ? WHERE ID = ?";
            Object[] parametros = {paciente.getData(), paciente.getId()};
            return executaQuery(sql, parametros);
        } else {
            String sql = "INSERT INTO PACIENTE (ID,DATA) VALUES(?,?)";
            Object[] parametros = {id, paciente.getData()};
            return executaQuery(sql, parametros);
        }
//        return id;
    }
    
    public List<Object[]> obterLista() throws SQLException {
        ResultSet result = obtemConsulta("SELECT PACIENTE.ID, PESSOA.NOME, TO_CHAR(PESSOAFISICA.DATANASCIMENTO, 'DD/MM/YYYY') AS DTNASC, PESSOAFISICA.CPF "
                                        + " FROM PACIENTE "
                                  + " INNER JOIN PESSOA USING(ID)"
                                  + " INNER JOIN PESSOAFISICA ON(PESSOA.ID = PESSOAFISICA.ID)");
        List<Object[]> dados = new ArrayList<Object[]>();
        while (result.next()) {
            Object[] row = {result.getObject("ID"), result.getObject("NOME"), result.getObject("DTNASC"), result.getObject("CPF")};  
            dados.add(row);
        }
        return dados;
    }
    
    public Paciente obterPaciente(Integer idPaciente) throws SQLException {
        Paciente paciente = new Paciente();
        Object[] parametros = {idPaciente};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM PACIENTE "
                                  + " INNER JOIN PESSOA USING(ID)"
                                  + " INNER JOIN PESSOAFISICA ON(PESSOA.ID = PESSOAFISICA.ID)"
                                       + " WHERE PACIENTE.ID = ?", 
                                        parametros);
        if (result.next()) {
            paciente.setId(result.getInt("ID"));
            paciente.setData(result.getDate("DATA"));
            /*PESSOA FISICA*/
            paciente.setSexo(result.getString("SEXO").charAt(0));
            paciente.setDataNascimento(result.getDate("DATANASCIMENTO"));
            paciente.setMae(result.getString("MAE"));
            paciente.setPai(result.getString("PAI"));
            paciente.setRg(result.getString("RG"));
            paciente.setCpf(result.getString("CPF"));
            paciente.setIdCidadeNascimento(result.getInt("IDCIDADENASCIMENTO"));
            paciente.setIdEstadoCivil(result.getInt("IDESTADOCIVIL"));
            paciente.setIdTitulacao(result.getInt("IDTITULACAO"));
            /*PESSOA*/
            paciente.setLogin(result.getString("LOGIN"));
            paciente.setSenha(result.getString("SENHA"));
            paciente.setNome(result.getString("NOME"));
            paciente.setEmail(result.getString("EMAIL"));
            paciente.setTelefone(result.getString("TELEFONE"));
            paciente.setCelular(result.getString("CELULAR"));
            paciente.setCep(result.getString("CEP"));
            paciente.setObservacao(result.getString("OBSERVACAO"));
            paciente.setLogradouro(result.getString("LOGRADOURO"));
            paciente.setNumero(result.getInt("NUMERO"));
            paciente.setBairro(result.getString("BAIRRO"));
            paciente.setComplemento(result.getString("COMPLEMENTO"));
            paciente.setIdCidade(result.getInt("IDCIDADE"));
        }
        return paciente;
    }
    
    public Integer excluir(Integer idPaciente) {
        try {
            Object[] parametros = {idPaciente};
            String sql = "DELETE FROM PACIENTE WHERE ID = ?;";
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
    
    public List<Paciente> obterTodosPacientes() throws SQLException {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM PACIENTE "
                                  + " INNER JOIN PESSOA USING(ID)"
                                  + " INNER JOIN PESSOAFISICA ON(PESSOA.ID = PESSOAFISICA.ID)");
        while (result.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(result.getInt("ID"));
            paciente.setData(result.getDate("DATA"));
            /*PESSOA FISICA*/
            paciente.setSexo(result.getString("SEXO").charAt(0));
            paciente.setDataNascimento(result.getDate("DATANASCIMENTO"));
            paciente.setMae(result.getString("MAE"));
            paciente.setPai(result.getString("PAI"));
            paciente.setRg(result.getString("RG"));
            paciente.setCpf(result.getString("CPF"));
            paciente.setIdCidadeNascimento(result.getInt("IDCIDADENASCIMENTO"));
            paciente.setIdEstadoCivil(result.getInt("IDESTADOCIVIL"));
            paciente.setIdTitulacao(result.getInt("IDTITULACAO"));
            /*PESSOA*/
            paciente.setLogin(result.getString("LOGIN"));
            paciente.setSenha(result.getString("SENHA"));
            paciente.setNome(result.getString("NOME"));
            paciente.setEmail(result.getString("EMAIL"));
            paciente.setTelefone(result.getString("TELEFONE"));
            paciente.setCelular(result.getString("CELULAR"));
            paciente.setCep(result.getString("CEP"));
            paciente.setObservacao(result.getString("OBSERVACAO"));
            paciente.setLogradouro(result.getString("LOGRADOURO"));
            paciente.setNumero(result.getInt("NUMERO"));
            paciente.setBairro(result.getString("BAIRRO"));
            paciente.setComplemento(result.getString("COMPLEMENTO"));
            paciente.setIdCidade(result.getInt("IDCIDADE"));
            pacientes.add(paciente);
        }
        return pacientes;
    }
    
    public List<Paciente> obtemPacienteEmUso(Integer idPessoa) throws SQLException {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        Object[] parametros = {idPessoa};
        String sql = "SELECT * FROM CONSULTA WHERE IDPACIENTE = ?;";
        ResultSet result = obtemConsulta(sql, parametros);
        
        while (result.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(result.getInt("ID"));
            pacientes.add(paciente);
        }
        return pacientes;
    }
}
