/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.Usuario;

/**
 *
 * @author Admin
 */
public interface UsuarioDao {
    
    boolean save(Usuario usuario);
    Usuario findByUserName(String userName);
    void update(Usuario usuario) throws Exception;
    
}
