package cn.edu.swun.www.model;

import cn.edu.swun.www.model.Point;
import cn.edu.swun.www.model.Shape;
import cn.edu.swun.www.action.Drawable;
import java.util.ArrayList;

public class Rectangle extends Shape implements Drawable{
	Point leftTop;
	Point rightBottom;
	public Rectangle(String color,Point leftTop,Point rightBottom){
		super(color);
		this.leftTop = leftTop;
		this.rightBottom = rightBottom;
	}
	@Override
	public int getUpperBound(){
		return rightBottom.getY();
	}
	@Override
	public double getArea(){
		return (double)Math.abs((rightBottom.getX()-leftTop.getX())*(rightBottom.getY()-leftTop.getY()));
	}
	@Override
	public ArrayList<Integer> intersectionPoint(int y){
		ArrayList<Integer> points = new ArrayList<Integer>();
		if(y >= leftTop.getY() && y <= rightBottom.getY()){
			if(y == leftTop.getY() || y == rightBottom.getY())
				for(int x = leftTop.getX(); x <= rightBottom.getX(); x++)
					points.add(x);
			else{
				points.add(leftTop.getX());
				points.add(rightBottom.getX());
			}
		}
		return points;
	}
	@Override
	public void draw(String penType){
		new Shape.Paint().drawShape(penType,this.getUpperBound());
	}
}