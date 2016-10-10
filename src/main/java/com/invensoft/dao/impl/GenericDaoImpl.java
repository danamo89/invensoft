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
import java.util.Map;
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

    protected EntityManager getEntityManager() {
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

    @Override
    public List<T> findAllOrderBy(String field) {
        @SuppressWarnings("JPQLValidation")
        Query query = getEntityManager().createQuery("SELECT e From " + getPersistentClass().getSimpleName() + " As e ORDER BY e." + field);
        return (List<T>) query.getResultList();
    }

    @Override
    public List<T> findByOrderBy(Map<String, Object> fieldsAndValues, String[] orders) {
        StringBuilder jpql = new StringBuilder("SELECT e From " + getPersistentClass().getSimpleName() + " As e WHERE 1=1 ");

        for (String key : fieldsAndValues.keySet()) {
            jpql.append(" AND e.").append(key).append(" = ").append(fieldsAndValues.get(key));
        }

        if (orders != null && orders.length > 0) {
            jpql.append(" ORDER BY ");

            for (int i = 0; i < orders.length; i++) {
                jpql.append(" e.").append(orders[i]).append(i==0?"":i<orders.length-1?",":"");
            }
        }

        Query query = getEntityManager().createQuery(jpql.toString());
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
    
    @Transactional
    @Override
    public void delete(ID id) throws Exception {
        T entidad = getEntityManager().getReference(getPersistentClass(), id);
        getEntityManager().remove(entidad);
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
}
