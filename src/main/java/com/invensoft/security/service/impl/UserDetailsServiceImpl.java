/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.security.service.impl;

import com.invensoft.model.Usuario;
import com.invensoft.model.UsuarioRol;
import com.invensoft.security.dao.AuthenticationDao;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    @Autowired
    private AuthenticationDao authenticationDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Usuario usuario = null;
        HttpServletRequest request = getRequest();
        try {
            usuario = authenticationDao.findUserName(username);
        } catch (Exception ex) {
            Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        } else {
            HttpSession session = request.getSession();
            
            session.setAttribute("s_obj_persona", usuario.getPersona());
            session.setAttribute("s_id_persona", usuario.getPersona().getIdPersona());
            session.setAttribute("s_nombres", usuario.getPersona().getNombres());
            session.setAttribute("s_apellidos", usuario.getPersona().getApellidos());
            session.setAttribute("s_email", usuario.getPersona().getEmail());
            session.setAttribute("s_obj_usuario", usuario);
            
            return makeUser(usuario);
        }
    }

    private User makeUser(Usuario user) {
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, makeGrantedAuthorities(user));
    }

    private List<GrantedAuthority> makeGrantedAuthorities(Usuario user) {
        List<GrantedAuthority> result = new LinkedList<>();
        for (UsuarioRol usuarioRol : user.getUsuarioRolList()) {
            result.add(new GrantedAuthorityImpl(usuarioRol.getRol().getNombre()));
        }
        return result;
    }

    protected HttpServletRequest getRequest() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request;
    }
}
