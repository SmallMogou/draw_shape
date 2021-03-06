package cn.edu.swun.www.model;

import cn.edu.swun.www.model.Point;

public class Line{
	private Point begin;
	private Point end;
	public Line(){};
	public Line(Point begin,Point end){
		this.begin = begin;
		this.end = end;
	}
	public Point getBegin(){ 
		return this.begin;
	}
	public Point getEnd(){
		return this.end;
	}
	public void setBegin(Point begin){
		this.begin = begin;
	}
	public void setEnd(Point end){
		this.end = end;
	}
	public double getLength(){
		return Math.sqrt(Math.pow(this.begin.getX()-this.end.getX(),2)+Math.pow(this.begin.getY()-this.end.getY(),2));
	}
}