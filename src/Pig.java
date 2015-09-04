import static stdlib.StdIn.*;

import static stdlib.StdOut.*;

import static stdlib.StdRandom.*;

public class Pig {

	public static void main(String[] args) {
		int roll, i = 1, player = 0, player0TotalScore = 0;
		int player1TotalScore = 0, playerTurnScore = 0, winningPoint = 100;

		println("Score: 0 - 0");

		while ((player0TotalScore <= winningPoint) && (player1TotalScore <= winningPoint)) {

			println("Keep going (y/n)?");

			if (readLine().equals("y")) {

				roll = uniform(6) + 1;

				if (roll != 1) {

					playerTurnScore = playerTurnScore + roll;

					println("Player " + player + "," + "you rolled a " + " " + roll + ".");

					println("you have " + playerTurnScore + " " + "points.");

				}
				if (roll == 1) {
					println("Player" + player + "," + "you rolled a " + roll);
					playerTurnScore = 0;

					println("Score: " + " " + player0TotalScore + " - " + player1TotalScore);

					i = -1 * i;
					if (i < 0) {
						player = 1;

					}

					else {
						player = 0;

					}

				}
			}

			else {
				if (player == 0) {
					player0TotalScore = player0TotalScore + playerTurnScore;

					println("Score:" + " " + player0TotalScore + " - " + player1TotalScore);

				} else {

					player1TotalScore = player1TotalScore + playerTurnScore;

					println("Score" + " " + player0TotalScore + " - " + player1TotalScore);

					playerTurnScore = 0;
				}

				i = -1 * i;

				if (i < 0) {

					player = 1;

				} else {

					player = 0;

				}

				playerTurnScore = 0;

			}

		}
		if (player0TotalScore >= 100) {

			println("Game over. Final score:" + player0TotalScore + "-" + player1TotalScore + ".");

		} else if (player1TotalScore >= 100) {
			println("Game over. Final score:" + player1TotalScore + "-" + player0TotalScore + ".");
		}

	}

}
public class Pig {

}
