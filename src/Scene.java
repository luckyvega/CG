import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Scene extends JFrame {
	public static final long serialVersionUID = 12345678;
	private JPanel display;
	private Config config;
	public Scene(){
		config = new Config();
		config.viewwidth = 400;//for x axis
		config.viewheight = 400;//for y axis
		config.lightlist = new ArrayList<Point>();
		config.lightlist.add(new Point(10,10,-300));
		config.lightlist.add(new Point(200,200,-300));
		config.eye = new Point(200,200,-100);
		config.objectlist = new ArrayList<Solid>();
		
		config.objectlist.add(new Ball(100,100,250,200));
		config.objectlist.add(new Ball(300,50,600,400));
		
		Cylinder cy = new Cylinder(new Point(200,300,200),new Point(200,400,200),100);
		config.objectlist.add(cy);
		
		display = new JPanel();
		display.setPreferredSize(new Dimension(0,0));
		Container c = this.getContentPane();
		c.add(display);
		this.setTitle("Demo");
		this.setSize(400,400);
		this.setVisible(true);
		this.setResizable(false);
	}
	public void paint(Graphics gg){
		Graphics g = display.getGraphics();
		Draw d = new Draw(g,config);
		d.draw();
	}
	public static void main(String[] s){
		Scene sc = new Scene();
		sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
