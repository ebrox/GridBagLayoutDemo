/**
 * Programmers: Ed Broxson & Chase McCowan
 * Date: 02/20/2013
 * Purpose: Create and draw multiple particles for use in Chemistry Diffusion
 *          example.
 */
package layout;

import javax.swing.JOptionPane;
import processing.core.PApplet;
import processing.core.PVector;

public class Particles extends PApplet{
    
    int numParts = 40;
    Mover[] particles = new Mover[numParts];
    float topSpeed = 4f;
    int[] color1 = new int[3];
    int[] color2 = new int[3];
    boolean open = true;
    static boolean isWinner = false;
//    boolean oneTime = false;


    /** method to create size of sketch and particles */
    @Override
    public void setup() {
      size(775,200);
      
      particleFill(2, 2);
            
       
    }
    /** method to fill the start box with 20 of each element to be used in 
     * simulation */
    public void particleFill(int part1, int part2){
        
        float vel1 = 0;
        float dia1 = 0;
        float vel2 = 0;
        float dia2 = 0;
        float rate = 5;
        
        switch(part1){
            case 1:
                vel1 = rate;
                dia1 = 2;
                color1[0] = 0;
                color1[1] = 120;
                color1[2] = 200;
                break;
            case 2:
                vel1 = rate/sqrt(5);
                dia1 = 5;
                color1[0] = 30;
                color1[1] = 80;
                color1[2] = 230;
                break;
            case 3:
                vel1 = rate/sqrt(10);
                dia1 = 6;
                color1[0] = 130;
                color1[1] = 50;
                color1[2] = 150;
        }
        
        switch(part2){
            case 1:
                vel2 = rate;
                dia2 = 2;
                color2[0] = 150;
                color2[1] = 120;
                color2[2] = 130;
                break;
            case 2:
                vel2 = rate/sqrt(5);
                dia2 = 5;
                color2[0] = 110;
                color2[1] = 200;
                color2[2] = 20;
                break;
            case 3:
                vel2 = rate/sqrt(10);
                dia2 = 6;
                color2[0] = 80;
                color2[1] = 10;
                color2[2] = 130;
                break;
            case 4:
                vel2 = rate/sqrt(20);
                dia2 = 9;
                color2[0] = 250;
                color2[1] = 220;
                color2[2] = 100;
                break;
            case 5:
                vel2 = rate/sqrt(16);
                dia2 = 7;
                color2[0] = 120;
                color2[1] = 200;
                color2[2] = 60;
                break;
            case 6:
                vel2 = rate/sqrt(14);
                dia2 = 4;
                color2[0] = 50;
                color2[1] = 190;
                color2[2] = 100;
        }
        
        for (int i = 0; i < numParts; i++) {
          if (i % 2 == 0){
              particles[i] = new Mover(vel1, dia1, i, particles);
          }
          else{
              particles[i] = new Mover(vel2, dia2, i, particles);
          }
      }
        
    }

    /** method to draw particles, runs continously */
    @Override
    public void draw() {
      background(170);
      
      if (!open){
          line(200, 0, 200, height);
      }
      
      
      
      for (int i = 0; i < numParts; i++) {
        particles[i].update();
        particles[i].checkEdges();
        particles[i].display(i);  
      }
      
      
    }
    /** class to create particles of elements and provide methods to move them
    *   around the screen
    */
    public class Mover {

      PVector location;
      PVector velocity;
      float x, y;
      float diameter;
      float vx = 0;
      float vy = 0;
      int id;
      Mover[] others;

      Mover() {
        location = new PVector(random(0, 180),random(height));
        x = location.x;
        y = location.y;
        velocity = new PVector(random(-2,2),random(-2,2));
        diameter = 10;
      }
      
      Mover(float vin, float din, int idin, Mover[] oin){
        location = new PVector(random(0, 180),random(height));
        x = location.x;
        y = location.y;
        velocity = new PVector(vin, vin);
        diameter = din;
        id = idin;
        others = oin;
      }
      /** method to update the particle/mover information based on collisions */
      public void update() {
        
        location.add(velocity);
        
        for (int i = id + 1; i < numParts; i++) {
            float dx = others[i].x - x;
            float dy = others[i].y - y;
            float distance = sqrt(dx*dx + dy*dy);
            float minDist = others[i].diameter/2 + diameter/2;
            if (distance < minDist) { 
              float angle = atan2(dy, dx);
              float targetX = x + cos(angle) * minDist;
              float targetY = y + sin(angle) * minDist;
              float ax = (targetX - others[i].x);
              float ay = (targetY - others[i].y);
              velocity.x -= ax;
              velocity.y -= ay;
              others[i].velocity.x += ax;
              others[i].velocity.y += ay;
            }
          } 
        velocity.limit(topSpeed);
      }
      /** method to display the particles/movers */
      public void display(int ele) {
        stroke(0);
        fill(175);
        
        if (ele % 2 == 0){
//                fill(150, 102, 200);
                fill(color1[0], color1[1], color1[2]);
                ellipse(x, y, diameter, diameter);
            }
            else{
//                fill(255, 204);
                fill(color2[0], color2[1], color2[2]);
                ellipse(x, y, diameter, diameter);
            }
      }
      /** method to check if particle/mover is at the edge and reverse it's
       * direction */
      public void checkEdges() {
          
          x += velocity.x;
          y += velocity.y;
          
          if (!open && x + diameter/2 > 200) {
            x = 200 - diameter/2;
            velocity.x *= -1;
          }
          else if (!isWinner &&  (x + diameter/2) > width){
               
//              JOptionPane.showMessageDialog(null, "WINNER!");
              isWinner = true;
            
//            GridBagLayoutDemo gbl = new GridBagLayoutDemo();
//            gbl.showWinner();
          }
//          else if (open && x + diameter/2 > width){
//            x = width - diameter/2;
//            velocity.x *= -1;
//          }
          else if (x - diameter/2 < 0) {
            x = diameter/2;
            velocity.x *= -1;
          }
          if (y + diameter/2 > height) {
            y = height - diameter/2;
            velocity.y *= -1;
          } 
          else if (y - diameter/2 < 0) {
            y = diameter/2;
            velocity.y *= -1;
          }        
      }
    }
    
}
