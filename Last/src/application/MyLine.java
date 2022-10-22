package application;

import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape{
	MyPoint p1,p2;
	MyPoint [] pLine = new MyPoint[2];
	MyColor color;
	
	MyLine(MyPoint p1,MyPoint p2, MyColor color) {
		super(new MyPoint(), null);
	
		this.p1 = p1;
		this.p2 = p2;
		pLine[0]= p1;
		pLine[1]= p2;
		this.color = color;
	}
	public MyPoint [] getLine() {return pLine;}
	public MyColor getColor() { return color;}
	
	public int xAngle() { return (int) p1.angleX(p2);}
	public int length() { return (int) p1.distance(p2);}
	
	@Override
	public int area() { return 0;}
	@Override
	public int perimeter() {return (int) length();}
	@Override
	public MyRectangle getMyBoundingRectangle() {
		int x1 = p1.getX(); int y1 = p1.getY();
		int x2 = p2.getX(); int y2 = p2.getY();
		MyPoint TopLeftPoint = new MyPoint(Math.min(x1, x2), Math.min(y1, y2));
		
		return new MyRectangle(TopLeftPoint, Math.abs(x1-x2), Math.abs(y1-y2),null);
	}
	@Override
	public boolean pointInMyShape(MyPoint p) {
		return (p1.distance(p) + p2.distance(p) == length());
	}
	
	@Override
	public void draw(GraphicsContext GC) {
		GC.setStroke(color.getColor());
		GC.strokeLine(p1.getX(), p2.getY(), p2.getX(), p1.getY());
		GC.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		GC.setLineWidth(5);
	}
	@Override
	public String toString() {
		return "Line [" + p1 + ","+p2+"] Lenth: " + length() + " Angle: " + xAngle();
	}
	

}