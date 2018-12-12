import java.awt.Graphics;
import java.awt.Color;

public class Apple extends GameItem {
	private final int value ;
	private static int nextValue = 0;

	public Apple(int x, int y) {
		super(x, y);
		value = nextValue++;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((getPosition().getX()) * SnakeGame.SQUARE_SIZE, (getPosition().getY()) * SnakeGame.SQUARE_SIZE,
				SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
	}

	public int getValue() {
		return value;
	}

}
