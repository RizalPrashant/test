import java.text.DecimalFormat;
public class Box {
private double width;
private double height;
private double depth;
private boolean full;

public Box(double startWidth, double startHeight, double startDepth){
	width = startWidth;
	height= startHeight;
	depth= startDepth;
	full = false;
	
}
public void setWidth(double newWidth){
	width= newWidth;
	
}
public double getWidth(){
	return width;
}
public void setHeight(double newHeight){
	height= newHeight;
}
public double getHeight(){
	return height;
}
public void setDepth(double newDepth){
	depth = newDepth;
}
public double getDepth() {
	return depth;
}
public void setFull(boolean newFull){
	full= newFull;
}

public boolean getFull(){
	return full;
}
public double volume(){
	double volume;
	volume= width * height * depth;
	return volume;
	
}
public double surfaceArea(){
	double surfaceArea;
	surfaceArea = 2*width*height + 2* height*depth + 2 * width* depth;
	return surfaceArea;
}

public String toString(){
	String pattern="##.00";
	DecimalFormat decimal = new DecimalFormat(pattern);
	if (full==false){
	return "An empty" + " "+decimal.format(width) + " * " + decimal.format(height) + " * " + decimal.format(depth) + " box"; 
	}
	else return "A full" + " "+decimal.format(width) + " * " + decimal.format(height) + " * " + decimal.format(depth) + " box"; 
}
}
