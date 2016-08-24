/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.ITipoIdentificacionDao;
import com.invensoft.model.TipoIdentificacion;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("tipoIdentificacionDao")
public class TipoIdentificacionDaoImpl extends GenericDao implements ITipoIdentificacionDao {

    @Override
    public List<TipoIdentificacion> findAll() throws Exception {
        Query q = getEntityManager().createNamedQuery("TipoIdentificacion.findAll");
        return (List<TipoIdentificacion>) q.getResultList();
    }
    
}
