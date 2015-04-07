package main;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import processing.core.PApplet;

public class Ball {
	PApplet parent;
	List< Ball> balls, trash;
	int x,y,radius, increment;
	public Ball(PApplet parent) {
		this.parent = parent;
		this.balls = StaticRefs.balls;
		this.trash = StaticRefs.ballsTrash;
		y = parent.height + 30;
		x = (int) (Math.random()*(parent.width - 20)+20);
		radius = (int) (Math.random()*20+20);
		parent.addMouseListener(new ClickChecker());
		increment = (int) -(Math.random()*2+2);
	}
	
	public void draw() { 
		parent.fill(255);
		parent.stroke(128);
		parent.ellipse(x, y,radius, radius);
	}
	
	public void fly() {
		y += increment;
		if (y <-5 )
			trash.add(this);
	}
	Ball me = this;
	private class ClickChecker extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int mx = e.getX(), my = e.getY();
			
			if (pointDimens(new Point(x,y), new Point(mx,my)) <= radius) {
				parent.removeMouseListener(this);
				trash.add(me);
				StaticRefs.count+=1;
			}
			
		}
	}
	private double sq(double x){
		return x*x;
	}
	private int pointDimens(Point p1, Point p2) {
		return (int) (Math.sqrt(sq(Math.max(p1.x, p2.x)- Math.min(p1.x, p2.x)) 
					 		    +
					 		    sq(Math.max(p1.y, p2.y)- Math.min(p1.y, p2.y))));
			
	}
}
