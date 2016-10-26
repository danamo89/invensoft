/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author dnavarro
 */
public class Cleaner extends HttpServlet {

    public void init() {
         (new Timer()).schedule(new Clean(), new Date(), (5*60*1000));
    }
}


class Clean extends TimerTask {
    @Override
    public void run() {
        try {
            Runtime runtime = Runtime.getRuntime();
            double memoria=(double)runtime.freeMemory()/(double)runtime.totalMemory();
            if (memoria <= 20) {
                System.out.println(new Date().toString() + " - Llamando al GC");
                System.gc();
            }
        } catch (Exception e) {
            System.out.println("No se pudo liberar memoria de forma manual. " + e.getLocalizedMessage());
        }
    }
}
