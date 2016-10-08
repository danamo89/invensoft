/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ICategoriaLaboralDao;
import com.invensoft.model.CategoriaLaboral;
import com.invensoft.service.ICategoriaLaboralService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "categoriaLaboralService")
public class CategoriaLaboralServiceImpl implements ICategoriaLaboralService {
    
    @Autowired
    private ICategoriaLaboralDao categoriaLaboralDao;
    @Autowired
    private MessageBean messageBean;

    @Override
    public List<CategoriaLaboral> findAll() {
        try {
            return categoriaLaboralDao.findAllOrderBy("cat");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
    
}
