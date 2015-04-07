package main;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;
import Ball.Ball;

public class StaticRefs {

	private StaticRefs() {}
	
	static public ArrayList<Ball> balls = new ArrayList<Ball>(200);
	static public ArrayList<Ball> ballsTrash = new ArrayList<Ball>(50);
	static public Integer count = 0;
	static public PApplet parent;
	public static PImage[] images;
	private static int imageCount = 1;
	public static void initImages() {
		images = new PImage[imageCount];
		for (int i = 0; i < imageCount; i ++)
			try {
				images[i] = parent.loadImage(images.getClass().getResource("/images/"
						+ i + ".png").toString());
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
	}
	public static PImage getRandomImage() {
		return images[(int) (Math.random()*imageCount)];
	}
}
