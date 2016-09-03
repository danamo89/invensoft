/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IPersonaDao;
import com.invensoft.model.Persona;
import com.invensoft.service.IPersonaService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "personaService")
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaDao personaDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Persona> findAll() {
        try {
            return personaDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de personas", e.getMessage());
            return null;
        }
    }

    @Override 
    public void save(Persona persona) {
        try {
            personaDao.save(persona);
            messageBean.addInfo("Datos guardados!", "Se han guardado los datos de forma correcta.");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al guardar los datos de la persona seleccionada", e.getMessage());
            e.printStackTrace();
        }
    }
    
}
