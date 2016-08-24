/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IPersonaDao;
import com.invensoft.model.Persona;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("personaDao") 
public class PersonaDaoImpl extends GenericDao implements IPersonaDao {
    
    @Override
    public List<Persona> findAll() throws Exception {
        Query q = getEntityManager().createNamedQuery("Persona.findAll");
        return (List<Persona>) q.getResultList();
    }

    @Override
    @Transactional
    public void save(Persona persona) throws Exception {
        System.out.println("Fecha de nacimiento: " + persona.getFechaNacimiento());
        getEntityManager().merge(persona);
    }
    
}
