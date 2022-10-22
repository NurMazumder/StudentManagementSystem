package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape{
	MyPoint center;
	MyPoint p1,p2;
	double startAngle;
	double angle;
	double rStartAngle,rAngle,rEndAngle;
	
	MyColor color;
	MyCircle C;
	int r;
	MyArc(MyPoint p, int r, double startAngle,double angle, MyColor color){
		super(new MyPoint(), null);
		
		this.center = p;this.r =r;
		this.startAngle = startAngle;
		this.angle = angle;
		
		this.color = color;
		
		this.rStartAngle =  Math.toRadians(startAngle);
		this.rAngle = Math.toRadians(angle);
		this.rEndAngle = Math.toRadians( startAngle + angle);
		
		int x = center.getX();
		int y = center.getY();
		int x1 = (int) (x+ (double) (r*r)/Math.sqrt(Math.pow(r, 2) + Math.pow(r * Math.tan(rStartAngle), 2)));
		int y1 = (int) (y+ (double) (r*r*Math.tan(rStartAngle)) / Math.sqrt(Math.pow(r, 2) + Math.pow(r * Math.tan(rStartAngle), 2)));
		int x2 = (int) (x+ (double) (r*r)/Math.sqrt(Math.pow(r, 2) + Math.pow(r * Math.tan(rEndAngle), 2)));
		int y2 = (int) (y+ (double) (r*r*Math.tan(rStartAngle)) / Math.sqrt(Math.pow(r, 2) + Math.pow(r * Math.tan(rEndAngle), 2)));
		this.p1 = new MyPoint(x1,y1);
		this.p2 = new MyPoint(x2,y2); 	
		this.C = new MyCircle(center,r,color);
	}
	public MyPoint getCenter() {return center;}
	public double getStartAngle() { return startAngle;}
	public double getAngle() { return angle;}
	public MyCircle getCircle() { return C;}
	public MyColor getColor() { return color;}
	
	@Override
	public int area() {
		return (int) (0.5 * rAngle * Math.pow(r, 2));
	}
	@Override
	public int perimeter() { return (int) (0.5 * Math.PI / Math.sqrt(2) * p1.distance(p2));}

	@Override
	public MyRectangle getMyBoundingRectangle() {
		return C.getMyBoundingRectangle();
	}

	@Override
	public boolean pointInMyShape(MyPoint p) {
		double pAngle = center.angleX(p);
		int dx = center.getX()-p.getX();
		int dy = center.getY()-p.getY();
		
		return Math.pow(r* dx,2) + Math.pow(r*dy,2) <= Math.pow(r*r, 2) 
				&& pAngle >= startAngle && pAngle <= startAngle + angle;
	}

	@Override
	public void draw(GraphicsContext GC) {
		GC.setFill(color.getColor());						
		GC.fillArc(center.getX()-r, center.getY()-r, 2*r,2*r,startAngle,angle,ArcType.ROUND);
		GC.strokeArc(center.getX()-r, center.getY()-r, 2*r,2*r,startAngle,angle,ArcType.ROUND);
		GC.setStroke(color.getColor());

	}
	@Override
	public String toString() {
		return "Arc: Center" + center + " Circle Diameter:" + 2*r + " (start Angle, Angle): ("
				+ startAngle + "," + angle + "), "
				+ " Perimeter: " + perimeter() + " Area: " + area();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
