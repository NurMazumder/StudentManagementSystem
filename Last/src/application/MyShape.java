package application;


import javafx.scene.canvas.GraphicsContext;


public abstract class MyShape implements MyShapeInterface{
 MyPoint p;
 MyColor color;
 
 MyShape(MyPoint p, MyColor color){ setPoint(p);this.color = color;}
 MyShape(int x,int y,MyColor color){setPoint(x,y);this.color = color;};

 	public void setPoint(MyPoint p) {p.setPoint(p);}
 	public void setPoint(int x,int y) {setPoint(x,y);}

 	public MyPoint getPoint() {return p;}
 	public double getX(){ return p.getX(); }
	public double getY(){ return p.getY(); }
	public MyColor getColor(){ return color; }

	public abstract int area();
	public abstract int perimeter();
	
	public abstract void draw(GraphicsContext GC);
	
	
	public String toString() {
		return "This is MyShape: ";
	}


 
}
