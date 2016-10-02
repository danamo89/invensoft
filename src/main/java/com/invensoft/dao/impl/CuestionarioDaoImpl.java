/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.ICuestionarioDao;
import com.invensoft.model.Cuestionario;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Scope("singleton")
@Component("cuestionarioDao")
public class CuestionarioDaoImpl extends GenericDaoImpl<Cuestionario, Integer> implements ICuestionarioDao {

    @Override
    public Cuestionario findCuestionarioSaludOcupacional() {
        Query query = getEntityManager().createQuery("SELECT c FROM Cuestionario c WHERE c.idCuestionario = (SELECT co.valor FROM Configuracion co WHERE co.parametro = 'CUEST_SO')");
        return (Cuestionario)query.getSingleResult();
    }

    @Override
    public Cuestionario findCuestionarioDesarrolloProfesional() {
        Query query = getEntityManager().createQuery("SELECT c FROM Cuestionario c WHERE c.idCuestionario = (SELECT co.valor FROM Configuracion co WHERE co.parametro = 'CUEST_DP')");
        return (Cuestionario)query.getSingleResult();
    }
    
}
