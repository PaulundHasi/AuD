import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class SnakeGame extends AudGameWindow {

	// Attribute:
	private int width;
	private int height;
	public static final int SQUARE_SIZE = 16;
	private int score = 0;
	private Snake snake;
	public static final int STEP_TIME = 100;
	private long lastSnakeUpdate;
	private Brick[] wall;
	public static SnakeGame game = new SnakeGame();
	public Apple apple;

	// Konstruktor:
	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(width / 2, height / 2, 20);
		this.lastSnakeUpdate = System.currentTimeMillis();
		this.wall = new Brick[((2 * width) + (2 * height)) - 4];
		int anzahl = 0;
		for (int i = 0; i < width; i++) {
			wall[anzahl++] = new Brick(i, 0);
			wall[anzahl++] = new Brick(i, height - 1);
		}
		for (int a = 1; a < height - 1; a++) {
			wall[anzahl++] = new Brick(0, a);
			wall[anzahl++] = new Brick(width - 1, a);
		}
	}

	// Main-Methode:
	public static void main(String[] args) {
		game.start();
		game.createNewApple();

	}

	@Override
	public void updateGame(long time) {
		long stepTime = (time - lastSnakeUpdate);
		int step = (int) (stepTime / STEP_TIME);
		for (int i = 0; i < step; i++) {
			snake.step();
			//checkCollisions();
			this.lastSnakeUpdate += STEP_TIME;
		}
	}

	@Override
	public void paintGame(Graphics g) {
		g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
		for (int i = 0; i < wall.length; i++) {
			wall[i].paint(g);
		}
		Apple a = new Apple(14, 16);
		a.paint(g);
		snake.paint(g);
	}

	@Override
	public void handleInput(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_RIGHT:
			snake.setNextDirection(Snake.Direction.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			snake.setNextDirection(Snake.Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.setNextDirection(Snake.Direction.LEFT);
			break;
		case KeyEvent.VK_UP:
			snake.setNextDirection(Snake.Direction.UP);
			break;
		}
	}

	@SuppressWarnings("static-access")
	private void checkCollisions() {
		for (int i = 0; i < wall.length; i++)
			if (snake.collidesWith(wall[i]) == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this.game, "You died! Score: " + score);
				break;
			} else if (snake.collidesWithSelf() == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this.game, "You died! Score: " + score);
				break;
			}else {
				continue;
			}
	}
	private void createNewApple() {
		int x = (int) (Math.random()*width);
		int y = (int) (Math.random()*height);
		for (int i = 0; i < snake.points.length; i++) {
			while (x == snake.points[i].getX() && y == snake.points[i].getY()) {
				x = (int) (Math.random()*SQUARE_SIZE);
				y = (int) (Math.random()*SQUARE_SIZE);
			}
		}
		this.apple = new Apple(x, y);
	}

}
