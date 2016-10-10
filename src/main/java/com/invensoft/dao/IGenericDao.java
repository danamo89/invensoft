/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 * @param <T>
 * @param <ID>
 */
public interface IGenericDao<T, ID extends Serializable> extends Serializable {
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public T find(ID id) throws Exception;

    /**
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public List<T> findAll() throws Exception;

    /**
     * 
     * @param field
     * @return 
     * @throws java.lang.Exception 
     */
    public List<T> findAllOrderBy(String field) throws Exception;
    
    /**
     * 
     * @param fieldsAndValues
     * @param orders
     * @return
     * @throws Exception 
     */
    public List<T> findByOrderBy(Map<String, Object> fieldsAndValues, String[] orders) throws Exception;

    /**
     * 
     * @param entity
     * @throws Exception 
     */
    public void save(T entity) throws Exception;
    
    /**
     * 
     * @param entities
     * @throws Exception 
     */
    public void save(List<T> entities) throws Exception;
    
    /**
     * 
     * @param id 
     * @throws java.lang.Exception 
     */
    public void delete(ID id) throws Exception;
    
}
