package Ball;

import main.StaticRefs;
import processing.core.PApplet;
/**
 * Я В РОТ СУКА НАХУЙ БЛЯТЬ ЕБАЛ ЭТОТ ПИЗДЕЦ!
 * @author tamtaradm
 *
 */
class Curve {
	private int step;
	private Ball myBall;
	private Spline splines[];
	private PApplet parent;
	public Curve(Ball myBall, int amplitude, 
			int step, int splines, int speed) {
		parent = StaticRefs.parent;
		this.step = step;
		this.myBall = myBall;
		this.splines = new Spline[splines];
		boolean negative = false;
		for (int i = getY(),
				sp = 0; sp < splines; i+=step, sp++) {
			
			this.splines[sp] = new Spline(getY(), i, amplitude, i+step, speed);
			amplitude = -amplitude;
			
		}
	}
	public void dangle() {
		parent.beginShape();
		parent.curveVertex(myBall.getX(), getY());
		parent.curveVertex(myBall.getX(), getY());
		for (int sp = 0; sp < splines.length; sp++) {
			splines[sp].move();
			parent.curveVertex(splines[sp].getX(), splines[sp].getY());
		}
		parent.curveVertex(myBall.getX(), getY()+step * (splines.length+1));
		
		parent.endShape();
	}
	private int getY() {
		return myBall.getY() + step + myBall.getRadius();
	}
	Curve c = this;
	private class Spline {
		private int x,y, shift, maxY, curY, speed;
		public Spline(int x,int y, int shift, int maxY,
				int speed) {
			this.x = x;
			this.y = y;
			this.curY = y;
			this.shift = shift;
			this.maxY = maxY;
			this.speed = speed;
		}
		public void move() {
			curY-=speed;
			if (curY>maxY){
				y -=step;
				curY = y;
				shift = -(shift);
			}	
		}
		public int getX() {
			return x + shift;
		}
		public int getY() {
			return curY;
		}
	} //End of Spline
}
