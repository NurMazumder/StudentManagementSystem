package application;
import java.util.Random;

import javafx.scene.paint.Color;

public enum MyColor {
	
	BLACK(0,0,0,255),
 	WHITE(255,255,255,255),
 	RED(255,0,0,255),
 	LIME(0,255,0,255),
 	BLUE(0,0,255,255),
 	YELLOW(255,255,0,255),
 	CYAN(0,255,255,255),
 	MANGENTA(255,0,255,255),
 	SILVER(192,192,192,255),
 	GREY(127,128,128,255),
 	MAROON(128,0,0,255),
 	OLIVE(128,128,0,255),
 	GREEN(0,128,0,255),
 	PURPLE(128,0,128,255),
 	TEAL(0,128,128,255),
 	NAVY(0,0,128,255),
	FIREBRICK(178,34,34,255),
 	CRIMSON(220,20,60,255),
 	TOMATO(255,99,71,255),
 	COLAL(255,127,80,255),
 	SALMON(250,128,114,255),
 	DARKORANGE(255,140,0,255),
 	ORANGE(255,165,0,255),
 	GOLD(255,215,0,255);
 
	
	 int r,g,b,a,argb;
	 public void setR(int r) {if(r>=0 && r<=255) { this.r = r; } }
	 public void setG(int g) {if(g>=0 && g<=255) { this.g = g; } }
	 public void setB(int b) {if(b>=0 && b<=255) { this.b = b; } }
	 public void setA(int a) {if(a>=0 && a<=255) { this.a = a; } }
	 
	 MyColor(int r, int g, int b, int a)
		{ 
			this.r = r; 
			this.g = g; 
			this.b = b; 
			this.a = a; 
			setARGB(r,g,b,a);
		}
	 public void setARGB(int r, int g, int b, int a) {
		 this.argb = (a << 24) & 0xFF000000 |
				 	 (r << 16) & 0x00FF0000 |
				 	 (g << 8) & 0x0000FF00  |
				 	 b;
	 }
	 
	 public int getR() {return r;} 
	 public int getG() {return g;} 
	 public int getB() {return b;} 
	 public int getA() {return a;}
	 public int getAGRG() {return argb;}
	 public Color getColor() {return Color.rgb(r, g, b, 0.7); }
	 public static MyColor getRand() {
		 	MyColor [] colors = MyColor.getMyColor();
			
			Random rand = new Random();
			int colorsSize = colors.length;
			return colors[rand.nextInt(colorsSize)];
	 }

	 public static MyColor [] getMyColor() {
		 return MyColor.values();
	 }
	 public String getHexColor() {
		 return "#"+ Integer.toHexString(argb).toUpperCase();
	 }
	
	
}

	