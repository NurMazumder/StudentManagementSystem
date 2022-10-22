package application;

import javafx.scene.canvas.GraphicsContext;


public class MyRectangle extends MyShape{
	MyPoint TopLeftPoint;
	int width,height;
	MyColor color;
	
	MyRectangle(MyPoint p, int width, int height, MyColor color){
		
		super(p,color);
		this.width = width;
		this.height = height;
		this.TopLeftPoint = p;
		if (color != null) {this.color = color;	}
		else {this.color = MyColor.GREY;}
		}


	public MyPoint getTopLeftPoint () { return TopLeftPoint;}
	public MyColor getGolor() { return color;}
	public int getWidth() {return width;}                        
	public int getHeight() {return height;} 
	@Override
	public int area() {return  width * height;}
	@Override
	public int perimeter() {return 2*width + 2*height;}
	@Override
	public MyRectangle getMyBoundingRectangle() {return this;}
	@Override
	public boolean pointInMyShape(MyPoint p) {
		int x = p.getX(); int y = p.getY();
		int xR = TopLeftPoint.getX();int yR = TopLeftPoint.getY();
		
		return (xR <= x && y<=xR + width) && (yR <= y && y <= yR + height);
				
		
	}
	@Override
	public String toString() 
	{
		return "Rectangle Top Left Corner " +getTopLeftPoint() + " Width: " + width + " Height: "
			+height+" Parameter: "+ perimeter()+" Area: "+ area() 
			+ " Hexcolor:"+ color.getHexColor();
	}
	
	@Override
	public void draw(GraphicsContext GC) 
	{
		GC.setFill(color.getColor());
		GC.fillRect(TopLeftPoint.getX(),TopLeftPoint.getY(), width, height);
		GC.strokeRect(TopLeftPoint.getX(),TopLeftPoint.getY(), width, height);	

	}	
}
