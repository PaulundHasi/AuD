import java.awt.Color;
import java.awt.Graphics;

public class SnakeGame extends AudGameWindow {

	private int width;
	private int heigth;
	public static final int SQUARE_SIZE = 16;
	private int score = 0;
	public Snake snake;

	public SnakeGame() {
		setTitle("AuD-Snake - Score: " + score);
		this.width = getGameAreaWidth() / SQUARE_SIZE;
		this.heigth = getGameAreaHeight() / SQUARE_SIZE;
		this.snake = new Snake(width / 2, heigth / 1);
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
		g.drawRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
		snake.paint(g);
	}

	@Override
	public void handleInput(int keycode) {

	}
}
