/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.security.service.impl;

import com.invensoft.security.service.AuthenticationService;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return true;
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void logout() {
        System.out.println("AuthenticationServiceImpl => logout ");
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
