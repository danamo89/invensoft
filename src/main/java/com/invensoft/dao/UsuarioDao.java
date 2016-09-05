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
public interface UsuarioDao extends IGenericDao<Usuario, Integer>{
    
//    void save(Usuario ussuario);
    Usuario findByUserName(String userName);
    void update(Usuario usuario) throws Exception;
    
}
