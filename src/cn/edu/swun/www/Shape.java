package cn.edu.swun.www.model;

import java.util.ArrayList;
import java.io.*;

public abstract class Shape {
	protected String color;
	public Shape(String color){
		this.color = color;
	}
	public abstract double getArea();	
	public abstract ArrayList<Integer> intersectionPoint(int y);
	public abstract int getUpperBound();
	private static PrintWriter pw;
	private static boolean isToFile;
	public static void beginDrawToFile(String fileName){
		try{
			pw = new PrintWriter(new FileWriter(fileName));
			isToFile = true;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void endDrawToFile(){
		if(pw != null){
			pw.close();
			isToFile = false;
		}
	}
	public class Paint{
		private void print(String outstr){
			if(isToFile) pw.print(outstr);
			else System.out.print(outstr);
		}
		//移除重合点
		public void removePoint(ArrayList<Integer> points){
			for(int i = 0; i < points.size(); i++)
				for(int j = i + 1; j < points.size(); j++)
					if(points.get(i).equals(points.get(j)))
						points.remove(j);
		}
		//处理每行的点，并将处理过的点返回
		public String drawPoints(String penType,ArrayList<Integer> points){
			removePoint(points);
			removePoint(points);
			points.sort(null);
			StringBuffer pointsLine = new StringBuffer();
			for(int i = 0; i < points.size(); i++){
				if(i == 0)
					for(int j = 0; j < points.get(i); j++)
						pointsLine.append(" ");
				else
					for(int k = 0; k < (points.get(i)-points.get(i-1)-1); k++)
						pointsLine.append(" ");
				pointsLine.append(penType);
			}
			pointsLine.append("\n");
			return pointsLine.toString();
		}
		//画出对应的形状
		public void drawShape(String penType,int maxY){
			for(int  y = 0; y <= maxY; y++)
				print(drawPoints(penType,Shape.this.intersectionPoint(y)));
		}
	}
}