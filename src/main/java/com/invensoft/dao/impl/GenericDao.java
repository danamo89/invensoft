/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */
public class GenericDao {
    
    @PersistenceContext(unitName = "invensoftPU")
    private EntityManager em;

    protected EntityManager getEntityManager(){
        return em;
    }
    
}
