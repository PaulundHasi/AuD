import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class SnakeGame extends AudGameWindow {

	private int width;
	private int height;
	private int score;
	public static final int SQUARE_SIZE = 16;
	private Snake snake;
	public static final int STEP_TIME = 90;
	private long lastSnakeUpdate;
	private Brick[] wall;
	public static final int GROW_AMOUNT = 1;
	private Apple apple;

	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(4, width / 2, height / 2);
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

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();

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
			}else if (snake.collidesWith(this.apple)){
				snake.grow(GROW_AMOUNT);
			} else {
				continue;
			}
		}
	}

	private void createNewApple() {
		int x = (int) (Math.random() * width);
		int y = (int) (Math.random() * height);
		for (int i = 0; i < snake.points.length; i++) {
			while (snake.points[i].getX() == x && snake.points[i].getY() == y) {
				x = (int) (Math.random() * width);
				y = (int) (Math.random() * height);
			}
		}
	}

}
