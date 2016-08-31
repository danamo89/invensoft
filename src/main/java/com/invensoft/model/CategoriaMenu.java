/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "categorias_menus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaMenu.findAll", query = "SELECT c FROM CategoriaMenu c"),
    @NamedQuery(name = "CategoriaMenu.findByIdCategoriaMenu", query = "SELECT c FROM CategoriaMenu c WHERE c.idCategoriaMenu = :idCategoriaMenu"),
    @NamedQuery(name = "CategoriaMenu.findByNombre", query = "SELECT c FROM CategoriaMenu c WHERE c.nombre = :nombre")})
public class CategoriaMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA_MENU", nullable = false)
    private Integer idCategoriaMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaMenu", fetch = FetchType.LAZY)
    private List<Menu> menuList;

    public CategoriaMenu() {
    }

    public CategoriaMenu(Integer idCategoriaMenu) {
        this.idCategoriaMenu = idCategoriaMenu;
    }

    public CategoriaMenu(Integer idCategoriaMenu, String nombre) {
        this.idCategoriaMenu = idCategoriaMenu;
        this.nombre = nombre;
    }

    public Integer getIdCategoriaMenu() {
        return idCategoriaMenu;
    }

    public void setIdCategoriaMenu(Integer idCategoriaMenu) {
        this.idCategoriaMenu = idCategoriaMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaMenu != null ? idCategoriaMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaMenu)) {
            return false;
        }
        CategoriaMenu other = (CategoriaMenu) object;
        if ((this.idCategoriaMenu == null && other.idCategoriaMenu != null) || (this.idCategoriaMenu != null && !this.idCategoriaMenu.equals(other.idCategoriaMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.CategoriaMenu[ idCategoriaMenu=" + idCategoriaMenu + " ]";
    }
    
}
