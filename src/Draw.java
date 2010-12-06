import java.awt.*;
import java.util.*;

public class Draw {
	private Graphics g;
	private Config config;
	
	public Draw(Graphics gg,Config c){
		g = gg;
		config = c;
	}
	private int calRGB(double cross1,double cross2,double fatt){
		double dark = (fatt*(0.45*cross1+0.5*Math.pow(cross2,3)))/(0.95*fatt);
		int rgb = (int)(dark*256);
		return rgb;
	}
	private int calRGB(ColorConfig cc){
		return calRGB(cc.diffusefactor,cc.mirrorfactor,cc.fatt);
	}
	public void draw(){
		int[][] map = createBitmap();
		for(int i=0;i<config.viewheight;i++){
			for(int j=0;j<config.viewwidth;j++){
				if(map[i][j]!=-1){
					int rgb = map[i][j];
					g.setColor(new Color(rgb,rgb,rgb));
					g.drawOval(i,j,1,1);
				}
			}
		}
	}
	public int[][] createBitmap(){
		int[][] map = new int[config.viewheight][config.viewwidth];
		for(int i=0;i<config.viewheight;i++){
			for(int j=0;j<config.viewwidth;j++){
				Point panel = new Point(i,j,0);
				ArrayList<Point> plist = new ArrayList<Point>();
				Hashtable<Point,Solid> table = new Hashtable<Point,Solid>();
				for(int k=0;k<config.objectlist.size();k++){
					Point[] intersects = config.objectlist.get(k).intersects(panel,config.eye);
					if(intersects[0]!=null){
						plist.add(intersects[0]);
						table.put(intersects[0],config.objectlist.get(k));
					}
					if(intersects[1]!=null){
						plist.add(intersects[1]);
						table.put(intersects[1],config.objectlist.get(k));
					}
				}
				int rgb = -1;
				if(plist.size()!=0){
					Point nearest = plist.get(0);
					for(int k=1;k<plist.size();k++){
						if(plist.get(k).posz<nearest.posz){
							nearest = plist.get(k);
						}
					}
					for(int m=0;m<config.lightlist.size();m++){
						boolean visible = true;
						for(int k=0;k<config.objectlist.size();k++){
							Point[] intersects = config.objectlist.get(k).intersects(nearest,config.lightlist.get(m));
							if(intersects[0]!=null){
								if(intersects[0].inside(nearest,config.lightlist.get(m))&&!intersects[0].equal(nearest)){
									visible = false;
									break;
								}
							}
							if(intersects[1]!=null){
								if(intersects[1].inside(nearest,config.lightlist.get(m))&&!intersects[1].equal(nearest)){
									visible = false;
									break;
								}
							}
						}
						ColorConfig cc = new ColorConfig();
						if(visible==false){
							cc.diffusefactor = 0;
							cc.mirrorfactor = 0;
							cc.fatt = 1;
						}
						else{
							Vector v1 = new Vector(config.lightlist.get(m),nearest);
							double norm1 = v1.getNorm();
							Vector v2 = table.get(nearest).getNormalVector(nearest);
							double norm2 = v2.getNorm();
							double cross1 = Math.abs(Vector.dotProduct(v1,v2)/norm1/norm2);
							Vector v3 = new Vector(config.eye,nearest);
							Vector v4 = Vector.bisector(v1,v3);
							double norm4 = v4.getNorm();
							double cross2 = Math.abs(Vector.dotProduct(v4,v2)/norm2/norm4);
							cc.diffusefactor = cross1;
							cc.mirrorfactor = cross2;
							cc.fatt = 1/norm1;
						}
						int tmp = calRGB(cc);
						if(rgb==-1) rgb = tmp; 
						else rgb += tmp;
					}
				}
				if(rgb==-1) map[i][j] = -1;
				else{
					map[i][j] = rgb/config.lightlist.size();
				}
			}
		}
		return map;
	}
}
