import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import traer.physics.*; 

import traer.physics.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class particle_cloud extends PApplet {

// title: custom traer physics cloud
// author: jeffrey traer bernstein
// modified: nick kunz
// date: july 11, 2019
// description: a quick experiment modifying the attraction behavoir of the
//              cloud example found in the traer physics library for processing

// load library


// define variables 
Particle mouse;
Particle[] particles;
ParticleSystem physics;
PImage img;

public void setup()
{
   // window size
  frameRate(60);  // fast frame rate
  noCursor();     // remove cursor from window

  tint(0, 0, 0);                // cloud color (black)
  img = loadImage("fade.png");  // particle fade
  
  particles = new Particle[1000];        // number of particles
  physics = new ParticleSystem(0, 0.1f);  // particle behavior
  mouse = physics.makeParticle();        // cursor control
  mouse.makeFixed();                     // cursor control

  // particle attraction
  for (int i = 0; i < particles.length; i++) {
    particles[i] = physics.makeParticle(2, random(0, width), random(0, height), 0.05f);
    physics.makeAttraction( mouse, particles[i], 7500, 50);
  } 
}

public void draw()
{
  
  physics.tick();
  background(255, 255, 255);                 // background color (white)
  mouse.position().set( mouseX, mouseY, 0);  // cursor control
  
  // initialize particle positions
  for (int i = 0; i < particles.length; i++) {
    Particle p = particles[i];
    image(img, p.position().x()-img.width/2, p.position().y()-img.height/2);
  }
}
  public void settings() {  size(920, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "particle_cloud" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
