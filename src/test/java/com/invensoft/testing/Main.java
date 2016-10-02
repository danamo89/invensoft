/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.testing;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author David
 */
public class Main {
    
    
    
    public static void main(String[] arg) {
        
        List<Carro> list = new LinkedList<>();
        list.add(new Carro(3));
        list.add(new Carro(1));
        list.add(new Carro(4));
        list.add(new Carro(2));
        
        for (Carro carro : list) {
            System.out.println(carro.getOrden());
        }
        
        System.out.println("Ordered");
        Collections.sort(list);
        
        for (Carro carro : list) {
            System.out.println(carro.getOrden());
        }
        
    }
    
    
}
