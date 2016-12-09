/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fun;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Mads327b
 */
public class Fun extends Canvas implements Runnable{
    boolean running = false;
    Random r = new Random();
    public static void main(String[] args) {
        new Fun();
    }
    public JFrame f;
    private Thread t;
    private Color background = Color.BLACK;
    private Color star = Color.WHITE;
    ArrayList<Star> stars = new ArrayList<>();
    private double fps;
    double speed = 1000;
    double max = 10000;
    double minSize = 0;
    double shape = 5.0;
    
    ArrayList<String> keyList = new ArrayList<>();
    public Fun() {
        f = new JFrame();
        f.setSize(800,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.setVisible(true);
        for (int i = 0; i < 10000; i++) {
            double z = r.nextDouble()*max;
            double per = (300.0/(300.0+max));
            double x = r.nextDouble()*(800.00/per)-400/per,
                   y = r.nextDouble()*(600.00/per)-300/per;
            stars.add(new Star(
                    x, 
                    y, 
                    z,
                    star
            ));
            
        }
        
        start();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.translate(getWidth()/2, getHeight()/2);
        
        int drawnObject = 0;
        for (Star star : stars) {
            if(star.show(g, this))
                drawnObject++;
        }
        
        g.setColor(Color.GREEN);
        g.drawString("FPS: "+(int)Math.round(fps), -getWidth()/2+10, -getHeight()/2+20);
        g.drawString("Stars drawn: "+drawnObject, -getWidth()/2+10, -getHeight()/2+40);
        g.drawString("Speed: "+dicimal(speed,2), -getWidth()/2+10, -getHeight()/2+60);
        g.drawString("mouse location relativ to center", -getWidth()/2+130, -getHeight()/2+60);
        g.drawString("Minimum size: "+dicimal(minSize,2), -getWidth()/2+10, -getHeight()/2+80);
        g.drawString("use mouse wheel", -getWidth()/2+130, -getHeight()/2+80);
        g.drawString("Egdes on star: "+(int)dicimal(shape,0), -getWidth()/2+10, -getHeight()/2+100);
        g.drawString("use ctrl + mouse wheel", -getWidth()/2+130, -getHeight()/2+100);
        g.dispose();
        bs.show();
    }
    private void update(double time){
        for (Star star : stars) {
            star.update(time, speed, max);
        }
    }
    
    public static double dicimal(double value,int dic){
        return Double.valueOf(String.format("%."+dic+"f", value).replace(",", "."));
    }
    
    @Override
    public void run() {
        long last = System.nanoTime();
        while(running){
            long now = System.nanoTime();
            double time = (now-last)/1000000000.00;
            fps = 1000000000.00/(now-last);
            last = now;
            render();
            update(time);
        }
    }

    private void start() {
        running = true;
        t = new Thread(this, "main");
        addMouseMotionListener(new mL(this));
        addMouseWheelListener(new mL2(this));
        addKeyListener(new kL(this));
        this.requestFocus();
        t.start();
    }
    
}
