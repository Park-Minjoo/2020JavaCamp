
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Option {
	public String shape;
	
	Color col_pen; // color
	Integer stro; //thick 
	boolean filling; // fill
	Point ptr1, ptr2;
	Vector<Point> ptrP = new Vector <Point>();
	Vector<Point> eraO = new Vector <Point>();
	public  Option() {
		filling=false;
	}
	
}
