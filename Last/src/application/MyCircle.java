package application;

import javafx.scene.canvas.GraphicsContext;


public  class MyCircle extends MyShape{
	MyPoint center;
	MyColor color;
	int radius;
	MyCircle(MyPoint p,int radius, MyColor color){
		super(p,color);
		this.center = p;
		this.color = color;
		this.radius = radius;
	}
	public MyPoint getCenter() {return center;}
	public int getRadius() { return radius;}
	public MyColor getColor() {return color;}
	public int getDiameter() {return 2*radius;}
	@Override
	public int area() 
	{
		int a = (int) (Math.PI *radius*radius);
		return a;
	}
	@Override
	public int perimeter() 
	{
		int p =  (int) (2* Math.PI * radius);
		return p;
	}
	@Override
	public MyRectangle getMyBoundingRectangle() {
		int x = center.getX() - radius;
		int y = center.getY() - radius;
		MyPoint TopLeftPoint = new MyPoint(x,y);
		
		return new MyRectangle(TopLeftPoint,radius*2,radius*2,null);
	}
	@Override
	public boolean pointInMyShape(MyPoint p) {
		return center.distance(p) <= radius;
	}
	@Override
	public void draw(GraphicsContext GC) 
	{
		GC.setFill(color.getColor());	
		GC.strokeOval(center.getX()- radius,center.getY() -radius, 2.0*radius,2.0* radius);
		GC.fillOval(center.getX()- radius,center.getY() -radius, 2.0*radius,2.0* radius);
		
	}
	@Override
	public String toString() {
		return "Circle Center: ("+ center.getX() + ","+ center.getY()+")"+" Radius: "
				+radius+ " Area:"+area()+" Perimeter:"
				+perimeter() + " Hexcolor: " + color.getHexColor();
	}

}
