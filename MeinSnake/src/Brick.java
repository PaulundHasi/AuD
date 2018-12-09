import java.awt.Graphics;
import java.awt.Color;

public class Brick extends GameItem{

	public Brick(int x, int y) {
		super(x, y);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(getPosition().getX()*SnakeGame.SQUARE_SIZE, getPosition().getY()*SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);	
	}
}
