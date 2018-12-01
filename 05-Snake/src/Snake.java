import java.awt.Color;
import java.awt.Graphics;
public class Snake {
	public Point [] points = null;
	public Color color = Color.blue;
	
	public Snake (int length, int x, int y) {
		if (length < 0) {
			throw new  IllegalArgumentException ("lenght muss eine positive Zahl sein");
		}
		Point start = new Point (x,y);
		this.points [0] = start; 
	}
	public Snake (int x, int y) {
		this(5, x, y);
	}
	void paint(Graphics g) {
		g.setColor(color);
		//TODO fehlt der Rest von e) ii) hab keine Lust mehr :)
	}

}
