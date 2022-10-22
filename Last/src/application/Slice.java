package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Slice {
	MyPoint center;
	int radius;
	double startAngle;
	double angle;
	double rStartAngle,rAngle,rEndAngle;
	
	MyColor color;
	
	Slice(MyPoint p,int r,double startAngle,double angle, MyColor color){
		this.center = p;
		this.radius = r;
		this.startAngle = startAngle;
		this.angle = angle;
		this.color = color;
		
		this.rAngle = Math.toRadians(angle);
	}
	
	public MyPoint getCenter() {return center;}
	public MyColor getColor() {return color;}
	public int getRadius() {return radius;}
	public double getStartAngle() {return startAngle;}
	public double getAngle() {return angle;}
	public double getArcLength() {return (double) radius * rAngle;}
	
	public double area() {return 0.5 * rAngle * Math.pow(radius, 2);}
	public double perimeter() {return getArcLength();}
	
	public String toString() {
		return "Slice Center: (" + center.getX() + ","+ center.getY() + ")" 
				+ " Radius: " + radius + "(starting anlge, angle): ("+ startAngle +","+ angle+")";
	}
	public void draw(GraphicsContext GC){
		GC.strokeArc(center.getX() - radius, center.getY() - radius, 2 *radius, 2* radius, startAngle, angle, ArcType.ROUND);
		GC.setFill(color.getColor());
		GC.fillArc(center.getX() - radius, center.getY() - radius, 2 *radius, 2* radius, startAngle, angle, ArcType.ROUND);
		
	}
	
}
