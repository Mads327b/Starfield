/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fun;

import com.sun.glass.events.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author Mads327b
 */
public class mL2 implements MouseWheelListener {
    Fun fun;
    public mL2(Fun fun) {
        this.fun = fun;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getPreciseWheelRotation() == 1){
            if(fun.keyList.contains(String.valueOf(KeyEvent.VK_CONTROL))){
                fun.shape -= 1;
                if(fun.shape < 3)
                    fun.shape = 3.0;
            }else{
                fun.minSize -= 0.01;
                if(fun.minSize < 0)
                    fun.minSize = 0;
            }
        }else if(e.getPreciseWheelRotation() == -1){
            if(fun.keyList.contains(String.valueOf(KeyEvent.VK_CONTROL)))
                fun.shape += 1;
            else
                fun.minSize += 0.01;
        }
    }
    
}
