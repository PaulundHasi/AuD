import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	public Point[] points = null;
	private Color color = Color.blue;
	private Direction nextDirection = Direction.UP;

	public Snake(int length, int x, int y) {
		if (length < 0) {
			throw new IllegalArgumentException("length muss eine positive Zahl sein");
		} else {
			Point start = new Point(x, y);
			this.points = new Point[length];
			this.points[0] = start;
		}
	}

	public Snake(int x, int y) {
		this(5, x, y);
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

	public enum Direction {
		RIGHT, DOWN, LEFT, UP
	}

	public void setnextDirection(Direction direction) {
		this.nextDirection = direction;
	}

	public Direction getnextDirection() {
		return nextDirection;
	}

	public void step() {
		Point help = new Point(points[0].getX(), points[0].getY());
		System.arraycopy(points, 0, points, 1, points.length - 1);
		switch (nextDirection) {
		case RIGHT:
			points[0] = new Point(help.getX()+SnakeGame.SQUARE_SIZE, help.getY());
			break;
		case DOWN:
			points[0] = new Point(help.getX(), help.getY()-SnakeGame.SQUARE_SIZE);
			break;
		case LEFT:
			points[0] = new Point(help.getX()-SnakeGame.SQUARE_SIZE, help.getY());
			break;
		case UP:
			points[0] = new Point(help.getX(), help.getY()-SnakeGame.SQUARE_SIZE);
		}
	}

}
