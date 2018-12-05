import java.awt.Graphics;

public class SnakeGame extends AudGameWindow {

	private int width;
	private int height;
	private int score;
	public static final int SQUARE_SIZE = 16;
	private Snake snake;

	public SnakeGame() {
		this.score = 0;
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.height = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(1, width /2, height / 2);
	}

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();
	}

	@Override
	public void updateGame(long time) {

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
