/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fun;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Mads327b
 */
public class kL implements KeyListener {
    Fun fun;

    public kL(Fun fun) {
        this.fun = fun;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!fun.keyList.contains(String.valueOf(e.getKeyCode()))){
            fun.keyList.add(String.valueOf(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        fun.keyList.remove(String.valueOf(e.getKeyCode()));
    }
    
    
}
