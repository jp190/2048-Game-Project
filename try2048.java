
/*Done by Yan Jiang and Jungmin Park
 * 
 */
import java.util.Random;
import java.util.Scanner;

public class try2048 {

	static int moves;
	int[][] array = new int[4][4];
	Random rand = new Random();
	boolean GoOn = true;

	public void direction(String d) {

		// left
		if (d.equals("a")) {
			pushL();
			addL();
			pushL();

			// RIGHT
		} else if (d.equals("d")) {
			pushR();
			addR();
			pushR();

			// Up
		} else if (d.equals("w")) {

			pushU();
			addU();
			pushU();

			// down
		} else if (d.equals("s")) {
			pushD();
			addD();
			pushD();

		}

	}

	// method addU sums the matrix to the top
	public void addU() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {

				if (array[j][i] == array[j + 1][i] && array[j][i] != 0 && array[j + 1][i] != 0) {
					array[j][i] = array[j][i] + array[j + 1][i];
					array[j + 1][i] = 0;
					break;
				}
			}
		}
		pushU();

	}

	// method addD sums the matrix to the bottom
	public void addD() {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > 0; j--) {

				if (array[j][i] == array[j - 1][i] && array[j][i] != 0 && array[j - 1][i] != 0) {
					array[j][i] = array[j - 1][i] + array[j][i];
					array[j - 1][i] = 0;
					break;
				}
			}
		}

	}

	// method addL sums the matrix to the left
	public void addL() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j] == array[i][j + 1] && array[i][j] != 0 && array[i][j + 1] != 0) {
					array[i][j] = array[i][j] + array[i][j + 1];
					array[i][j + 1] = 0;
					break;
				}
			}
		}

	}

	// method addR sums the matrix to the right
	public void addR() {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > 0; j--) {

				if (array[i][j] == array[i][j - 1] && array[i][j] != 0 && array[i][j - 1] != 0) {
					array[i][j] = array[i][j] + array[i][j - 1];
					array[i][j - 1] = 0;
					break;
				}
			}
		}

	}

	// method pushL eliminates empty tiles between values in direction of left
	public void pushL() {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > 0; j--) {
				if (array[i][j] != 0 && array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;
					for (int k = j; k < array.length - 1; k++) {
						if (array[i][k] == 0 && array[i][k + 1] != 0) {

							array[i][k] = array[i][k + 1];
							array[i][k + 1] = 0;
						}
					}
				}

			}
		}
	}

	// method pushR eliminates empty tiles between values in direction of right
	public void pushR() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j] != 0 && array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;
					for (int k = array.length - 1; k > 0; k--) {
						if (array[i][k] == 0 && array[i][k - 1] != 0) {
							array[i][k] = array[i][k - 1];
							array[i][k - 1] = 0;
						}
					}
				}
			}
		}
	}

	public void pushD() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j][i] != 0 && array[j + 1][i] == 0) {
					array[j + 1][i] = array[j][i];
					array[j][i] = 0;
					for (int k = 0; k < array.length - 1; k++) {
						if (array[k][i] != 0 && array[k + 1][i] == 0) {
							array[k + 1][i] = array[k][i];
							array[k][i] = 0;
						}
					}
				}
			}
		}
	}

	public void pushU() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j][i] == 0 && array[j + 1][i] != 0) {
					array[j][i] = array[j + 1][i];
					array[j + 1][i] = 0;
					for (int k = array.length - 1; k > 0; k--) {
						if (array[k][i] != 0 && array[k - 1][i] == 0) {
							array[k - 1][i] = array[k][i];
							array[k][i] = 0;
						}
					}
				}
			}
		}
	}

	// method addRandomNumber generates 2 (2 and 4 show up in ratio 4:1)numbers and
	// randomly place them on the where is empty on the board
	public void addRandomNumber() {

		int numgenerated;
		int x = rand.nextInt(10);
		if (x == 8 || x == 9)
			numgenerated = 4;
		else
			numgenerated = 2; // generate 2 or 4's in ratio of 0.8:0.2

		int row = rand.nextInt(4);
		int column = rand.nextInt(4);

		while (array[row][column] != 0) {
			row = rand.nextInt(4);
			column = rand.nextInt(4);
		}

		array[row][column] = numgenerated;

	}

	// method printBoard prints out the board in format
	public void printBoard() {

		for (int t = 0; t < 6; t++)
			System.out.print("-\t");
		System.out.println();// print out the board top

		for (int i = 0; i < array.length; i++) {
			System.out.print("|\t"); // print out the left boarder of the board
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0) {
					System.out.print("*\t");
				} else {
					System.out.print(array[i][j] + "\t");
				}
				// show the empty element in array as *
			}
			System.out.print("|"); // right boarder
			System.out.println();
		}

		for (int b = 0; b < 6; b++)
			System.out.print("-\t"); // bottom of the board
		System.out.println();

	} // end of of printBoard method

	// method checkrow() checks if there are two equal consecutive numbers in the
	// same row
	public boolean checkrow() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j] == array[i][j + 1]) {
					return true;
				}
			}
		}

		return false;
	}

	// method checkcolumn() checks if there are two equal consecutive numbers in the
	// same column
	public boolean checkcolumn() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j][i] == array[j + 1][i]) {

					return true;
				}
			}
		}
		return false;
	}

	// method isZero() check if there is still empty tiles on the board
	public boolean isZero() {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	} // end of isZero method

	// method play() is the body method of this game
	public void play() {

		Scanner scan = new Scanner(System.in);
		String d = "";
		addRandomNumber();
		addRandomNumber();
		System.out.println("Game 2048!!");
		printBoard();
		System.out.println();
		System.out.println();
		System.out.println();

		while (GoOn != false) {

			if (d.equals("q")) {
				System.out.println("Do you want to quit(press 'q' again to confirm)?");
				d = scan.nextLine();
				if (d.equals("q")) {
					GoOn = false;
				}
				continue;
			} // double check whether the user wants to quit

			if (d.equals("r")) {
				System.out.println(
						"Press 'r' one more time to confirm your restart, any other keys to continue the game:");
				d = scan.nextLine();
				if (d.equals("r")) {
					System.out.println();
					play();
				}

			} else {
				System.out.println(
						"Key Instruction: press key 'w' for UP, 's' for DOWN, 'a' for LEFT, 'd' for RIGHT, 'q' to quit, and 'r' to restart");
				System.out.print("Enter the direction: ");
				d = scan.nextLine(); // take the direction from user

				direction(d); // call class Direction
				addRandomNumber();// generate 1 number for the next shift
				printBoard();

				int max = 0;
				max++;
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array.length; j++) {
						if (array[i][j] > max) {
							max = array[i][j];
						}

					}
				}

				moves += 1; // check the max on the board

				System.out.println("The max number on the board is " + max);
				System.out.println("Move counts: " + moves);

				System.out.println();
				System.out.println();
				System.out.println(); // leave space between each two moves

				if (max == 2048) {
					printBoard();
					System.out.println("Congratulation! You have completed the game.");
					break;
				} // when the max number on the board reaches 2048, the game ends

				if (d.equals("a") || d.equals("d")) {
					if (checkrow() == false && isZero() == false) {
						System.out.println("Game Over");
						GoOn = false;
					}
				} else if (d.equals("w") || d.equals("s")) {
					if (checkcolumn() == false && isZero() == false) {
						System.out.println("Game Over");
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();

						GoOn = false;
					}

				}

			}

		}

	}

	public static void main(String[] args) {
		try2048 game = new try2048();
		game.play();
	}

}
