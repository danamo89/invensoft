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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;

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

    @PostConstruct
    public void postConstruct() {
        System.out.println("Executing Postconstruct");
        try {
            menuBinding = new Menu();

            Map<Integer, UISubmenu> mapaCategorias = new HashMap<>();
            Map<Integer, UIMenuItem> mapaMenu = new HashMap<>();

            if (FacesUtils.getSessionUser() != null) {
                for (UsuarioRol usuarioRol : FacesUtils.getSessionUser().getUsuarioRolList()) {
                    for (final RolMenu rolMenu : usuarioRol.getRol().getRolMenuList()) {

                        UISubmenu subMenu;
                        UIMenuItem subMenuItem;

                        if (!mapaCategorias.containsKey(rolMenu.getMenu().getCategoriaMenu().getIdCategoriaMenu())) {
                            // Creamos el SubMenu
                            subMenu = new UISubmenu();
                            subMenu.setLabel(rolMenu.getMenu().getCategoriaMenu().getNombre());
                            // Colocamos la categoria en el mapa para evitar crearla 2 veces
                            mapaCategorias.put(rolMenu.getMenu().getCategoriaMenu().getIdCategoriaMenu(), subMenu);
                        } else {
                            subMenu = mapaCategorias.get(rolMenu.getMenu().getCategoriaMenu().getIdCategoriaMenu());
                        }

                        if (!mapaMenu.containsKey(rolMenu.getMenu().getIdMenu())) {
                            // Creamos el menu
                            subMenuItem = new UIMenuItem();
                            subMenuItem.setValue(rolMenu.getMenu().getNombre());
                            subMenuItem.addActionListener(new ActionListener() {
                                @Override
                                public void processAction(ActionEvent event) throws AbortProcessingException {
                                    onRedirectTo(rolMenu.getMenu().getUrl());
                                }
                            });

                            subMenu.getChildren().add(subMenuItem);
                            // Colocamos el menu para no crearlo 2 veces
                            mapaMenu.put(rolMenu.getMenu().getIdMenu(), subMenuItem);
                        }
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
