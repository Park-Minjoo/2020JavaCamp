package jaculator;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.Vector;

public class Option {
	public String shape;
	
	Shape shapeobj;
	Color col_pen; // color
	Integer stro; //thick 
	boolean filling; // fill
	Point ptr1, ptr2;
	Vector<Point> ptrP = new Vector <Point>();
	Vector<Point> eraO = new Vector <Point>();
	boolean selected;
	
	public  Option() {
		filling=false;
		selected=false;
		shape = "SELECTOR";
	}
	
}
