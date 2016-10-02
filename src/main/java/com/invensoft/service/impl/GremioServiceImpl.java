/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IGremioDao;
import com.invensoft.model.Gremio;
import com.invensoft.service.IGremioService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "gremioService")
public class GremioServiceImpl implements IGremioService {
    
    @Autowired
    private IGremioDao gremioDao;
    @Autowired
    private MessageBean messageBean;

    @Override
    public List<Gremio> findAll() {
        try {
            return gremioDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
    
}
