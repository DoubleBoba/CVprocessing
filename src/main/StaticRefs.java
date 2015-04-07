package main;

import java.util.ArrayList;

public class StaticRefs {

	private StaticRefs() {}
	
	static public ArrayList<Ball> balls = new ArrayList<Ball>(200);
	static public ArrayList<Ball> ballsTrash = new ArrayList<Ball>(50);
	static public Integer count = 0;
}
