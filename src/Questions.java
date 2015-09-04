import static stdlib.StdDraw.*;
import java.awt.Color;
import javax.xml.soap.Node;

/** GUI for the Questions game. */
public class Questions {

	/** Radius of circles drawn for nodes. */
	public static final double CIRCLE_RADIUS = 0.01;

	public static void main(String[] args) {
		new Questions().run();
	}

	/** The root of the tree of knowledge. */
	public Node root;

	/** Question or instructions for the user. */
	private String label;

	/** Current node in the decision tree. */
	private Node node;

	/** The text currently being entered by the user. */
	private String textBeingEntered;

	public Questions() {
		root = new Node("Does it have a motor?",
				new Node("Does it store information?", new Node("a hard drive"), new Node("a car")),
				new Node("a giraffe"));
		node = root;
		label = "";
		textBeingEntered = "";
	}

	/** Draws the state of the game. */
	protected void draw() {
		clear();
		text(0.5, 0.9, label);
		text(0.5, 0.8, textBeingEntered);
		double yDecrement = 0.5 / (root.height() + 1);
		drawSubtree(root, 0.5 - yDecrement / 2, yDecrement, 0.0, 1.0);
		show(0);
	}

	/**
	 * Draws the subtree rooted at node.
	 * 
	 * @param y
	 *            y coordinate at which to draw node.
	 * @param yDecrement
	 *            amount to decrease y coordinate for each level of the tree.
	 * @param left
	 *            left boundary of drawing space for this subtree.
	 * @param right
	 *            right boundary of drawing space for this subtree.
	 */
	protected void drawSubtree(Node node, double y, double yDecrement, double left, double right) {

		double x = (left + right) / 2;

		circle(x, y, 0.01);
		if (node.left != null) {
			drawSubtree(node.left, y - yDecrement, yDecrement, left, x);
		}
		if (node.right != null) {
			drawSubtree(node.right, y - yDecrement, yDecrement, x, right);

		}
		if (node == this.node) {
			setPenColor(Color.red);
			filledCircle(x, y, 0.01);
		}
		setPenColor(Color.black);
	}

	/**
	 * Reads a String from the user, displaying it as they type and allowing
	 * backspaces.
	 */
	protected String readString() {

		while (true) {
			draw();
			char pressed = waitForKey();
			if (pressed == '\n') {
				String result = textBeingEntered;
				textBeingEntered = "";
				return result;
			} else if (pressed == '\b') {
				textBeingEntered = textBeingEntered.substring(0, textBeingEntered.length() - 1);
			} else {
				textBeingEntered += pressed;
			}
		}
	}

	/** Plays games until the user chooses to quit. */
	public void run() {
		while (true) {
			// Descend through the tree to a leaf
			node = root;
			while (!node.isLeaf()) {
				label = node.getKey() + " (y/n)";
				draw();
				if (waitForKey() == 'y') {
					node = node.getLeft();
				} else {
					node = node.getRight();
				}
			}
			// Now node is a leaf; handle end of game
			label = "Is it " + node.getKey() + "? (y/n)";
			draw();
			char pressed = waitForKey();
			if (pressed == 'y') {
				label = "I win! Would you like to play again? (y/n)";
			} else if (pressed == 'n') {
				label = "I give up! What was it?";
				String correct = readString();
				label = "What question would distinguish " + correct + " (y) + from " + node.getKey() + " (n)?";
				String question = readString();
				node.learn(correct, question);
				node = node.getLeft();
				label = "Would you like to play again? (y/n)";
			}
			draw();
			if (waitForKey() != 'y') {
				System.exit(0);
			}
		}
	}

	/** Waits for the user to press a key, then returns that character. */
	protected char waitForKey() {
		while (!hasNextKeyTyped()) {
			// Wait for keypress
		}
		return nextKeyTyped();
	}

}
