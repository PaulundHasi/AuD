
public class Point extends SnakeGame {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x * SQUARE_SIZE;
		this.y = y * SQUARE_SIZE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
