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

}
