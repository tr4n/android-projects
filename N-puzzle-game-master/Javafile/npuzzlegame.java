
import java.util.*;

public class npuzzlegame {

	public static Scanner in = new Scanner(System.in);
	public static Random random = new Random();
	public static int HEIGHT = 4;
	public static int WIDTH = 4;
	public static int[][] puzzles = new int[15][15];

	public static int[] directX = { 0, -1, 0, 1 }; // LEFT, UP, RIGHT, DOWN
	public static int[] directY = { 1, 0, -1, 0 };
	public static int GO_LEFT = 0, GO_UP = 1, GO_RIGHT = 2, GO_DOWN = 3;
	public static SpecialPuzzle emptyPuzzle;
	public static Node targetNode, startNode;
	public static HashSet<Node> nodeHashSet = new HashSet<Node>();
	public static int RANDOM_TIMES = 100;
	public static HashMap<Node, Node> parentMap = new HashMap<Node, Node>();

	public static int[][] getNewTable() {
		int[][] newTable = new int[10][10];

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				newTable[i][j] = i * WIDTH + j;
			}
		}

		return newTable;

	}

	public static int[][] getRandomTable() {
		int[][] randomTable = getNewTable();

		int countTimes = 0;
		int currentX = 0;
		int currentY = 0;
		while (countTimes < RANDOM_TIMES) {
			int dir = random.nextInt(4);
			int newX = currentX - directX[dir];
			int newY = currentY - directY[dir];
			if (newX < 0 || newX >= HEIGHT || newY < 0 || newY >= WIDTH)
				continue;
			countTimes++;

			randomTable[currentX][currentY] = randomTable[newX][newY];
			randomTable[newX][newY] = 0;
			currentX = newX;
			currentY = newY;

		}

		return randomTable;
	}

	public static boolean checkPositionXY(int X, int Y) {
		return (X >= 0 && X < HEIGHT && Y >= 0 && Y < WIDTH);
	}

	public static int[][] copyTable(int[][] table) {
		if (table == null)
			return null;

		int[][] newTable = new int[10][10];

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				newTable[i][j] = table[i][j];
			}
		}

		return newTable;
	}

	public static int[][] getNewState(int[][] table, int dir) {

		int currentX = 0, currentY = 0;

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {

				if (table[i][j] == 0) {
					currentX = i;
					currentY = j;
					break;
				}
			}
		}

		int newX = currentX - directX[dir];
		int newY = currentY - directY[dir];

		if (!checkPositionXY(newX, newY))
			return null;

		int[][] newState = copyTable(table);
		newState[currentX][currentY] = newState[newX][newY];
		newState[newX][newY] = 0;
		currentX = newX;
		currentY = newY;
		return newState;
	}

	public static void showTable(int[][] table) {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.format("%3d", table[i][j]);
			}
			System.out.println();
		}
	}

	public static String getStringTable(int[][] table) {

		String string = "";
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				String formatString = String.format("%4d", table[i][j]);
				string += formatString;
			}
			string += "\n";
		}

		return string;
	}

	public static String getHashStringState(int[][] state) {
		String string = "";
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				string += ("-" + String.valueOf(state[i][j]));
			}
		}

		return string;
	}

	public static void Initialization() {
		puzzles = getRandomTable();
		System.out.println(" BEGIN : \n" + getStringTable(puzzles));

		targetNode = new Node(getNewTable());
		startNode = new Node(puzzles);

	}

	public static long getValueState(int[][] state) {

		long value = 0;
		long num = 0;

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {

				if (state[i][j] != 0) {

					if (state[i][j] != i * WIDTH + j) {
						num++;
						int line = state[i][j] / WIDTH;
						int col = state[i][j] % WIDTH;
						value += (Math.abs(i - line) + Math.abs(j - col));
						if (line + col > 1)
							value += (Math.max(Math.abs(i - line), Math.abs(j - col)));

					}
				}

			}
		}

		value = 10 * value + num;
		return value;
	}

	public static Node getNode(int[][] state) {

		return (new Node(getHashStringState(state), getValueState(state)));
	}

	public static boolean Visit(Node node) {

		int numberNodes = 0;

		PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
		HashSet<String> hashSet = new HashSet<String>();

		if (node.isEquals(targetNode)) {
			System.out.println("found");

			return true;
		}

		priorityQueue.add(node);
		hashSet.add(node.name);
		node.preNode = null;

		while (!priorityQueue.isEmpty()) {

			Node currentNode = priorityQueue.remove();
			// System.out.println("Visit " + (++numberNodes) + ":\n" + (currentNode.name) +
			// " " + currentNode.value);

			// System.out.println("Visit " + (++numberNodes) + ":\n" +
			// getStringTable(currentNode.state));

			if (currentNode.value == 0) {
				System.out.println("found");
				trace(currentNode, 0);

				return true;
			}

			// if(hashSet.contains(currentNode.name )) continue;

			ArrayList<Node> nodeList = currentNode.getNodeDirrect();

			for (Node subNode : nodeList) {
				// System.out.println("+++ \n" + getStringTable(subNode.state));
				if (!hashSet.contains(subNode.name)) {
					priorityQueue.add(subNode);
					hashSet.add(subNode.name);
					subNode.preNode = currentNode;

				}
			}

			// System.out.println(priorityQueue);

		}

		System.out.println("No thing");

		return false;

	}

	private static void trace(Node node, int id) {
		// TODO Auto-generated method stub

		if (node.preNode == null)
			return;
		System.out.println(id + ": \n" + getStringTable(node.state));
		trace(node.preNode, id + 1);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Initialization();

		System.out.println(
				getHashStringState(getNewTable()) + "\n" + getValueState(getNewTable()) + "\n" + getValueState(puzzles)

		);

		Visit(startNode);

		System.out.println(getStringTable(startNode.state) + "\n-----");

	}

}
