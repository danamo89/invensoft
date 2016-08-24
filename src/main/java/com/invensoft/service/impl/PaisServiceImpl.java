/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IPaisDao;
import com.invensoft.model.Pais;
import com.invensoft.service.IPaisService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "paisService")
public class PaisServiceImpl implements IPaisService {

    @Autowired
    private IPaisDao paisDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Pais> findAll() {
        try {
            return (List<Pais>) paisDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
    
}
