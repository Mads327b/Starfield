/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fun;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Mads327b
 */
public class Star {
    double x,y,z;
    Color color;

    public Star(double x, double y, double z, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }
    public void update(double time, double speed, double max){
        z -= speed*time;
        double per = (300.0/(300.0+max));
        if(z <= 5){
            x = Math.random()*(800.00/per)-400/per;
            y = Math.random()*(600.00/per)-300/per;
            z = max;
        }else if(z >= max){
            z = 5;
        }
    }
    public boolean show(Graphics2D g, Fun fun){
        GeneralPath path = new GeneralPath();
        
        double per = (300.0/(300.0+z));
        double size = 10.0*per;
        double X = x*per,
               Y = y*per;
        if(X <= fun.getWidth()/2 && X >= -fun.getWidth()/2 && Y <= fun.getHeight()/2 && Y >= -fun.getHeight()/2 && size >= fun.minSize){
            for (double i = 0; i < 2*Math.PI; i+=Math.PI/(fun.shape/2)) {
                double x = Math.sin(i)*size+X;
                double y = Math.cos(i)*size+Y;
                if(i == 0)
                    path.moveTo(x, y);
                else
                    path.lineTo(x, y);
            }
            
            float
                    rC = (float) (color.getRed()/255.0),
                    gC = (float) (color.getGreen()/255.0),
                    bC = (float) (color.getBlue()/255.0),
                    aC = (float) (1*per/(fun.minSize+0.2));
            if(aC > 1f){
                aC = 1f;
            }
            Color col = new Color(rC, gC, bC, aC);
            g.setColor(col);
            g.fill(path);
            return true;
        }
        return false;
    }
}
