/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.dao.IPersonaDao;
import com.invensoft.model.Persona;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Scope(value = "singleton")
@Component("personaDao") 
public class PersonaDaoImpl extends GenericDaoImpl<Persona, Integer> implements IPersonaDao {
    
    @Override
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CL.DESCRIPCION, T.CANTIDAD ");
        sql.append("  FROM CATEGORIAS_LABORALES CL ");
        sql.append("     , (SELECT @rownum:=0) X");
        sql.append("     , ( ");
        sql.append("            SELECT (@rownum:=@rownum+1) N");
        sql.append("                 , COUNT(PE.ID_PERSONA) CANTIDAD ");
        sql.append("                 , PE.ID_CATEGORIA_LABORAL");
        sql.append("              FROM PERSONAS PE, (SELECT @rownum:=0) X");
        sql.append("          GROUP BY PE.ID_CATEGORIA_LABORAL");
        sql.append("          ORDER BY 2 DESC");
        sql.append("       ) T");
        sql.append("  WHERE CL.ID_CATEGORIA_LABORAL = T.ID_CATEGORIA_LABORAL");
        sql.append("    AND T.N <= ").append(top);
        sql.append(" ORDER BY 2 DESC, 1 ASC ");
        
        Query query = getEntityManager().createNativeQuery(sql.toString());
        return (List<Object[]>) query.getResultList();
    }
    
    @Override
    public List<Object[]> findTopPersonasXLocalidad(Integer top) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CONCAT(P.NOMBRE,' - ',L.NOMBRE) NOMBRE, T.CANTIDAD ");
        sql.append("  FROM LOCALIDADES L ");
        sql.append("     ,  PROVINCIAS P ");
        sql.append("     , (SELECT @rownum:=0) X");
        sql.append("     , ( ");
        sql.append("            SELECT (@rownum:=@rownum+1) N");
        sql.append("                 , COUNT(PE.ID_PERSONA) CANTIDAD ");
        sql.append("                 , PE.ID_LOCALIDAD");
        sql.append("              FROM PERSONAS PE, (SELECT @rownum:=0) X");
        sql.append("          GROUP BY PE.ID_LOCALIDAD");
        sql.append("          ORDER BY 2 DESC");
        sql.append("       ) T");
        sql.append("  WHERE L.ID_LOCALIDAD = T.ID_LOCALIDAD ");
        sql.append("    AND L.ID_PROVINCIA = P.ID_PROVINCIA ");
        sql.append("    AND T.N <= ").append(top);
        sql.append(" ORDER BY 2 DESC, 1 ASC ");
        
        Query query = getEntityManager().createNativeQuery(sql.toString());
        return (List<Object[]>) query.getResultList();
    }
}
