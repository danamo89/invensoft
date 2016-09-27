/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IGenericDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements IGenericDao<T, ID> {
    
    @PersistenceContext(unitName = "invensoftPU")
    private EntityManager em;
    
    @SuppressWarnings("FieldMayBeFinal")
    private Class<T> persistentClass;

    protected EntityManager getEntityManager(){
        return em;
    }

    public GenericDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public T find(ID id) {
        T entidad = (T) getEntityManager().find(getPersistentClass(), id);
        return entidad;

    }

    @Override
    public List<T> findAll() {
        @SuppressWarnings("JPQLValidation")
        Query query = getEntityManager().createQuery("SELECT e From " + getPersistentClass().getSimpleName() + " As e");
        return (List<T>) query.getResultList();
    }
    
    @Transactional
    @Override
    public synchronized void save(T entity) throws Exception {
        getEntityManager().merge(entity);
    }
    
    @Transactional
    @Override
    public void save(List<T> entities) throws Exception {
        for (T entity : entities) {
            entity = getEntityManager().merge(entity);
        }
    }
    
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
}
