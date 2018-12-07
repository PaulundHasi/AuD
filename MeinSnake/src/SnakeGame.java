import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class SnakeGame extends AudGameWindow {

	private int width;
	private int height;
	public static final int SQUARE_SIZE = 16;
	private int score = 0;
	private static Snake snake;
	public static final int STEP_TIME = 100;
	private long lastSnakeUpdate;
	private Point[] wall;

	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(width / 2, height / 2, 5);
		this.lastSnakeUpdate = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();
		//snake.step();
	}

	@Override
	public void updateGame(long time) {
		long stepTime = (time - lastSnakeUpdate);
		int step = (int) (stepTime/ STEP_TIME);
		for (int i = 0; i < step; i++) {
			snake.step();
			this.lastSnakeUpdate += STEP_TIME;
		}
	}

	@Override
	public void paintGame(Graphics g) {
		g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
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

}
