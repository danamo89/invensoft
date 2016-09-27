/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IConfiguracionDao;
import com.invensoft.model.Configuracion;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope("singleton")
@Component("configuracionDao")
public class ConfiguracionDaoImpl extends GenericDaoImpl<Configuracion, Integer> implements IConfiguracionDao {

    @Override
    public Configuracion findBy(String parametro) {
        Query query = getEntityManager().createNamedQuery("Configuracion.findByParametro");
        query.setParameter("parametro", parametro);
        return (Configuracion)query.getSingleResult();
    }
    
}
