package application;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends MyShape{
 
	MyPoint center;
	int side;
	int radius;
	MyColor color;
	double xpoints[];
	double ypoints[];
	
	MyPolygon(MyPoint p, int radius,int side,MyColor color){
		super(p,color);
		this.center = p;
		this.side = side;
		this.radius = radius;
		this.color = color;
		xpoints = new double[side];
		ypoints = new double[side];
		// Finds vertices
		double angle = ((2* Math.PI)/side);
		for (int i = 0; i < side; i++){
			
		xpoints[i] = (int) (center.getX() + (radius*(-1* Math.sin(i*angle))));
		ypoints[i] = (int) (center.getY() + (radius*( -1*Math.cos(i*angle)))); 
			}
		
	}
	
	@Override
	public int area(){return (int) (Math.pow(radius,2) * side * Math.sin(getAngle()) * 0.5);}
	
	@Override
	public int perimeter() { return (int) (side * getSide());}
	
	public MyPoint getCenter() {return center;}
	public int getAngle() {return  (180*side - 360)/side;}
	public int getSide() {   return (int) (2 * radius * Math.sin(Math.PI/side));}
	public int getApothem() { return (int) (radius* Math.cos(Math.PI/side));}
	
	public String toString() {
		return "Polygon's Center Point: ("  + center.getX() + ","+ center.getY()+")" + " Side Length: " + getSide() + " Interior Angle: "
				+ getAngle() + " Perimeter: " + perimeter() + " Area: " + area();
	}
	@Override
	public MyRectangle getMyBoundingRectangle(){
	MyPoint p = new MyPoint(center.getX() - radius, center.getY() - radius);
	return new MyRectangle(p,2*radius, 2*radius, null);
	}
	@Override
	public boolean pointInMyShape(MyPoint p) {
		int x = p.getX(); int y = p.getY();
		for (int i = 0; i < side; i++) {
			if (x == xpoints[i] && y == ypoints[i]) {return true;}}
		 if (center.distance(p) <= this.getApothem()) {return true;}

		return false;
	}

	@Override
	public void draw(GraphicsContext GC){
		

	GC.setFill(color.getColor());
	GC.setStroke(color.getColor());
	GC.setLineWidth(3);
	GC.fillPolygon(xpoints, ypoints, side);
	GC.strokePolygon(xpoints, ypoints, side);
	}
	
	
	
};
