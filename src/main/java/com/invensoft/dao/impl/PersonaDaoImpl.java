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
    public List<Object[]> findPersonasBasicData() throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select legajo, nombres, apellidos, email, id_persona from personas order by apellidos, nombres");
        
        Query query = getEntityManager().createNativeQuery(sql.toString());
        return (List<Object[]>) query.getResultList();
    }
    
    @Override
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select cl.descripcion, t.cantidad ");
        sql.append("  from categorias_laborales cl ");
        sql.append("     , (select @rownum:=0) x");
        sql.append("     , ( ");
        sql.append("            select (@rownum:=@rownum+1) n");
        sql.append("                 , count(pe.id_persona) cantidad ");
        sql.append("                 , pe.id_categoria_laboral");
        sql.append("              from personas pe, (select @rownum:=0) x");
        sql.append("          group by pe.id_categoria_laboral");
        sql.append("          order by 2 desc");
        sql.append("       ) t");
        sql.append("  where cl.id_categoria_laboral = t.id_categoria_laboral");
        sql.append("    and t.n <= ").append(top);
        sql.append(" order by 2 desc, 1 asc ");
        
        Query query = getEntityManager().createNativeQuery(sql.toString());
        return (List<Object[]>) query.getResultList();
    }
    
    @Override
    public List<Object[]> findTopPersonasXLocalidad(Integer top) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select concat(p.nombre,' - ',l.nombre) nombre, t.cantidad ");
        sql.append("  from localidades l ");
        sql.append("     ,  provincias p ");
        sql.append("     , (select @rownum:=0) x");
        sql.append("     , ( ");
        sql.append("            select (@rownum:=@rownum+1) n");
        sql.append("                 , count(pe.id_persona) cantidad ");
        sql.append("                 , pe.id_localidad");
        sql.append("              from personas pe, (select @rownum:=0) x");
        sql.append("          group by pe.id_localidad");
        sql.append("          order by 2 desc");
        sql.append("       ) t");
        sql.append("  where l.id_localidad = t.id_localidad ");
        sql.append("    and l.id_provincia = p.id_provincia ");
        sql.append("    and t.n <= ").append(top);
        sql.append(" order by 2 desc, 1 asc ");
        
        Query query = getEntityManager().createNativeQuery(sql.toString());
        return (List<Object[]>) query.getResultList();
    }
}
