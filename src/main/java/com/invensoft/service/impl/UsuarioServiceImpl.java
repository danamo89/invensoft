/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.UsuarioDao;
import com.invensoft.model.Usuario;
import com.invensoft.service.IUsuarioService;
import com.invensoft.util.MessageBean;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dnavarro
 */
@Service(value = "usuarioService")
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public void save(Usuario usuario) {
        try {
            usuarioDao.save(usuario);
            messageBean.addInfo("Datos guardados!", "Se han guardado los datos de forma correcta.");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return usuarioDao.findAllOrderBy("apellidos");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return new LinkedList<>();
        }
    }
    
}
