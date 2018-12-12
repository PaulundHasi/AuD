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
	public static final int STEP_TIME = 150;
	private long lastSnakeUpdate;
	private Brick[] wall;
	public SnakeGame game;
	private Apple apple;
	public static final int GROW_AMOUNT = 1;

	// Konstruktor:
	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(width / 2, height / 2, 5);
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
		createNewApple();
	}

	// Main-Methode:
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.start();

	}

	@Override
	public void updateGame(long time) {
		long stepTime = (time - lastSnakeUpdate);
		int step = (int) (stepTime / STEP_TIME);
		for (int i = 0; i < step; i++) {
			snake.step();
			checkCollisions();
			this.lastSnakeUpdate += STEP_TIME;
		}
	}

	@Override
	public void paintGame(Graphics g) {
		g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
		for (int i = 0; i < wall.length; i++) {
			wall[i].paint(g);
		}
		snake.paint(g);
		apple.paint(g);
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

	private void checkCollisions() {
		for (int i = 0; i < wall.length; i++)
			if (snake.collidesWith(wall[i]) == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this, "You died! Score: " + score);
				break;
			} else if (snake.collidesWithSelf() == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this, "You died! Score: " + score);
				break;
			} else if (snake.collidesWith(this.apple) == true) {
				snake.grow(GROW_AMOUNT);
				createNewApple();
				this.score += this.apple.getValue();
				setTitle("AuD-Snake - Score: " + score);
			} else {
				continue;
			}
	}

	private void createNewApple() {
		int x = (int) ((Math.random() * (width - 2))+1);
		int y = (int) ((Math.random() * (height - 2))+1);
		for (int i = 0; i < this.wall.length; i++) {
			if (wall[i] != null) {
				while (snake.collidesWith(x, y) == true
						&& (wall[i].getPosition().getX() == x && wall[i].getPosition().getY() == y)) {
					x = (int) ((Math.random() * (width - 2))+1);
					y = (int) ((Math.random() * (height - 2))+1);

				}
			}
		}
		this.apple = new Apple(x, y);
	}

}
