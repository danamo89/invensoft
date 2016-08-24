/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.security.service;

/**
 *
 * @author David
 */
public interface AuthenticationService {
    
    boolean login(String username, String password);
    void logout();
    
}
