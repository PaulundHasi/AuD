import java.awt.Graphics;

public class SnakeGame extends AudGameWindow {

	private int width;
	private int height;
	private int score;
	public static final int SQUARE_SIZE = 16;
	private static Snake snake;
	public static final int STEP_TIME = 100;
	private long lastSnakeUpdate;

	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(1, width /2, height / 2);
		this.lastSnakeUpdate = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();
		snake.step();
	}

	@Override
	public void updateGame(long time) {
	/*for (int i = 0; i <= (time-lastSnakeUpdate);i++) {
		if (System.currentTimeMillis() == STEP_TIME)
		lastSnakeUpdate = lastSnakeUpdate + STEP_TIME;
		}*/
	}
	@Override
	public void paintGame(Graphics g) {
		g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
		snake.paint(g);
	}

	@Override
	public void handleInput(int keyCode) {

	}

}
