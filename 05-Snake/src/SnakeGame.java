import java.awt.Graphics;
import java.awt.Color;

public class SnakeGame extends AudGameWindow {

	public static void main(String[] args) {
		SnakeGame one = new SnakeGame();
		one.start();
	}
	public int width;
	public int height;
	public static final int SQUARE_SIZE = 16;

	@Override
	public void updateGame(long time) {

	}

	@Override
	public void paintGame(Graphics g) {
		g.drawRect(0, 0,getGameAreaWidth(),getGameAreaHeight());
		
	}

	@Override
	public void handleInput(int keyCode) {

	}
	public SnakeGame () {
		setTitle ("AuD-Snake - Score: 0");
		this.width = getGameAreaWidth()/SQUARE_SIZE;
		this.height = getGameAreaHeight ()/SQUARE_SIZE;
	}

}
