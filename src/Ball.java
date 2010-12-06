
public class Ball implements Solid {
	public double posx;
	public double posy;
	public double posz;
	public double radius;
	public Ball(double x,double y,double z,double r){
		posx = x;
		posy = y;
		posz = z;
		radius = r;
	}
	public Vector getNormalVector(Point p){
		Point center = new Point(posx,posy,posz);
		return new Vector(center,p);
	}
	public Point[] intersects(Point p1,Point p0){
		Point[] out = {null,null};
		double delx = p1.posx-p0.posx;
		double dely = p1.posy-p0.posy;
		double delz = p1.posz-p0.posz;
		
		double diffx = p0.posx-this.posx;
		double diffy = p0.posy-this.posy;
		double diffz = p0.posz-this.posz;
		
		double A = delx*delx+dely*dely+delz*delz;
		double B = delx*diffx+dely*diffy+delz*diffz;
		double C = diffx*diffx+diffy*diffy+diffz*diffz-this.radius*this.radius;
		if(B*B-A*C<0) return out;
		else
		{
			double square = Math.sqrt(B*B-A*C);
			double root1 = (B*-1+square)/A;
			double root2 = (B*-1-square)/A;
			if(root1==root2){
				 double xx = p0.posx+root1*delx;
				 double yy = p0.posy+root1*dely;
				 double zz = p0.posz+root1*delz;
				 out[0] = new Point(xx,yy,zz);
			}
			else{
				double xx = p0.posx+root1*delx;
				double yy = p0.posy+root1*dely;
				double zz = p0.posz+root1*delz;
				out[0] = new Point(xx,yy,zz);
				xx = p0.posx+root2*delx;
				yy = p0.posy+root2*dely;
				zz = p0.posz+root2*delz;
				out[1] = new Point(xx,yy,zz);
			}
		}
		return out;
	}
}
