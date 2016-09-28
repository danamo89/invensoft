/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IRespuestaPreguntaDao;
import com.invensoft.model.Cuestionario;
import com.invensoft.model.Persona;
import com.invensoft.model.RespuestaPregunta;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope("singleton")
@Component("respuestaPreguntaDao")
public class RespuestaPreguntaDaoImpl extends GenericDaoImpl<RespuestaPregunta, Integer> implements IRespuestaPreguntaDao {

    @Override
    public List<RespuestaPregunta> findBy(Persona persona, Cuestionario cuestionario) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("   SELECT rp ");
        jpql.append("     FROM RespuestaPregunta rp ");
        jpql.append("    WHERE rp.pregunta.grupoPreguntas.cuestionario.idCuestionario = :idCuestionario ");
        jpql.append("      AND rp.persona.idPersona = :idPersona");
        
        Query query = getEntityManager().createQuery(jpql.toString());
        query.setParameter("idCuestionario", cuestionario.getIdCuestionario());
        query.setParameter("idPersona", persona.getIdPersona());
        
        return (List<RespuestaPregunta>)query.getResultList();
    }
    
}
