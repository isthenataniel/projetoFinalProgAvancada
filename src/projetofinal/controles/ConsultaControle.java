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
import projetofinal.modelos.Consulta;
import projetofinal.modelos.Paciente;
import projetofinal.modelos.Profissional;
import projetofinal.modelos.Situacao;

/**
 *
 * @author root
 */
public class ConsultaControle extends Controle<Consulta> {
    
    public Integer salvar(Consulta consulta) {
        try {
            if (consulta.getId() == null) {
                String sql = "INSERT INTO CONSULTA (IDPROFISSIONAL, "
                                                 + "IDPACIENTE,"
                                                 + "DATA,"
                                                 + "MOTIVO,"
                                                 + "DIAGNOSTICO,"
                                                 + "OBSERVACAO,"
                                                 + "STATUS,"
                                                 + "VALOR) VALUES(?,?,?,?,?,?,?,?)";
                Object[] parametros = {consulta.getIdProfissional(), 
                                       consulta.getIdPaciente(), 
                                       consulta.getData(),
                                       consulta.getMotivo(),    
                                       consulta.getDiagnostico(),
                                       consulta.getObservacao(),
                                       consulta.getStatus(),
                                       consulta.getValor()};
                return executaQuery(sql, parametros);
            } else {
                String sql = "UPDATE CONSULTA SET IDPROFISSIONAL = ?, "
                                               + "IDPACIENTE = ?,"
                                               + "DATA = ?, "
                                               + "MOTIVO = ?, "
                                               + "DIAGNOSTICO = ?, "
                                               + "OBSERVACAO = ?, "
                                               + "STATUS = ?, "
                                               + "VALOR = ? "
                                               + "WHERE ID = ?";
                Object[] parametros = {consulta.getIdProfissional(), 
                                       consulta.getIdPaciente(), 
                                       consulta.getData(),
                                       consulta.getMotivo(),    
                                       consulta.getDiagnostico(),
                                       consulta.getObservacao(),
                                       consulta.getStatus(),
                                       consulta.getValor(),
                                       consulta.getId()};
                return executaQuery(sql, parametros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Consulta obterConsulta(Integer idConsulta) throws SQLException {
        Consulta consulta = new Consulta();
        Object[] parametros = {idConsulta};
        ResultSet result = obtemConsulta("SELECT * "
                                        + " FROM CONSULTA "
                                       + " WHERE ID = ?", 
                                        parametros);
        if (result.next()) {
            consulta.setId(result.getInt("ID"));
            consulta.setIdProfissional(result.getInt("IDPROFISSIONAL"));
            consulta.setIdPaciente(result.getInt("IDPACIENTE"));
            consulta.setData(result.getDate("DATA"));
            consulta.setMotivo(result.getString("MOTIVO"));
            consulta.setDiagnostico(result.getString("DIAGNOSTICO"));
            consulta.setObservacao(result.getString("OBSERVACAO"));
            consulta.setStatus(result.getString("STATUS").charAt(0));
            consulta.setValor(result.getDouble("VALOR"));
        }
        return consulta;
    }
    
    public Integer excluir(Integer idConsulta) {
        try {
            Object[] parametros = {idConsulta};
            String sql = "DELETE FROM CONSULTA WHERE ID = ?;";
            executaQuery(sql, parametros);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Consulta> obtemTodasConsultas() throws SQLException {
        List<Consulta> consultas = new ArrayList<Consulta>();
        ResultSet result = obtemConsulta("SELECT CONSULTA.*, "
                                              + "TO_CHAR(CONSULTA.DATA, 'dd/MM/yyyy') AS DATA_F,"
                                              + "PESPROFI.NOME AS PROFISSIONAL_NOME, "
                                              + "PESPACI.NOME AS PACIENTE_NOME, "
                                              + "SITUACAO.DESCRICAO AS STATUS_DESCRICAO "
                                         + "FROM CONSULTA "
                                   + "INNER JOIN PROFISSIONAL ON (IDPROFISSIONAL = PROFISSIONAL.ID)  "
                                   + "INNER JOIN PESSOA PESPROFI ON (PESPROFI.ID= PROFISSIONAL.ID)  "
                                   + "INNER JOIN PACIENTE ON (IDPACIENTE= PACIENTE.ID)  "
                                   + "INNER JOIN PESSOA PESPACI ON (PESPACI.ID= PACIENTE.ID)  "
                                   + "INNER JOIN SITUACAO ON STATUS::INTEGER = SITUACAO.ID "
                                     + "ORDER BY DATA ASC;");
        while (result.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(result.getInt("ID"));
            consulta.setIdProfissional(result.getInt("IDPROFISSIONAL"));
            consulta.setIdPaciente(result.getInt("IDPACIENTE"));
            consulta.setData(result.getDate("DATA"));
            consulta.setMotivo(result.getString("MOTIVO"));
            consulta.setDiagnostico(result.getString("DIAGNOSTICO"));
            consulta.setObservacao(result.getString("OBSERVACAO"));
            consulta.setStatus(result.getString("STATUS").charAt(0));
            consulta.setValor(result.getDouble("VALOR"));
            consulta.setPaciente(new Paciente());
            consulta.getPaciente().setNome(result.getString("PACIENTE_NOME"));
            consulta.setProfissional(new Profissional());
            consulta.getProfissional().setNome(result.getString("PROFISSIONAL_NOME"));
            consulta.setSituacao(new Situacao());
            consulta.getSituacao().setDescricao(result.getString("STATUS_DESCRICAO"));
            consulta.setDataFormatada(result.getString("DATA_F"));
            consultas.add(consulta);
        }
        return consultas;
    }
    
    public ResultSet obtemConsultaRelatorio() {
        return obtemConsulta("SELECT CONSULTA.ID, TO_CHAR(CONSULTA.DATA, 'dd/MM/yyyy') AS DATA, PESSOA.nome AS NOMEPACIENTE, E.nome AS NOMEPROFISSIONAL, F.DESCRICAO AS SITUACAO" +
                            "  FROM CONSULTA " +
                            "INNER JOIN PACIENTE ON (CONSULTA.IDPACIENTE = PACIENTE.ID)" +
                            "INNER JOIN PESSOA ON (PACIENTE.ID=PESSOA.ID)" +
                            "INNER JOIN PROFISSIONAL ON (CONSULTA.IDPROFISSIONAL=PROFISSIONAL.ID)" +
                            "INNER JOIN PESSOA E ON (PROFISSIONAL.ID=E.ID)" +
                            "INNER JOIN SITUACAO F ON (CONSULTA.STATUS::INTEGER = F.ID)" +
                            " ORDER BY CONSULTA.DATA, CONSULTA.IDPACIENTE ASC;");
    }
}
