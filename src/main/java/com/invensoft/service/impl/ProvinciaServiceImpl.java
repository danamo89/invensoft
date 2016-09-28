/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IProvinciaDao;
import com.invensoft.model.Provincia;
import com.invensoft.service.IProvinciaService;
import com.invensoft.util.MessageBean;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "provinciaService")
public class ProvinciaServiceImpl implements IProvinciaService {

    @Autowired
    private IProvinciaDao provinciaDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Provincia> findAllOrderBy(String field) {
        List<Provincia> list;
        try {
            list = provinciaDao.findAllOrderBy(field);
            if (list == null) {
                list = new LinkedList<>();
            }
        } catch (Exception e) {
            messageBean.addWarn("Ha ocurrido un error al consultar las provincias", e.getMessage());
            list = new LinkedList<>();
        }
        
        return list;
    }
    
}
