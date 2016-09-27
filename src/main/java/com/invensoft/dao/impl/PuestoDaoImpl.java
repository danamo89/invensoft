/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IPuestoDao;
import com.invensoft.model.Puesto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nanchy-PC
 */
@Scope("singleton")
@Component("puestoDao")
public class PuestoDaoImpl extends GenericDaoImpl<Puesto, Integer> implements IPuestoDao {
    
}
