/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.util;

/**
 *
 * @author David
 */
public class Test {
    
    public enum Orientacion {
        VERTICAL, HORIZONTAL
    }
    
    public static void main(String args[]) {
        System.out.println("Ordinal de la posicion = " + Orientacion.VERTICAL.ordinal());
    }
    
}
