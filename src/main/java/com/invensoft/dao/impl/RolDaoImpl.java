/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IRolDao;
import com.invensoft.model.Rol;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnavarro
 */
@Scope("singleton")
@Component("rolDao")
public class RolDaoImpl extends GenericDaoImpl<Rol, Integer> implements IRolDao {
    
}
