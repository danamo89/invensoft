/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IPaisDao;
import com.invensoft.model.Pais;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("paisDao")
public class PaisDaoImpl extends GenericDaoImpl<Pais, Integer> implements IPaisDao {
    
}
