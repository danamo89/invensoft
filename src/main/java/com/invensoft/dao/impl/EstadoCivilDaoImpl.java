/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IEstadoCivilDao;
import com.invensoft.model.EstadoCivil;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component(value = "estadoCivilDao")
public class EstadoCivilDaoImpl extends GenericDao implements IEstadoCivilDao {

    @Override
    public List<EstadoCivil> findAll() throws Exception {
        Query q = getEntityManager().createNamedQuery("EstadoCivil.findAll");
        return (List<EstadoCivil>) q.getResultList();
    }
    
}
