/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "idmenupai")
    private Integer idMenuPai;
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "rota")
    private String rota;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @Transient
    private List<Menu> menuFilhos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMenuPai() {
        return idMenuPai;
    }

    public void setIdMenuPai(Integer idMenuPai) {
        this.idMenuPai = idMenuPai;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Menu> getMenuFilhos() {
        return menuFilhos;
    }

    public void setMenuFilhos(List<Menu> menuFilhos) {
        this.menuFilhos = menuFilhos;
    }
}
