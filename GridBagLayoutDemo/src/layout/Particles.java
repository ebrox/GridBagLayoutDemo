/**
 * Programmers: Ed Broxson & Chase McCowan
 * Date: 02/20/2013
 * Purpose: Create and draw multiple particles for use in Chemistry Diffusion
 *          example.
 */
package layout;

import processing.core.PApplet;
import processing.core.PVector;

public class Particles extends PApplet{
    
    int numParts = 40;
    Mover[] particles = new Mover[numParts];
    float vel1 = 1;
    float vel2 = 4;
    float topSpeed = 7f;
    

    /** method to create size of sketch and particles */
    @Override
    public void setup() {
      size(775,200);
            
      for (int i = 0; i < numParts; i++) {
          if (i % 2 == 0){
              particles[i] = new Mover(vel1, 10, i, particles);
          }
          else{
              particles[i] = new Mover(vel2, 20, i, particles);
          }
      } 
    }

    /** method to draw particles, runs continously */
    @Override
    public void draw() {
      background(170);
      line(200, 0, 200, height);
      
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
                fill(150, 102, 200);
                ellipse(x, y, diameter, diameter);
            }
            else{
                fill(255, 204);
                ellipse(x, y, diameter, diameter);
            }
      }
      /** method to check if particle/mover is at the edge and reverse it's
       * direction */
      public void checkEdges() {
          
          x += velocity.x;
          y += velocity.y;
          
          if (x + diameter/2 > 200) {
            x = 200 - diameter/2;
            velocity.x *= -1;
          }
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
