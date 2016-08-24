package com.invensoft.dao.impl;

import com.invensoft.dao.impl.GenericDao;
import com.invensoft.model.Usuario;
import com.invensoft.dao.UsuarioDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
@Scope("singleton")
@Component("usuarioDao")
public class UsuarioDaoImpl extends GenericDao implements UsuarioDao {

    @Override
    @Transactional
    public synchronized boolean save(Usuario usuario) {
        boolean procesoOk = false;
        try {
            getEntityManager().merge(usuario);
            procesoOk = true;
        } catch (Exception ex) {
//            Logger.getLogger(this.getClass().toString()).error("Error al guardar información del usuario ", ex);
        }
        return procesoOk;
    }

    @Override
    public Usuario findByUserName(String userName) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsername");
        query.setParameter("username", userName);
        
        try {
            return (Usuario) query.getSingleResult();
        } catch (Exception ex) {
//            Logger.getLogger(this.getClass()).error("Error al consultar ", ex);
            return null;
        }
    }

    @Override
    @Transactional
    public synchronized void update(Usuario usuario) throws Exception{
        Query consulta = getEntityManager().createNativeQuery("UPDATE usuarios SET clave = ? WHERE username = ?");
        consulta.setParameter(1, usuario.getPassword());
        consulta.setParameter(2, usuario.getUsername());

        consulta.executeUpdate();
    }
}