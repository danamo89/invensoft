/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IGremioDao;
import com.invensoft.model.Gremio;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component(value = "gremioDao")
public class GremioDaoImpl extends GenericDaoImpl<Gremio, Integer> implements IGremioDao {
    
    
}