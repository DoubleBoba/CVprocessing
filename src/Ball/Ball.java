package Ball;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box.Filler;

import main.StaticRefs;
import processing.core.PApplet;
import processing.core.PImage;

public class Ball {
	private PApplet parent;
	private List< Ball> trash;
	private int x,y,radius, increment;
	Curve straid;
	private PImage img;
	public Ball() {
		this.parent = StaticRefs.parent;
		this.trash = StaticRefs.ballsTrash;
		img =parent.loadImage(getClass().getResource("/images/0.png").toString());

		y = parent.height + 30;
		x = (int) (Math.random()*(parent.width - 20)+20);
		radius = (int) (Math.random()*20+20);
		increment = (int) -(Math.random()*2+2);
		straid = new Curve(this, 5, 5, 5, increment);
		parent.addMouseListener(new ClickChecker());
		
	}
	
	public void draw() { 
		parent.fill(255);
		parent.stroke(128);
		parent.image(img, x, y, radius, radius);
	}
	
	public void fly() {
		y += increment;
		if (y <-5 )
			trash.add(this);
		parent.fill(192,64,0);
		straid.dangle();
		parent.fill(255);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRadius() {
		return radius;
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
