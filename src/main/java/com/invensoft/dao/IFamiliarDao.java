/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.Familiar;
import java.util.List;

/**
 *
 * @author David
 */
public interface IFamiliarDao {
    
    List<Familiar> findAll() throws Exception;
    void save(Familiar familiar) throws Exception;
}
