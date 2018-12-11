import java.awt.Graphics;
import java.awt.Color;

public class Apple extends GameItem {
	private static int nextValue = 0;
	private final int value;

	public Apple(int x, int y) {
		super(x, y);
		value = nextValue++;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(getPosition().getX() * SnakeGame.SQUARE_SIZE, getPosition().getY() * SnakeGame.SQUARE_SIZE,
				SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
	}

	public int getValue() {
		return this.value;
	}
}
