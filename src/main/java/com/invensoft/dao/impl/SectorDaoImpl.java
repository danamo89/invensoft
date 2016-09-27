/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.ISectorDao;
import com.invensoft.model.Sector;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope("singleton")
@Component("sectorDao")
public class SectorDaoImpl extends GenericDaoImpl<Sector, Integer> implements ISectorDao {
    
}
