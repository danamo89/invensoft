package com.invensoft.util;

import com.invensoft.model.Usuario;
import com.invensoft.model.UsuarioRol;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtils {

    public static Object getBackingBean(String beanName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, convertToElSyntax(beanName), Object.class);
        return valueExpression.getValue(elContext);
    }
    
    public static Usuario getSessionUser() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        Usuario usuario = null;
        try {
            usuario = (Usuario)request.getSession().getAttribute("s_obj_usuario");
        } catch (Exception e) {
            //ignored
        }
        return usuario;
    }

    private static String convertToElSyntax(String value) {
        return new StringBuffer("#{").append(value).append("}").toString();
    }
    
    public static boolean sessionUserIsReadOnly() {
        boolean isReadOnly = false;
        
        try {
            for (UsuarioRol usuarioRol : getSessionUser().getUsuarioRolList()) {
                if (usuarioRol.getRol().getReadOnly().equals("1")) {
                    isReadOnly = true;
                    break;
                }
            }
        } catch (Exception e) {
            isReadOnly = false;
        }
        
        return isReadOnly;
    }
    
    public static boolean sessionUserCantViewCuestionario() {
        boolean cantViewCuestionario = false;
        
        try {
            for (UsuarioRol usuarioRol : getSessionUser().getUsuarioRolList()) {
                if (usuarioRol.getRol().getViewCuestionario().equals("0")) {
                    cantViewCuestionario = true;
                    break;
                }
            }
        } catch (Exception e) {
            cantViewCuestionario = false;
        }
        
        return cantViewCuestionario;
    }
    
    public static boolean sessionUserCanDelete() {
        boolean cantViewCuestionario = false;
        
        try {
            for (UsuarioRol usuarioRol : getSessionUser().getUsuarioRolList()) {
                if (usuarioRol.getRol().getCanDelete().equals("1")) {
                    cantViewCuestionario = true;
                    break;
                }
            }
        } catch (Exception e) {
            cantViewCuestionario = false;
        }
        
        return cantViewCuestionario;
    }
    
    public static ValueExpression createValueExpression(String expression, Class<?> expectedType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), expression, expectedType);
    }
}
