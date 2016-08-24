/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IFamiliarDao;
import com.invensoft.model.Familiar;
import com.invensoft.service.IFamiliarService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author David
 */
public class FamiliarServiceImpl implements IFamiliarService {

    @Autowired
    private IFamiliarDao familiarDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Familiar> findAll() {
        try {
            return familiarDao.findAll();
        } catch(Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar los datos familiares", e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Familiar familiar) {
        try {
            familiarDao.save(familiar);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al guardar los datos familiares", e.getMessage());
        }
    }
    
}
