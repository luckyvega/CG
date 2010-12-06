
public class Cylinder implements Solid{
	public Point top;
	public Point bottom;
	public int radius;
	public Cylinder(Point t,Point b,int r){
		top = t;
		bottom = b;
		radius = r;
	}
	public Point[] intersects(Point p1,Point p2){
		Vector self = new Vector(top,bottom);
		Vector other = new Vector(p1,p2);
		Vector mid = new Vector(top,p1);
		Point[] intersects = new Point[2];
		intersects[0] = null;
		intersects[1] = null;
		double A = self.getNormDouble();
		double B = Vector.dotProduct(self,other);
		double C = Vector.dotProduct(mid,self);
		Vector v1 = Vector.sub(self.mul(B/A),other);
		Vector v2 = Vector.sub(self.mul(C/A),mid);
		
		double AA = v1.x*v1.x+v1.y*v1.y+v1.z*v1.z;
		double BB = v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
		double CC = v2.x*v2.x+v2.y*v2.y+v2.z*v2.z-radius*radius;
		double delta = BB*BB-AA*CC;
		
		if(delta<0){
			intersects[0] = null;
			intersects[1] = null;
			return intersects;
		}
		else if(delta==0){
			double root = (BB*-1)/AA;
			double t = (B*root+C)/A;
			if(t>0&&t<1){
				Point p = other.mul(root).getEndPoint(p1);
				intersects[0] = p;
				intersects[1] = null;
				return intersects;
			}
			else{
				Vector n;
				int index = 0;
				double t1 = C/B*-1;
				Point pt1 = other.mul(t1).getEndPoint(p1);
				n = Vector.add(v1.mul(t1),v2);
				if(n.getNormDouble()<radius*radius){
					intersects[index] = pt1;
					index++;
				}
				double t2 = (A-C)/B;
				n = Vector.add(v1.mul(t2),v2);
				if(n.getNormDouble()<radius*radius){
					Point pt2 = other.mul(t2).getEndPoint(p1);
					intersects[index] = pt2;
				}
				return intersects;
			}
		}
		else{
			double root1 = (BB*-1+Math.sqrt(delta))/AA;
			double root2 = (BB*-1-Math.sqrt(delta))/AA;
			
			double tmp1 = (B*root1+C)/A;
			double tmp2 = (B*root2+C)/A;
			
			int index = 0;
			
			if(tmp1>0&&tmp1<1){
				Point pt1 = other.mul(root1).getEndPoint(p1);
				intersects[index] = pt1;
				index++;
			}
			else{
				double t1 = C/B*-1;
				Vector n = Vector.add(v1.mul(t1),v2);
				if(n.getNormDouble()<radius*radius){
					Point pt1 = other.mul(t1).getEndPoint(p1);
					intersects[index] = pt1;
					index++;
				}
			}
			if(tmp2>0&&tmp2<1){
				Point pt2 = other.mul(root2).getEndPoint(p1);
				intersects[index] = pt2;
			}
			else{
				double t2 = (A-C)/B;
				Vector n = Vector.add(v1.mul(t2),v2);
				if(n.getNormDouble()<radius*radius){
					Point pt2 = other.mul(t2).getEndPoint(p1);
					intersects[index] = pt2;
				}
			}
			return intersects;
		}
	}
	public Vector getNormalVector(Point p){
		
		Vector v = new Vector(p,top);
		Vector self = new Vector(bottom,top);
		double distance = Vector.dotProduct(v,self)/self.getNorm();
		if(distance<0.1) return self;
		
		v = new Vector(p,bottom);
		self = new Vector(top,bottom);
		distance = Vector.dotProduct(v,self)/self.getNorm();
		if(distance<0.1) return self;
		
		double diffx = p.posx - bottom.posx;
		double diffy = p.posy - bottom.posy;
		double diffz = p.posz - bottom.posz;
		
		double axisx = top.posx - bottom.posx;
		double axisy = top.posy - bottom.posy;
		double axisz = top.posz - bottom.posz;
		
		double t = (diffx*axisx+diffy*axisy+diffz*axisz)/(axisx*axisx+axisy*axisy+axisz*axisz);
		
		double xx = diffx-t*axisx;
		double yy = diffy-t*axisy;
		double zz = diffz-t*axisz;
		
		return new Vector(xx,yy,zz);
	}
}
