/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ICuestionarioDao;
import com.invensoft.model.Cuestionario;
import com.invensoft.service.ICuestionarioService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "cuestionarioService")
public class CuestionarioServiceImpl implements ICuestionarioService {

    @Autowired
    private ICuestionarioDao cuestionarioDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Cuestionario> findAll() {
        try {
            return cuestionarioDao.findAllOrderBy("descripcion");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de cuestionarios", e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Cuestionario cuestionario) {
        try {
            cuestionarioDao.save(cuestionario);
            messageBean.addInfo("Datos guardados!", "Se han guardado los datos de forma correcta.");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al guardar los datos del cuestionario", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Cuestionario findCuestionarioSaludOcupacional() {
        try {
            return cuestionarioDao.findCuestionarioSaludOcupacional();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cuestionario findCuestionarioDesarrolloProfesional() {
        try {
            return cuestionarioDao.findCuestionarioDesarrolloProfesional();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
