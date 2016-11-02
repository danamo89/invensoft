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
    public List<Object[]> findPersonasReportData() throws Exception {
        StringBuilder sql = new StringBuilder();
        
        sql.append(" select p.legajo ");
        sql.append("\n    , p.nombres ");
        sql.append("\n    , p.apellidos ");
	sql.append("\n    , p.cuil ");
        sql.append("\n    , ti.DESCRIPCION ");
        sql.append("\n    , p.NUMERO_IDENTIFICACION ");
        sql.append("\n    , date_format(p.FECHA_NACIMIENTO, '%d/%m/%Y') ");
        sql.append("\n    , p.LUGAR_NACIMIENTO ");
        sql.append("\n    , p.DOMICILIO ");
        sql.append("\n    , p.NUMERO_DOMICILIO ");
        sql.append("\n    , p.PISO ");
        sql.append(" , p.DEPARTAMENTO ");
        sql.append(" , lo.NOMBRE ");
        sql.append(" , p.CODIGO_POSTAL ");
        sql.append(" , p.EMAIL ");
        sql.append(" , p.TELEFONO_PARTICULAR ");
        sql.append(" , p.CELULAR ");
        sql.append(" , ec.DESCRIPCION ");
        sql.append(" , pa.NOMBRE ");
        sql.append(" , pu.descripcion ");
        sql.append(" , ca.descripcion ");
        sql.append(" , gr.descripcion ");
        sql.append(" , p.HORARIO ");
        sql.append(" , p.LUGAR_DE_TRABAJO ");
        sql.append(" , s.NOMBRE ");
        sql.append(" , p.JEFE_INMEDIATO ");
        sql.append(" , p.LINEA ");
        sql.append(" , p.fecha_ingreso ");
        sql.append(" , p.ANTIGUEDAD ");
        sql.append(" , p.OBRA_SOCIAL ");
        sql.append(" , p.BANCO ");
        
        sql.append(" , case p.TIENE_CREDENCIAL_ART when 'S' THEN 'SI' ");
        sql.append("      when 'N' THEN 'NO' ");
        sql.append("      else 'N/A' ");
	sql.append("   end ");
        sql.append(" , case p.TIENE_CARNET_CONDUCTOR  when 'S' THEN 'SI' ");
        sql.append("      when 'N' THEN 'NO' ");
        sql.append("      else 'N/A' ");
	sql.append("   end ");
        
        sql.append(" , p.CARNET_CONDUCTOR_DESDE ");
        sql.append(" , p.CARNET_CONDUCTOR_HASTA ");
        sql.append(" , p.BUZO ");
        sql.append(" , p.PANTALON ");
        sql.append(" , p.BOTINES ");
        sql.append(" , p.CAMPERA ");
        sql.append(" , p.EQ_LLUVIA ");
        sql.append(" , p.CAMISA ");
        sql.append(" from personas p ");
	sql.append(" , sectores s ");
        sql.append(" , tipos_identificacion ti ");
        sql.append(" , localidades lo ");
        sql.append(" , categorias_laborales ca ");
        sql.append(" , estados_civiles ec ");
        sql.append(" , paises pa ");
        sql.append(" , puestos pu ");
        sql.append(" , gremios gr ");
        sql.append(" where p.id_sector = s.id_sector ");
	sql.append(" and p.ID_TIPO_IDENTIFICACION = ti.ID_TIPO_IDENTIFICACION ");
        sql.append(" and p.ID_LOCALIDAD = lo.ID_LOCALIDAD ");
        sql.append(" and p.ID_CATEGORIA_LABORAL = ca.id_categoria_laboral ");
        sql.append(" and p.ID_ESTADO_CIVIL = ec.ID_ESTADO_CIVIL ");
        sql.append(" and p.ID_PAIS_ORIGEN = pa.ID_PAIS ");
        sql.append(" and p.ID_PUESTO = pu.id_puesto ");
        sql.append(" and p.ID_GREMIO = gr.id_gremio; ");
        
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
