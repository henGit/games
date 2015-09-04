
import static stdlib.StdRandom.*;

import static stdlib.StdDraw.*;

import java.io.*;

import java.util.*;

import stdlib.StdDraw;

public class Hangman {

	public static boolean complete(char[] known) {

		int i = 0;
		while (i < known.length) {
			if (known[i] == '_') {
				return false;
			} else {
				i++;
			}
		}
		return true;
	}

	public static void draw(int guesses, char[] known, String word) {

		setXscale(0, 9);
		setYscale(0, 8);
		line(1, 2, 4, 2);
		line(2.5, 2, 2.5, 7);
		line(2.5, 7, 4, 7);
		line(4, 7, 4, 6);

		String newWord = new String();
		int i = 0;
		while (i < known.length) {
			newWord += " " + known[i];
			i++;
		}
		text(6.5, 6, newWord);
		if (!complete(known)) {
			if (guesses == 5) {
				circle(4, 5.5, 0.5);
			}
			if (guesses == 4) {
				line(4, 5, 4, 4);
			}
			if (guesses == 3) {
				line(4, 5, 3, 5);
			}
			if (guesses == 2) {
				line(4, 5, 5, 5);
			}
			if (guesses == 2) {
				line(4, 4, 3.5, 3);
			}
			if (guesses == 1) {
				line(4, 4, 4.5, 3);
				text(6, 3, "Game over");
				text(6, 2.5, "You lost the game");
				text(6.5, 4, "The right word is: " + word);
			}
			if (guesses != 0) {
				text(2, 1.5, String.valueOf(guesses) + " guesses left");
			}
		} else {
			text(4, 1.5, "Game over");
			text(4, 1, "Congratulations! You won the game");
		}
	}

	public static boolean found(char letter, String word, char[] known) {

		boolean correct = false;

		int i = 0;

		while (i < known.length) {
			if (word.charAt(i) == letter) {
				known[i] = letter;
				correct = true;
			}
			i++;
		}
		if (correct == true) {
			return correct;
		} else {
			return false;
		}
		// return false;
	}

	public static String randomWord(String[] dictionary) {

		int amount = dictionary.length;

		int random = uniform(amount);

		return dictionary[random];

	}

	public static void main(String[] args) {

		String[] dictionary = readDictionary();

		String word = randomWord(dictionary);

		int guesses = 6;

		char[] known = new char[word.length()];

		for (int i = 0; i < known.length; i++) {

			known[i] = '_';

		}

		while (guesses > 0 && !complete(known)) {

			draw(guesses, known, word);

			while (!hasNextKeyTyped()) {

				// Wait for keypress

			}

			char letter = nextKeyTyped();

			if (!found(letter, word, known)) {

				guesses--;

			}

		}

		draw(guesses, known, word);

	}

	public static String[] readDictionary() {

		try {

			Scanner input = new Scanner(new File("enable1.txt"));

			ArrayList<String> dictionary = new ArrayList<String>();

			while (input.hasNextLine()) {

				String word = input.nextLine();

				if (word.equals(word.toLowerCase())) {

					dictionary.add(word);

				}

			}

			return dictionary.toArray(new String[dictionary.size()]);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

			System.exit(1);

			return null;

		}

	}

}