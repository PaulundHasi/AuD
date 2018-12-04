import java.awt.Color;
import java.awt.Graphics;
public class Snake {
	public Point [] points = null;
	public Color color = Color.blue;
	
	public Snake (int length, int x, int y) {
		if (length < 0) {
			throw new  IllegalArgumentException ("length muss eine positive Zahl sein");
		}
		Point start = new Point (x,y);
		this.points [0] = start; 
	}
	public Snake (int x, int y) {
		this(5, x, y);
	}
	void paint(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < points.length; i++) {
			if (points [i] == null) {
				continue;
			}else {
				g.drawRect(points[i].getX(), points[i].getY(), SnakeGame.WIDTH, SnakeGame.HEIGHT);
			}
		}
	}

}
