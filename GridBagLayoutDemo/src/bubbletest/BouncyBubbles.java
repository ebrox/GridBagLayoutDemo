/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbletest;

import processing.core.PApplet;
/**
 *
 * @author Ed
 */
public class BouncyBubbles extends PApplet{
    
    /**
 * Bouncy Bubbles  
 * based on code from Keith Peters. 
 * 
 * Multiple-object collision.
 */
 
 // look at Ch. 5 and 6 of Processing - also Vectors (Ch. 1)
    
    int numBalls = 40;
    float spring = 0.25f; // original 0.05f
    float gravity = 0.00f; 
    float friction = -0.89f; // original -0.09f    
    Ball[] balls = new Ball[numBalls];

    @Override 
    public void setup() 
    {
      size(640, 360);
//      noStroke();
      for (int i = 0; i < numBalls; i++) {
          if (i % 2 == 0){
              balls[i] = new Ball(random(width), random(height), 30, i, balls);
          }
          else{
              balls[i] = new Ball(random(width), random(height), 50, i, balls);
          }
        
      }
    }

    @Override 
    public void draw() 
    {
//      background(0);
      background(170);
      line(0, 0, width, height);
      for (int i = 0; i < numBalls; i++) {
        balls[i].collide();
        balls[i].move();
        balls[i].display(i);  
      }
    }

    class Ball {
        
        float x, y;
        float diameter;
        float vx = 0;
        float vy = 0;
        int id;
        Ball[] others;

        Ball(float xin, float yin, float din, int idin, Ball[] oin) {
          x = xin;
          y = yin;
          diameter = din;
          id = idin;
          others = oin;
        } 

        void collide() {
          for (int i = id + 1; i < numBalls; i++) {
            float dx = others[i].x - x;
            float dy = others[i].y - y;
            float distance = sqrt(dx*dx + dy*dy);
            float minDist = others[i].diameter/2 + diameter/2;
            if (distance < minDist) { 
              float angle = atan2(dy, dx);
              float targetX = x + cos(angle) * minDist;
              float targetY = y + sin(angle) * minDist;
              float ax = (targetX - others[i].x) * spring;
              float ay = (targetY - others[i].y) * spring;
              vx -= ax;
              vy -= ay;
              others[i].vx += ax;
              others[i].vy += ay;
            }
          }   
        }

        void move() {
          vy += gravity;
          x += vx;
          y += vy;
          if (x + diameter/2 > width) {
            x = width - diameter/2;
            vx *= friction - .095;  // original without - .095
          }
          else if (x - diameter/2 < 0) {
            x = diameter/2;
            vx *= friction - .095;
          }
          if (y + diameter/2 > height) {
            y = height - diameter/2;
            vy *= friction - .095; 
          } 
          else if (y - diameter/2 < 0) {
            y = diameter/2;
            vy *= friction - .095;
          }
        }

        void display(int ele) {
//          fill(255, 204);
            if (ele % 2 == 0){
                fill(150, 102, 200);
                ellipse(x, y, diameter, diameter);
            }
            else{
                fill(255, 204);
                ellipse(x, y, diameter, diameter);
            }
          
        }
    }
    
}
