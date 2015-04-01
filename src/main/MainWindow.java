package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import processing.core.PApplet;

public class MainWindow extends PApplet {
	
	List<Ball> balls = Collections.synchronizedList(new ArrayList<Ball>());
	List<Ball> ballsToRemove = Collections.synchronizedList(new ArrayList<Ball>());
	PApplet me = this;
	@Override
	public void setup() {
		size(1280,720);
		lastAdd = new Date();
		lastMove = new Date();
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		background(192, 64, 0);
		for (Ball b: balls) {
			b.draw();
		}
		addBall();
		moveBalls();
		balls.removeAll(ballsToRemove);
		ballsToRemove.removeAll(ballsToRemove);
	}
	Date lastAdd;
	private void addBall() {
		if (checkTime(lastAdd, 1)) {
			balls.add(new Ball(this, balls, ballsToRemove));
			System.out.println(balls.size());
			lastAdd = new Date();
		}
	}
	
	Date lastMove;
	private void moveBalls() {
		if (checkTime(lastMove, 1 )) {
			for (Ball b: balls)
				b.fly();
			lastMove = new Date();
		}
	}
	
	private boolean checkTime(Date last, int ms) {
		return (new Date().getTime() - last.getTime() > ms);
	}
}
