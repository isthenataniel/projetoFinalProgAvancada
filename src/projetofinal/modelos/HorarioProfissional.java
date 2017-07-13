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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "exame")
public class HorarioProfissional implements Serializable {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "idprofissional")
    private Integer idProfissional;
    
    @Column(name = "inicio")
    private Date inicio;
    
    @Column(name = "fim")
    private Date fim;
    
    @Column(name = "intervalo")
    private Date intervalo;
    
    @Column(name = "dia")
    private Character dia;
    
    @Column(name = "data")
    private Date data;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @Column(name = "horariosuspenso")
    private boolean horarioSuspenso;
    
    @Column(name = "horarioDiferenciado")
    private boolean horarioDiferenciado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Date intervalo) {
        this.intervalo = intervalo;
    }

    public Character getDia() {
        return dia;
    }

    public void setDia(Character dia) {
        this.dia = dia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isHorarioSuspenso() {
        return horarioSuspenso;
    }

    public void setHorarioSuspenso(boolean horarioSuspenso) {
        this.horarioSuspenso = horarioSuspenso;
    }

    public boolean isHorarioDiferenciado() {
        return horarioDiferenciado;
    }

    public void setHorarioDiferenciado(boolean horarioDiferenciado) {
        this.horarioDiferenciado = horarioDiferenciado;
    }

}
