package cn.edu.swun.www;

import cn.edu.swun.www.action.Drawable;
import cn.edu.swun.www.model.*;
import java.util.ArrayList;
/**
	@author lm
	主类
	执行之后将在 Windows 控制台 画出简易的图形
 */
public class TestMain{
	public static void DrawShape(Drawable dShape,String penTyppe){
		dShape.draw(penTyppe);
	}
	public static void main(String[] args){
		Point p1 = new Point(10,10);
		Point p2 = new Point(20,30);
		Shape rect = new Rectangle("红色",p1,p2);
		Shape cir = new Circle("绿色",p1,5);
		Shape.beginDrawToFile("test.txt");

		Shape rect_1 = new Rectangle("红色",new Point(5,10),new Point(80,19));
		Shape cir_1 = new Circle("绿色",new Point(5,10),5);

		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(rect_1);
		shapes.add(cir_1);
		shapes.add(rect);
		shapes.add(cir);

		CompositeShape comp = new CompositeShape("blue",shapes);
		DrawShape(comp,".");
		Shape.endDrawToFile();
	    DrawShape(comp,"*");
	}
}
 //javac -d .\build -encoding utf-8 .\src\cn\edu\swun\www\*.java 
 //java -cp .\build cn.edu.swun.www.TestMain
 