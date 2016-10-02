/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.ICategoriaLaboralDao;
import com.invensoft.model.CategoriaLaboral;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component(value = "categoriaLaboralDao")
public class CategoriaLaboralDaoImpl extends GenericDaoImpl<CategoriaLaboral, Integer> implements ICategoriaLaboralDao {
    
    
}
