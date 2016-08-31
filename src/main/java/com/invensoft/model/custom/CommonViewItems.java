/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.spacer.Spacer;

/**
 *
 * @author David
 */
public class CommonViewItems {
    
    public static OutputLabel getBr() {
        OutputLabel space = new OutputLabel();
        space.setEscape(false);
        space.setValue("<br/>");
        
        return space;
    }
    
    public static Spacer getSpacer(Integer spaceInPixels) {
        Spacer space = new Spacer();
        space.setWidth(spaceInPixels.toString());
        return space;
    }
    
}
