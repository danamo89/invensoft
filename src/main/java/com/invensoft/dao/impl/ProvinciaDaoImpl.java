/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IProvinciaDao;
import com.invensoft.model.Provincia;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope("singleton")
@Component("provinciaDao")
public class ProvinciaDaoImpl extends GenericDaoImpl<Provincia, Integer> implements IProvinciaDao {
    
}
