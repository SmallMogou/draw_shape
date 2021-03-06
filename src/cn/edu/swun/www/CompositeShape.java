package cn.edu.swun.www.model;

import java.util.ArrayList;
import java.util.Iterator;
import cn.edu.swun.www.action.Drawable;
import cn.edu.swun.www.model.Rectangle;
import cn.edu.swun.www.model.Circle;

public class CompositeShape extends Shape implements Drawable{
	private ArrayList<Shape> shapes;
	public CompositeShape(String color,ArrayList<Shape> shapes){
		super(color);
		this.shapes = shapes;
	}
	@Override
	public int getUpperBound(){
		int maxY = 0,temp = 0;		
		for(Iterator<Shape> li = shapes.iterator(); li.hasNext();)
			if(maxY < (temp = li.next().getUpperBound())) maxY = temp;
		return maxY;
	}
	@Override
	public double getArea(){
		double area = 0;
		for(Iterator<Shape> li = shapes.iterator(); li.hasNext();)
			area += li.next().getArea();
		return area;
	}
	@Override
	public ArrayList<Integer> intersectionPoint(int y){
		ArrayList<Integer> points = new ArrayList<Integer>();
		for(Shape sp : shapes)
			points.addAll(sp.intersectionPoint(y));
		return points;
	}
	@Override
	public void draw(String penType){
		new Shape.Paint().drawShape(penType,this.getUpperBound());
	}
}