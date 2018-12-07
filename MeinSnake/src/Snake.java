import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	public Point[] points = null;
	private Color color = Color.blue;
	private Direction nextDirection = Direction.UP;
	private Direction lastDirection;

	public Snake(int x, int y, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("length muss eine positive Zahl sein!");
		}
		Point p = new Point(x, y);
		this.points = new Point[length];
		this.points[0] = p;

	}

	public Snake(int x, int y) {
		this(x, y, 5);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				continue;
			} else {
				g.fillRect(points[i].getX() * SnakeGame.SQUARE_SIZE, points[i].getY() * SnakeGame.SQUARE_SIZE,
						SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
			}
		}
	}

	public enum Direction {
		RIGHT, DOWN, LEFT, UP
	}

	public Direction getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(Direction direction) {
		step();
		if ((direction == Direction.RIGHT && lastDirection == Direction.LEFT)
				|| (direction == Direction.UP && lastDirection == Direction.DOWN)) {
			nextDirection = lastDirection;
		} else if ((direction == Direction.LEFT && lastDirection == Direction.RIGHT)
				|| (direction == Direction.DOWN && lastDirection == Direction.UP)) {
			nextDirection = lastDirection;
		} else {
			this.nextDirection = direction;
		}
	}

	public void step() {
		Point help = new Point(points[0].getX(), points[0].getY());
		System.arraycopy(points, 0, points, 1, points.length - 1);
		switch (nextDirection) {
		case RIGHT:
			points[0] = new Point(help.getX() + 1, help.getY());
			this.lastDirection = Direction.RIGHT;
			break;
		case DOWN:
			points[0] = new Point(help.getX(), help.getY() + 1);
			this.lastDirection = Direction.DOWN;
			break;
		case LEFT:
			points[0] = new Point(help.getX() - 1, help.getY());
			this.lastDirection = Direction.LEFT;
			break;
		case UP:
			points[0] = new Point(help.getX(), help.getY() - 1);
			this.lastDirection = Direction.UP;
		}
	}

	public boolean collidesWith(GameItem item) {
		
	}

	public boolean collidesWith(int x, int y) {

	}
}
