
public class Cube implements Solid {
	public Point corner;
	public Vector xdirection;
	public Vector ydirection;
	public Vector zdirection;
	public int edgelength;
	public static Cube generateCube(Point c,Vector x,Vector y,int e){
		if(Vector.dotProduct(x,y)!=0) return null;
		else return new Cube(c,x,y,e);
	}
	public Vector getNormalVector(Point p){
		return null;
	}
	private Cube(Point c,Vector x,Vector y,int e){
		corner = c;
		xdirection = x;
		ydirection = y;
		zdirection = Vector.crossProduct(x,y);
		edgelength = e;
	}
	public Point[] intersects(Point p1,Point p2){
		return null;
	}
}
