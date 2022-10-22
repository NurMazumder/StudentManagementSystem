package application;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.LinkedHashMap;


public class HistogramAlphaBet {
	
	Map<Character,Integer> frequency = new HashMap<Character, Integer>();
	Map<Character,Double> probability = new HashMap<Character, Double>();
	int all;

	HistogramAlphaBet(Map<Character,Integer> m){
		frequency.putAll(m);
	}
	HistogramAlphaBet(String text){

		String w = text.replaceAll("[^a-zA-Z]","").toLowerCase();

		for(int i = 0; i < w.length();i++) {
			Character key = w.charAt(i);
			incrementFrequency(frequency, key);
		}
	}
	public Map<Character, Integer> getFrequency(){ return frequency;}
	
	public Integer getCumulativeFrequency() {
		int sum = 0;
		for(Integer value : frequency.values()) {sum += value; all = sum;}
		return all;
	}
	public Map<Character,Integer> sortDownFrequency(){
		return frequency.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
	}
	public Map<Character,Integer> sortUpFrequency(){
		return frequency.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
	}
	public Map<Character, Double>getProbability(){
		for(Character key : frequency.keySet()) {
			probability.put(key, (double) frequency.get(key) *  (1.0 / getCumulativeFrequency()));
		}
		return probability.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
	}
	public double getSumofPrabiblity() { return probability.values().stream().reduce(0.0, Double::sum);}
	
	private static<K> void incrementFrequency(Map<K,Integer>m, K key) {
		m.putIfAbsent(key, 0);
		m.put(key, m.get(key)+1);
	}
	public String toString() {
		String output = "Frequency of Characters:\n";
		for (Character K : frequency.keySet()) {
			output += K + ": "+ frequency.get(K)+ "\n";
		}
		return output;
	}
	class MyPieChart{
		Map<Character, Slice> slices = new HashMap<Character, Slice>();
		Map<Character, Double> ns = new HashMap<>();
		Map<Character, Integer> freq = new HashMap<>();

		double m;
		int N;
		MyPoint center;
		int radius;
		double rotateAngle;
		
		
		MyPieChart(int N, MyPoint p, int r, double rotateAngle){
			this.N = N;
			this.center = p;
			this.radius = r;
			this.rotateAngle = rotateAngle;
			
			probability = getProbability();
			slices = getMyPieChart();	
			
		}
		public Map<Character, Slice> getMyPieChart(){
			
			MyColor [] colors = MyColor.getMyColor();
			
			Random rand = new Random();
			int colorsSize = colors.length;
			double angle;
			double startAngle = rotateAngle;
			
			
			for (int i = 1; i <= N; i++) {
			    double max = 0, sum = 0;
			    Character maxKey = 'a';
			    for (Character key : probability.keySet()) {
			        if (probability.get(key) > max) {
			            max = probability.get(key);
			            maxKey = key;
			        }
			    }
			    ns.put(maxKey, max);
			    probability.remove(maxKey);
			}
			

			for(Character Key : ns.keySet()) {
				angle = 360.0 * ns.get(Key);	
				slices.put(Key, new Slice(center,radius,startAngle, angle, colors[rand.nextInt(colorsSize)]));		
				startAngle += angle;
			}	
			
			return slices;
		}
		public void draw(GraphicsContext GC) {
			
		//	MyCircle C = new MyCircle(center,radius,MyColor.WHITE);
		//	C.draw(GC);
			double midA,X,Y;
			
			for(Character Key : ns.keySet()) {
				double start = slices.get(Key).getStartAngle();
				double angle = slices.get(Key).getAngle();
				slices.get(Key).draw(GC);
				midA = start + angle/2;
				
				X = (radius * 1.1) * Math.cos(Math.toRadians(midA));
				Y = (radius * 1.1) * Math.sin(Math.toRadians(midA));
				
				if (midA > 100 && midA < 250){ X -= radius/4;}
				start  = slices.get(Key).getStartAngle() +  slices.get(Key).getAngle();
			
			//	GC.setStroke(slices.get(Key).getColor().getColor());
				GC.setFill(MyColor.BLACK.getColor());
				GC.setFont(new Font(null, 15));
			//	double num =Math.round(ns.get(Key) * 100.0)/100.0;
				GC.fillText(Key +", "+ frequency.get(Key) ,center.getX() + X, center.getY() - Y);
			//	GC.fillText(Key +", "+ num ,center.getX() + X-15, center.getY() - Y+15);
				//GC.fillText(Key +", "+ ns.get(Key) , 20 +x, 30*x+200);
				
			}
		/*	
			double rest =0;
			for(Character Key : ns.keySet()) {
			
				rest += ns.get(Key);
			}
			rest = 1- rest;
			//String r = String.valueOf(rest);
			double num =Math.round(rest * 100.0)/100.0;
			String r = String.valueOf(num);
			GC.fillText("all other letters" +", " + r , 750, 300);	*/
		}
	}
	}




