/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "pessoafisica")
public class PessoaFisica extends Pessoa implements Serializable {
    
    @Column(name = "sexo")
    private Character sexo;
    
    @Column(name = "datanascimento")
    private Date dataNascimento;
    
    @Column(name = "mae")
    private String mae;
    
    @Column(name = "pai")
    private String pai;
    
    @Column(name = "rg")
    private String rg;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "idcidadenascimento")
    private Integer idCidadeNascimento;
    
    @Column(name = "idestadocivil")
    private Integer idEstadoCivil;
    
    @Column(name = "idtitulacao")
    private Integer idTitulacao;

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdCidadeNascimento() {
        return idCidadeNascimento;
    }

    public void setIdCidadeNascimento(Integer idCidadeNascimento) {
        this.idCidadeNascimento = idCidadeNascimento;
    }

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Integer getIdTitulacao() {
        return idTitulacao;
    }

    public void setIdTitulacao(Integer idTitulacao) {
        this.idTitulacao = idTitulacao;
    }
    
}
