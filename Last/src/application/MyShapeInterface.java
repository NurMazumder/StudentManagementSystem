package application;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface MyShapeInterface {
	MyRectangle getMyBoundingRectangle();
	boolean pointInMyShape(MyPoint p);
	
	public static MyRectangle overlapMyRectangles(MyRectangle R1, MyRectangle R2) {
		int x1 = R1.getTopLeftPoint().getX();
		int y1 = R1.getTopLeftPoint().getY();
		int w1 = R1.getWidth();
		int h1 = R1.getHeight();
		
		int x2 = R2.getTopLeftPoint().getX();
		int y2 = R2.getTopLeftPoint().getY();
		int w2 = R2.getWidth();
		int h2 = R2.getHeight();
		
		if (y1 + h1 < y2 || y1 > y2 + h2) {return null;}
		if (x1 + w1 < x2 || x1 > x2 + w2) {return null;}
		
		int xmax = Math.max(x1, x2);
		int ymax = Math.max(y1, y2);
		int xmin = Math.min(x1+w1, x2+w2);
		int ymin = Math.min(y1+h1, y2+h2);
		
		MyPoint p = new MyPoint(xmax,ymax);
		return new MyRectangle(p,Math.abs(xmin-xmax),Math.abs(ymin-ymax),null);
	}
	public static MyRectangle overlapMyShapes(MyShape S1, MyShape S2) {
		MyRectangle R1 = S1.getMyBoundingRectangle();
		MyRectangle R2 = S2.getMyBoundingRectangle();
		return overlapMyRectangles(R1,R2);
	}

	public static List<MyPoint> intersectMyShapes(MyShape S1, MyShape S2){
		MyRectangle R1 = S1.getMyBoundingRectangle();
		MyRectangle R2 = S2.getMyBoundingRectangle();
		MyRectangle R = overlapMyShapes(R1,R2);
	
		if (R != null) {
			int x = R.getTopLeftPoint().getX();
			int y = R.getTopLeftPoint().getY();
			int w = R.getWidth();
			int h = R.getHeight();
			
			List<MyPoint> intersect = new ArrayList<MyPoint>();
			
			for(int i = 0; i < w; ++i) {
				int xi = x + 1;
				for (int j = 0; j < h; ++j) {
					MyPoint p = new MyPoint(xi,y+j);
					if (S1.pointInMyShape(p) && S2.pointInMyShape(p)) {
						intersect.add(p);
					}
				}
			}
			return intersect;	
	}
		else {
			return null;
	}}
		
	public default Canvas drawIntersectMyShapes(MyShape S1, MyShape S2) {
			List<MyPoint> intersect = intersectMyShapes(S1,S2);

			Canvas overlayCV = new Canvas();
			
			GraphicsContext overlayGC = overlayCV.getGraphicsContext2D();
			S1.draw(overlayGC);
			S2.draw(overlayGC);
		
		for(MyPoint p : intersect) {
			p.draw(overlayGC);
		}
		return overlayCV;
		}
	
}
