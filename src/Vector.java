
public class Vector {
	public double x;
	public double y;
	public double z;
	public Vector(double xx,double yy,double zz){
		x = xx;
		y = yy;
		z = zz;
	}
	public Vector(Point p1,Point p2){
		x = p2.posx-p1.posx;
		y = p2.posy-p1.posy;
		z = p2.posz-p1.posz;
	}
	public Point getEndPoint(Point start){
		double xx = start.posx+x;
		double yy = start.posy+y;
		double zz = start.posz+z;
		return new Point(xx,yy,zz);
	}
	public static Vector add(Vector v1,Vector v2){
		double xx = v1.x+v2.x;
		double yy = v1.y+v2.y;
		double zz = v1.z+v2.z;
		return new Vector(xx,yy,zz);
	}
	public static Vector sub(Vector v1,Vector v2){
		double xx = v1.x-v2.x;
		double yy = v1.y-v2.y;
		double zz = v1.z-v2.z;
		return new Vector(xx,yy,zz);
	}
	public Vector div(double d){
		double xx = x/d;
		double yy = y/d;
		double zz = z/d;
		return new Vector(xx,yy,zz);
	}
	public Vector mul(double m){
		double xx = x*m;
		double yy = y*m;
		double zz = z*m;
		return new Vector(xx,yy,zz);
	}
	public static boolean isparallel(Vector v1,Vector v2){
		if(v1.x*v2.y==v2.x*v1.y&&v1.y*v2.z==v2.y*v1.z) return true;
		else return false;
	}
	public static Vector bisector(Vector v1,Vector v2){
		double norm1 = v1.getNorm();
		double norm2 = v2.getNorm();
		return add(v1.div(norm1),v2.div(norm2));
	}
	public static double dotProduct(Vector v1,Vector v2){
		return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
	}
	public static Vector crossProduct(Vector v1,Vector v2){
		double xx = v1.y*v2.z-v1.z*v2.y;
		double yy = v1.z*v2.x-v1.x*v2.z;
		double zz = v1.x*v2.y-v1.y*v2.x;
		return new Vector(xx,yy,zz);
	}
	public static Vector reverse(Vector v){
		return new Vector(v.x*-1,v.y*-1,v.z*-1);
	}
	public double getNorm(){
		return Math.sqrt(x*x+y*y+z*z);
	}
	public double getNormDouble(){
		return x*x+y*y+z*z;
	}
}
