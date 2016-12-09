/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fun;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Mads327b
 */
public class mL implements MouseMotionListener {
    Fun fun;
    public mL(Fun fun) {
        this.fun = fun;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        p.x = p.x-fun.getWidth()/2;
        p.y = p.y-fun.getHeight()/2;
        double speed = Math.sqrt(p.x*p.x+p.y*p.y)*20;
        if(p.x+p.y < 0)
            fun.speed = -speed;
        else
            fun.speed = speed;
    }
    
}
