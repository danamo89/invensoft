/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao.impl;

import com.invensoft.model.Documento;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.invensoft.dao.IDocumentoDao;

/**
 *
 * @author Nanchy_2
 */
@Scope("singleton")
@Component("documentoDao")
public class DocumentoDaoImpl extends GenericDaoImpl<Documento, Integer> implements IDocumentoDao {
    
}
