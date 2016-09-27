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
        return (Usuario)request.getSession().getAttribute("s_obj_usuario");
    }

    private static String convertToElSyntax(String value) {
        return new StringBuffer("#{").append(value).append("}").toString();
    }
    
    public static boolean sessionUserIsReadOnly() {
        boolean isReadOnly = false;
        
        for (UsuarioRol usuarioRol : getSessionUser().getUsuarioRolList()) {
            if (usuarioRol.getRol().getNombre().equals("ROLE_READ_ONLY")) {
                isReadOnly = true;
                break;
            }
        }
        
        return isReadOnly;
    }
    
    public static ValueExpression createValueExpression(String expression, Class<?> expectedType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), expression, expectedType);
    }
}
