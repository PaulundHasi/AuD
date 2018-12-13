import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class SnakeGame extends AudGameWindow {

	public static final int SQUARE_SIZE = 16;
	public static final int STEP_TIME = 100;
	public static final int GROW_AMOUNT = 5;
	private int width;
	private int height;
	private long lastSnakeUpdate;
	private int score;
	private Snake snake;
	private Brick[] wall;
	private Apple apple;

	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(width / 2, height / 2, 5);
		this.lastSnakeUpdate = System.currentTimeMillis();
		this.wall = new Brick[(width * 2 + height * 2) - 4];
		int amountBricks = 0;
		for (int i = 0; i < width; i++) {
			wall[amountBricks++] = new Brick(i, 0);
			wall[amountBricks++] = new Brick(i, height - 1);
		}
		for (int i = 1; i < height - 1; i++) {
			wall[amountBricks++] = new Brick(0, i);
			wall[amountBricks++] = new Brick(width - 1, i);
		}
		createNewApple();

	}

	@Override
	public void updateGame(long time) {
		long timeSteps = time - lastSnakeUpdate;
		int steps = (int) (timeSteps / STEP_TIME);
		for (int i = 0; i <= steps; i++) {
			snake.step();
			checkCollisions();
			lastSnakeUpdate += STEP_TIME;
		}

	}

	@Override
	public void paintGame(Graphics g) {
		g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
		snake.paint(g);
		for (int i = 0; i < wall.length; i++) {
			wall[i].paint(g);
		}
		apple.paint(g);
	}

	@Override
	public void handleInput(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_RIGHT:
			snake.setnextDirection(Snake.Direction.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			snake.setnextDirection(Snake.Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.setnextDirection(Snake.Direction.LEFT);
			break;
		case KeyEvent.VK_UP:
			snake.setnextDirection(Snake.Direction.UP);
			break;
		}
	}

	private void checkCollisions() {
		for (int i = 0; i < wall.length; i++) {
			if (snake.collidesWith(wall[i]) == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this, "You Died! Score: " + score);
				break;
			} else if (snake.collidesWithSelf() == true) {
				stop();
				javax.swing.JOptionPane.showMessageDialog(this, "You Died! Score: " + score);
				break;
			} else if (snake.collidesWith(this.apple) == true) {
				snake.grow(GROW_AMOUNT);
				createNewApple();
				score = score + apple.getValue();
				setTitle("AuD-Snake - Score: " + score);
			} else {
				continue;
			}
		}
	}

	private void createNewApple() {
		int x = (int) ((Math.random() * (width - 2)) + 1);
		int y = (int) ((Math.random() * (height - 2)) + 1);
		for (int i = 0; i < this.wall.length; i++) {
			if (wall[i] != null) {
				while (snake.collidesWith(x, y) == true
						&& (wall[i].getPosition().getX() == x && wall[i].getPosition().getY() == y)) {
					x = (int) ((Math.random() * (width - 2)) + 1);
					y = (int) ((Math.random() * (height - 2)) + 1);
				}
			}
		}
		apple = new Apple(x, y);
	}

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();
	}
}
