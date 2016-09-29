/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.ICargoDao;
import com.invensoft.model.Cargo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component(value = "cargoDao")
public class CargoDaoImpl extends GenericDaoImpl<Cargo, Integer> implements ICargoDao {
    
    
}
