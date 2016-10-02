/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.testing;

import java.util.List;

/**
 *
 * @author David
 */
public class Carro implements Comparable<Carro> {
    
    private Integer orden;
    private List<Partes> partesList;

    public Carro() {
    }

    public Carro(Integer orden) {
        this.orden = orden;
    }

    public List<Partes> getPartesList() {
        return partesList;
    }

    public void setPartesList(List<Partes> partesList) {
        this.partesList = partesList;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public int compareTo(Carro o) {
        return orden.compareTo(o.orden);
    }
    
}
