package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Ball.Ball;
import processing.core.PApplet;
import processing.core.PFont;

public class MainWindow extends PApplet {
	
	PFont font;
	ArrayList<Ball> balls = StaticRefs.balls;
	ArrayList<Ball> ballsTrash = StaticRefs.ballsTrash;
	@Override
	public void setup() {
		StaticRefs.parent = this;
		StaticRefs.initImages();
		size(1280,720);
		font = createFont("Arial", 16, true);
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
		balls.removeAll(ballsTrash);
		ballsTrash.removeAll(ballsTrash);
		textFont(font, 16);
		fill(255);
		text("Your points: "+StaticRefs.count, 0, 16);
	}
	Date lastAdd;
	private void addBall() {
		if (checkTime(lastAdd, 200)) {
			balls.add(new Ball());
			lastAdd = new Date();
			System.out.println(balls.size());
		}
	}
	
	Date lastMove;
	private void moveBalls() {
		if (checkTime(lastMove, 10 )) {
			for (Ball b: balls)
				b.fly();
			lastMove = new Date();
		}
	}
	
	private boolean checkTime(Date last, int ms) {
		return (new Date().getTime() - last.getTime() > ms);
	}
}
