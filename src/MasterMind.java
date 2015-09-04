
//package mastermind;

import static stdlib.StdDraw.*;

import java.awt.Color;

public class Mastermind {

	public final Color VIOLET = new Color(102, 0, 153);

	public static void main(String[] args) {
		new Mastermind().run();
	}

	public static final int ROWS = 10;

	public static final int COLUMNS = 4;

	public Mastermind() {
		board = new Color[4][10];
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				board[i][j] = WHITE;
			}
		}
	}

	private Game game;

	private Color[][] board;

	public void clear() {
		setPenColor(WHITE);
		for (int i = 0; i <= 10; i++) {
			filledSquare(i, 11, 1.5);
		}
	}

	public void run() {
		game = new Game();
		draw();
		while ((!game.isLost()) && (!game.isWon())) {
			String guess = makeGuess();
			game.guess(guess);
			if (game.isLost()) {
				clear();
				setPenColor(BLACK);
				text(3, 10, "you lost!");
				showCorrect();
			}
			if (game.isWon()) {
				clear();
				setPenColor(StdDraw.BLACK);
				text(3, 10, "you won!");
			}
		}
	}

	public void draw() {
		System.out.println("jj");
		clear();
		setXscale(-4, 12);
		setYscale(-4, 12);
		setPenColor(BLACK);
		text(3, 11, "to play, press keys");
		setPenColor(BLUE);
		text(3, 10, "'r', 'o', 'y', 'g', 'b' and 'v'");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				setPenColor(board[i][j]);
				filledCircle(i, j, .45);
				if (board[i][j] != WHITE) {
					setPenColor(BLACK);

					circle(i, j, .50);
				}
			}
		}
	}

	public String makeGuess() {
		char[] colors = new char[4];
		for (int i = 0; i < 4; i++) {
			while (!hasNextKeyTyped()) {
			}
			colors[i] = nextKeyTyped();
			if (colors[i] == 'r') {
				board[i][9 - game.getNumberOfGuessesMade()] = RED;
			} else if (colors[i] == 'o') {
				board[i][9 - game.getNumberOfGuessesMade()] = ORANGE;
			} else if (colors[i] == 'y') {
				board[i][9 - game.getNumberOfGuessesMade()] = YELLOW;
			} else if (colors[i] == 'g') {
				board[i][9 - game.getNumberOfGuessesMade()] = GREEN;
			} else if (colors[i] == 'b') {
				board[i][9 - game.getNumberOfGuessesMade()] = BLUE;
			} else if (colors[i] == 'v') {
				board[i][9 - game.getNumberOfGuessesMade()] = VIOLET;
			}
			draw();
		}
		int black = game.getNumberOfBlackPegs(new String(colors));
		setPenColor(BLACK);
		filledCircle(6, 9 - game.getNumberOfGuessesMade(), .5);
		setPenColor(WHITE);
		text(6, 9 - game.getNumberOfGuessesMade(), String.valueOf(black));

		int white = game.getNumberOfWhitePegs(new String(colors));
		setPenColor(BLACK);
		circle(7, 9 - game.getNumberOfGuessesMade(), .5);
		setPenColor(BLACK);
		text(7, 9 - game.getNumberOfGuessesMade(), String.valueOf(white));
		return new String(colors);
	}

	public void showCorrect() {
		String correct = game.getCorrect();
		setPenColor(BLACK);
		text(4, -2, "Here's the code.");
		for (int i = 0; i < 4; i++) {
			if (correct.charAt(i) == 'r') {
				setPenColor(RED);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
			if (correct.charAt(i) == 'o') {
				setPenColor(BLUE);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
			if (correct.charAt(i) == 'y') {
				setPenColor(YELLOW);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
			if (correct.charAt(i) == 'g') {

				setPenColor(GREEN);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
			if (correct.charAt(i) == 'b') {
				setPenColor(BLUE);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
			if (correct.charAt(i) == 'v') {
				setPenColor(VIOLET);
				filledCircle(4 + i, -3, .5);
				setPenColor(BLACK);
				circle(4 + i, -3, .5);
			}
		}
	}
}

public class MasterMind {

}
