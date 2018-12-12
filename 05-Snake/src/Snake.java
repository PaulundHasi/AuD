import java.awt.Color;
import java.awt.Graphics;

public class Snake {

	private Direction nextDirection = Direction.UP;
	private Color color = Color.blue;
	private Direction lastDirection;
	private Point[] points = null;

	public enum Direction {
		RIGHT, DOWN, LEFT, UP
	}

	public Snake(int x, int y, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("length muss eine positive Zahl sein");
		} else {
			Point start = new Point(x, y);
			this.points = new Point[length];
			this.points[0] = start;
		}
	}

	public Snake(int x, int y) {
		this(x, y, 5);
	}

	public Direction getnextDirection() {
		return nextDirection;
	}

	public void setnextDirection(Direction direction) {
		if ((direction == Direction.RIGHT && lastDirection == Direction.LEFT)
				|| (direction == Direction.UP && lastDirection == Direction.DOWN)
				|| (direction == Direction.LEFT && lastDirection == Direction.RIGHT)
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
			lastDirection = Direction.RIGHT;
			break;
		case DOWN:
			points[0] = new Point(help.getX(), help.getY() + 1);
			lastDirection = Direction.DOWN;
			break;
		case LEFT:
			points[0] = new Point(help.getX() - 1, help.getY());
			lastDirection = Direction.LEFT;
			break;
		case UP:
			points[0] = new Point(help.getX(), help.getY() - 1);
			lastDirection = Direction.UP;
			break;
		}
	}

	public void grow(int amount) {
		if (amount < 1) {
			throw new IllegalArgumentException("amount muss eine positive Zahl sein");
		} else {
			Point[] help = new Point[points.length + amount];
			System.arraycopy(points, 0, help, 0, points.length);
			points = help;
		}
	}

	public boolean collidesWith(GameItem item) {
		return collidesWith(item.getPosition().getX(), item.getPosition().getY());
	}

	public boolean collidesWith(int x, int y) {
		boolean b = false;
		if (points[0].getX() == x && points[0].getY() == y) {
			b = true;
		} else {
			for (int i = 1; i < points.length; i++) {
				if (points[i] != null) {
					if (points[i].getX() == x && points[i].getY() == y) {
						b = true;
					} else {
						b = false;
					}
				}
			}
		}
		return b;
	}

	public boolean collidesWithSelf() {
		boolean help = false;
		for (int i = 1; i < points.length; i++) {
			if (points[i] != null) {
				if (points[0].getX() == points[i].getX() && points[0].getY() == points[i].getY()) {
					help = true;
					break;
				} else {
					help = false;
					continue;
				}

			}

		}
		return help;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				continue;
			} else {
				g.fillRect((points[i].getX()) * SnakeGame.SQUARE_SIZE, points[i].getY() * SnakeGame.SQUARE_SIZE,
						SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
			}
		}
	}
}
