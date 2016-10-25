/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.security.controller;

import com.invensoft.controller.MenuController;
import com.invensoft.model.Usuario;
import com.invensoft.util.FacesUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author David
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    
    public String loginUsingSpringAuthenticationManager() {
        UserDataForAuthentication userDataForAuthentication = (UserDataForAuthentication) FacesUtils.getBackingBean("userDataForAuthentication");
        //authentication manager located in  Spring config: /WEB-INF/authenticationContext-security.xml
        AuthenticationManager authenticationManager = (AuthenticationManager) getSpringBean("authenticationManager");
        //simple token holder
        Authentication authenticationRequestToken = createAuthenticationToken(userDataForAuthentication);
        //authentication action
        try {
            HttpServletRequest request = getRequest();
            
            Authentication authenticationResponseToken = authenticationManager.authenticate(authenticationRequestToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationResponseToken);
            //ok, test if authenticated, if yes reroute
            if (authenticationResponseToken.isAuthenticated()) {
//                Usuario usuario = (Usuario)request.getSession().getAttribute("s_obj_usuario");
                
                ((MenuController) FacesUtils.getBackingBean("menuController")).loadMenu();

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/InvenSoft/pages/secure/index.xhtml");
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }
        } catch (BadCredentialsException badCredentialsException) {
            FacesMessage facesMessage = new FacesMessage("Inicio de sesión fallido", "Por favor verificar usuario/contraseña e intente de nuevo.");
            FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        } catch (LockedException lockedException) {
            FacesMessage facesMessage = new FacesMessage("Cuenta bloqueada", "Por favor comuníquese con soporte.");
            FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        } catch (DisabledException disabledException) {
            FacesMessage facesMessage = new FacesMessage("Cuenta deshabilitada", "Por favor comuníquese con soporte.");
            FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        }

        return null;
    }
    
    private Authentication createAuthenticationToken(UserDataForAuthentication userDataForAuthentication) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDataForAuthentication.getUserName(),
                        userDataForAuthentication.getPassword()
                );
        return usernamePasswordAuthenticationToken;
    }


    private Object getSpringBean(String name){
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(
                (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
        return ctx.getBean(name);
    }

    protected HttpServletRequest getRequest() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request;
    }

}
