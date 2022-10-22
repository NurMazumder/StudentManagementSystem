package application;

import javafx.scene.canvas.GraphicsContext;

public class MyPoint {
	int x,y;
	MyPoint p;
	MyColor color = MyColor.GREY;
	
	
	MyPoint(int x,int y){
	setPoint(x,y); 
	}
	
	MyPoint(MyPoint p){
		setPoint(p);}
	
	MyPoint(){setPoint(0,0);}
	
	public void setPoint(MyPoint p) {this.x = p.getX(); this.y = p.getY();}
	public void setPoint(int x,int y){this.x =x; this.y=y;}
	
	public void setColor(MyColor color) { this.color = color;}
	public void setX(int x) {this.x =x;}
	public void setY(int y) {this.y =y;}
	public int getX() {return x;}
	public int getY() {return y;}
	public MyPoint getPoint() { return p;}
	
public double distance(MyPoint p2) {
		
		int cX = x - p2.getX();
		int cY = y - p2.getY();
		double d = Math.sqrt((cX * cX + cY * cY));
		return d;
	}

public double angleX(MyPoint p) {
	int dx =  (p.getX()- x);
	int dy =  (p.getY()- y);
	return Math.toDegrees(Math.atan2(dy, dx));
}
public void draw(GraphicsContext GC) {
	GC.setFill(color.getColor());
	GC.fillRect(x,y,1,1);
	
}
	@Override
	public String toString() {
		return "Point(" + x +","+y+")";
	}
}
