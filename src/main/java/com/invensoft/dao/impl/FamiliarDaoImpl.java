/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IFamiliarDao;
import com.invensoft.model.Familiar;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("familiarDao") 
public class FamiliarDaoImpl extends GenericDao implements IFamiliarDao {

    @Override
    public List<Familiar> findAll() throws Exception {
        Query q = getEntityManager().createNamedQuery("Familiar.findAll");
        return (List<Familiar>) q.getResultList();
    }

    @Override
    @Transactional
    public void save(Familiar familiar) throws Exception {
        getEntityManager().merge(familiar);
    }
    
}
