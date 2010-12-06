
public class Point {
	public double posx;
	public double posy;
	public double posz;
	private static int diffvalue = 4;
	public Point(double x,double y,double z){
		posx = x;
		posy = y;
		posz = z;
	}
	public boolean inside(Point p1,Point p2){
		if(posx>p1.posx&&posx>p2.posx) return false;
		if(posy>p1.posy&&posy>p2.posy) return false;
		if(posz>p1.posz&&posz>p2.posz) return false;
		if(posx<p1.posx&&posx<p2.posx) return false;
		if(posy<p1.posy&&posy<p2.posy) return false;
		if(posz<p1.posz&&posz<p2.posz) return false;
		return true;
	}
	public boolean equal(Point p){
		Vector v = new Vector(this,p);
		if(v.getNormDouble()<diffvalue) return true;
		else return false;
	}
}
