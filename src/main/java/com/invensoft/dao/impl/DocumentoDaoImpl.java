/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.model.Documento;
import com.invensoft.model.Cargo;
import java.util.List;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.invensoft.dao.IDocumentoDao;

/**
 *
 * @author Nanchy_2
 */
@Scope("singleton")
@Component("documentoDao")
public class DocumentoDaoImpl extends GenericDao implements IDocumentoDao {

    @Override
    public List<Documento> findAll() {
        Query q = getEntityManager().createNamedQuery("Documento.findAll");
        return (List<Documento>) q.getResultList();
    }

    @Override
    public List<Documento> findDocumentosTipoRRHH() {
        Query q = getEntityManager().createNamedQuery("Documento.findDocumentosByTipo");
        q.setParameter("idDocumentoTipo", Documento.DOCUMENTO_RRHH);
        return (List<Documento>) q.getResultList();
    }

    @Override
    public List<Documento> findDocumentosTipoPersonales() {
        Query q = getEntityManager().createNamedQuery("Documento.findDocumentosByTipo");
        q.setParameter("idDocumentoTipo", Documento.DOCUMENTO_PERSONALES);
        return (List<Documento>) q.getResultList();
    }

    @Override
    public List<Documento> findDocumentosTipoFamiliares() {
        Query q = getEntityManager().createNamedQuery("Documento.findDocumentosByTipo");
        q.setParameter("idDocumentoTipo", Documento.DOCUMENTO_FAMILIARES);
        return (List<Documento>) q.getResultList();
    }
    
}
