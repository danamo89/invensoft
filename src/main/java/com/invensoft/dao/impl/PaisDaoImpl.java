/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IPaisDao;
import com.invensoft.model.Pais;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("paisDao")
public class PaisDaoImpl extends GenericDao implements IPaisDao {

    @Override
    public List<Pais> findAll() throws Exception {
        Query q = getEntityManager().createNamedQuery("Pais.findAll");
        return (List<Pais>) q.getResultList();
    }
    
}
