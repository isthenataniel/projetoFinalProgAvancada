/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "profissional")
public class Profissional extends PessoaFisica {
     
    @Column(name = "conselho")
    private String conselho;
    
    @Column(name = "conselhoregional")
    private String conselhoRegional;
    
    @Column(name = "idestadoconselho")
    private Integer idEstadoConselho;

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public String getConselhoRegional() {
        return conselhoRegional;
    }

    public void setConselhoRegional(String conselhoRegional) {
        this.conselhoRegional = conselhoRegional;
    }

    public Integer getIdEstadoConselho() {
        return idEstadoConselho;
    }

    public void setIdEstadoConselho(Integer idEstadoConselho) {
        this.idEstadoConselho = idEstadoConselho;
    }
}
