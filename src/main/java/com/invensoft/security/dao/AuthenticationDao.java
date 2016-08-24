/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.invensoft.security.dao;

import com.invensoft.model.Usuario;

/**
 *
 * @author Administrador
 */
public interface AuthenticationDao {

    Usuario findUserName(String username)throws Exception;

}
