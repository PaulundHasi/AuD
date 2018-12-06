import java.awt.Graphics;

abstract public class GameItem {

	private Point position;
	public GameItem(int x, int y) {
		this.position = new Point(x, y);
	}
	public Point getPosition() {
		return this.position;
	}
	
	public abstract void paint (Graphics g); 
		
}
