/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author David
 */
public class Main {
    
    
    
    public static void main(String[] arg) {
        Carro carro = new Carro();
        List<Partes> partesList = new ArrayList<>();
        
        carro.setPartesList(partesList);
        
        partesList.add(new Partes("Motor"));
        
        for (Partes parte : carro.getPartesList()) {
            System.out.println("Parte: " + parte.getNombre());
        }
        
        
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(3);
        
        for (Integer integer : list) {
            System.out.println(integer);
        }
        
        System.out.println("Ordered");
        Collections.sort(list);
        
        for (Integer integer : list) {
            System.out.println(integer);
        }
        
    }
    
    
}
