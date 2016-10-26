/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.RolMenu;
import com.invensoft.model.UsuarioRol;
import com.invensoft.util.FacesUtils;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menuitem.UIMenuItem;

/**
 *
 * @author David
 */
@Named(value = "menuController")
@ViewScoped
public class MenuController implements Serializable {

    private Menu menuBinding;

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {

    }

    public void loadMenu() {
        try {
            if (menuBinding == null) {
                menuBinding = new Menu();
                List<com.invensoft.model.Menu> listMenus = new LinkedList<>();
                Map<Integer, com.invensoft.model.Menu> mapaMenu = new HashMap<>();

                if (FacesUtils.getSessionUser() != null) {
                    //Cargamos todos los menues disponibles para el usuario
                    for (UsuarioRol usuarioRol : FacesUtils.getSessionUser().getUsuarioRolList()) {
                        for (final RolMenu rolMenu : usuarioRol.getRol().getRolMenuList()) {
                            mapaMenu.put(rolMenu.getMenu().getIdMenu(), rolMenu.getMenu());
                        }
                    }

                    //Ordenamos las opciones del menu
                    listMenus.addAll(mapaMenu.values());
                    Collections.sort(listMenus);

                    //Agregamos las opciones al menu principal
                    for (final com.invensoft.model.Menu menu : listMenus) {
                        UIMenuItem subMenuItem = new UIMenuItem();
                        subMenuItem.setValue(menu.getNombre());
                        subMenuItem.addActionListener(new ActionListener() {
                            @Override
                            public void processAction(ActionEvent event) throws AbortProcessingException {
                                onRedirectTo(menu.getUrl());
                            }
                        });

                        menuBinding.getChildren().add(subMenuItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRedirectTo(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/InvenSoft/pages/secure/" + url + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public Menu getMenuBinding() {
        return menuBinding;
    }

    public void setMenuBinding(Menu menuBinding) {
        this.menuBinding = menuBinding;
    }
    //</editor-fold>
}
