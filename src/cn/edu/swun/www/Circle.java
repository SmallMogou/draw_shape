package cn.edu.swun.www.model;

import cn.edu.swun.www.model.Shape;
import cn.edu.swun.www.model.Point;
import cn.edu.swun.www.action.Drawable;
import java.util.ArrayList;

public class Circle extends Shape implements Drawable{
	private Point center;
	private double radius;
	public Circle(String color,Point center,double radius){
		super(color);
		this.center = center;
		this.radius = radius;
	}
	@Override
	public int getUpperBound(){
		return center.getY() + (int)radius;
	}
	@Override
	public double getArea(){
		return Math.pow(radius,2) * Math.PI;
	}
	@Override
	public ArrayList<Integer> intersectionPoint(int y){
		ArrayList<Integer> points = new ArrayList<Integer>();
		if(y >= (center.getY() - radius) && y <= (center.getY() + radius)){
			points.add((center.getX()-(int)Math.sqrt(radius*radius-Math.pow(y-center.getY(),2))));
			points.add((int)(center.getX() + Math.sqrt(radius*radius-Math.pow(y-center.getY(),2))));
		}
		return points;
	}
	@Override
	public void draw(String penType){
		new Shape.Paint().drawShape(penType,this.getUpperBound());
	}
}