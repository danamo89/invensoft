/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.invensoft.security.dao.impl;


import com.invensoft.model.Usuario;
import com.invensoft.security.dao.AuthenticationDao;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrador
 */
@Scope("singleton")
@Component("authenticationDao")
public class AuthenticationDaoImpl implements AuthenticationDao, Serializable {

    @PersistenceContext(unitName = "invensoftPU")
    private EntityManager em;

    protected EntityManager getEntityManager(){
        return em;
    }

    @Override
    public Usuario findUserName(String username) throws Exception {
        Query q = getEntityManager().createNamedQuery("Usuario.findByUsername");
        q.setParameter("username",username);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
