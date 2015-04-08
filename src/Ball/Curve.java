package Ball;

import javax.swing.text.DefaultEditorKit.CutAction;

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
		for (int i = 0,
				sp = 0; sp < splines; i+=step, sp++) {
			
			this.splines[sp] = new Spline(getX(), i, amplitude, i+step, speed);
			amplitude = -amplitude;
			
		}
	}
	public void dangle() {
		parent.beginShape();
		parent.curveVertex(getX(), getY()-3);
		parent.curveVertex(getX(), getY()-3);
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
	private int getX() {
		// TODO Auto-generated method stub
		return myBall.getX() + myBall.getRadius()/2;
	}
	Curve c = this;
	private class Spline {
		private int x,yShift, shift,  speed, increment;
		public Spline(int x,int yShift, int shift, int maxY,
				int speed) {
			this.x = x;
			this.yShift = yShift;
			this.shift = shift;
			this.increment = 0;
			this.speed = speed * (shift/Math.abs(shift));
		}
		public void move() {
			increment+=speed;
			if ( Math.abs(increment) >= Math.abs(shift) ) {
				speed = -(speed);
			}
		}
		public int getX() {
			return x + increment;
		}
		public int getY() {
			return c.getY() + yShift;
		}
	} //End of Spline
}
