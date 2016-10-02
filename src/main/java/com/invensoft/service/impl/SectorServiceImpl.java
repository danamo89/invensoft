/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ISectorDao;
import com.invensoft.model.Sector;
import com.invensoft.service.ISectorService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "sectorService")
public class SectorServiceImpl implements ISectorService {

    @Autowired
    private ISectorDao sectorDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public Sector find(Integer sectorId) {
        try {
            return sectorDao.find(sectorId);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el sector", e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Sector> findAll() {
        try {
            return sectorDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de sectores", e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Sector sector) {
        try {
            sectorDao.save(sector);
            messageBean.addInfo("Datos guardados!", "Se han guardado los datos de forma correcta.");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al guardar los datos del sector", e.getMessage());
            e.printStackTrace();
        }
    }

    
    
}
