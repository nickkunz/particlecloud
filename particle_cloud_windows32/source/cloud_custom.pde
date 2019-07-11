// title: custom cloud
// author: jeffrey traer bernstein
// modified: nick kunz
// date: july 11, 2019
// description: an experiment modifying the attraction behavoir of 
//              the cloud example found in the traer physics library

// load library
import traer.physics.*;

// define variables
Particle mouse;
Particle[] particles;
ParticleSystem physics;
PImage img;

void setup()
{
  size(920, 720); // app window size
  frameRate(60);  // fast frame rate
  noCursor();     // remove cursor from app window

  tint(0, 0, 0);                // cloud color
  img = loadImage("fade.png");  // particle fade
  
  particles = new Particle[1000];        // number of particles
  physics = new ParticleSystem(0, 0.1);  // particle behavior
  mouse = physics.makeParticle();        // cursor control
  mouse.makeFixed();                     // cursor control

  // particle attraction
  for (int i = 0; i < particles.length; i++) {
    particles[i] = physics.makeParticle(2, random(0, width), random(0, height), 0.05);
    physics.makeAttraction( mouse, particles[i], 7500, 50);
  } 
}

void draw()
{
  
  physics.tick();
  background(255, 255, 255);                 // background color
  mouse.position().set( mouseX, mouseY, 0);  // cursor control
  
  // initialize particle positions
  for (int i = 0; i < particles.length; i++) {
    Particle p = particles[i];
    image(img, p.position().x()-img.width/2, p.position().y()-img.height/2);
  }
}
