/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.Rol;
import com.invensoft.model.Usuario;
import com.invensoft.model.UsuarioRol;
import com.invensoft.service.IRolService;
import com.invensoft.service.IUsuarioService;
import com.invensoft.util.FacesUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author David
 */
@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuariosList;
    private List<Rol> rolesList;
    
    private boolean readOnly;
    private boolean cantViewCuestionario;
    private boolean showUsuariosTable;
    
    @ManagedProperty(value = "#{rolService}")
    private IRolService rolService;
    @ManagedProperty(value = "#{usuarioService}")
    private IUsuarioService usuarioService;
    
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }
    
    @PostConstruct
    public void postConstruct() {
        showUsuariosTable = true;
        readOnly = FacesUtils.sessionUserIsReadOnly();
        cantViewCuestionario = FacesUtils.sessionUserCantViewCuestionario();
        
        this.loadLists();
    }
    
    private void loadLists() {
        if (usuariosList == null) {
            usuariosList = usuarioService.findAll();
        }
        
        if (rolesList == null) {
            rolesList = rolService.findAll();
        }
    }
    
    public void onCreateUsuario() {
        this.usuario = new Usuario();
        showUsuariosTable = false;
    }
    
    public void onViewUsuarioDetailedInfo(Usuario usuario) {
        this.usuario = usuario;
        
        for (Rol rol : rolesList) {
            rol.setSelected(false);
            for (UsuarioRol usuarioRol : usuario.getUsuarioRolList()) {
                if (rol.getIdRol().equals(usuarioRol.getRol().getIdRol())) {
                    rol.setSelected(true);
                    break;
                }
            }
        }
        
        showUsuariosTable = false;
    }
    
    public void onSave() {
        //Actualizamos los roles en el usuario
        List<UsuarioRol> rolesNuevos = new LinkedList<>();
        for (Rol rol : rolesList) {
            if (rol.isSelected()) {
                //Verificamos si ya tenia el rol
                UsuarioRol newUsuarioRol = new UsuarioRol(rol, usuario);
                
                if (usuario.getUsuarioRolList() != null) {
                    for (UsuarioRol usuarioRol : usuario.getUsuarioRolList()) {
                        if (rol.getIdRol().equals(usuarioRol.getRol().getIdRol())) {
                            newUsuarioRol.setIdUsuarioRol(usuarioRol.getIdUsuarioRol());
                        }
                    }
                }
                rolesNuevos.add(newUsuarioRol);
            }
        }
        
        usuario.setUsuarioRolList(rolesNuevos);
        
        usuarioService.save(usuario);
        
        usuariosList.clear();
        usuariosList.addAll(usuarioService.findAll());
        showUsuariosTable = true;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isCantViewCuestionario() {
        return cantViewCuestionario;
    }

    public void setCantViewCuestionario(boolean cantViewCuestionario) {
        this.cantViewCuestionario = cantViewCuestionario;
    }

    public boolean isShowUsuariosTable() {
        return showUsuariosTable;
    }

    public void setShowUsuariosTable(boolean showUsuariosTable) {
        this.showUsuariosTable = showUsuariosTable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Rol> rolesList) {
        this.rolesList = rolesList;
    }

    public IRolService getRolService() {
        return rolService;
    }

    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //</editor-fold>
    
}
