/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.service.ICargoService;
import com.invensoft.dao.ICargoDao;
import com.invensoft.model.Cargo;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "cargoService")
public class CargoServiceImpl implements ICargoService {
    
    @Autowired
    private ICargoDao cargoDao;
    @Autowired
    private MessageBean messageBean;

    @Override
    public List<Cargo> findAll() {
        try {
            return cargoDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
}
