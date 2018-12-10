import java.awt.Graphics;
import java.awt.Color;

public class Apple extends GameItem {
	private static int nextValue = 1;
	private int value = 0;

	public Apple(int x, int y) {
		super(x, y);
		final int value = this.value + nextValue;
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
