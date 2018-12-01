
public class Snake {
	public Point [] points = null;
	public java.awt.Color color;
	
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

}
