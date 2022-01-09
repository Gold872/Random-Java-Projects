package adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Adventure {

	// Scanner to scan input commands
	static Scanner scn = new Scanner(System.in);

	static boolean inGame = true;

	// All of the possible commands. NULL is used when an invalid command is typed
	enum Commands {
		LEFT, RIGHT, UP, DOWN, GRAB, FIGHT, HELP, INVENTORY, NULL
	}

	static Commands command = Commands.NULL;

	enum Objects {
		SWORD, MONSTER, MAGIC_STONE, STAIRCASE, NULL
	}

	static Objects currentObject = Objects.NULL;

	static ArrayList<Objects> inventory = new ArrayList<Objects>();

	// Dimensions of the rooms
	static final int WIDTH = 11;
	static final int HEIGHT = 7;

	// Stores the player coordinates (x and y)
	static int[] playerCoordinates = new int[] { 9, 3 };

	/*
	 * The room gates store the position for the gates in each room number The array
	 * is x, y, and then the room number that it is going to
	 */
	static int[][] room1Gates = { { WIDTH - 1, HEIGHT / 2, 4 }, // Right Center
			{ WIDTH / 2, 0, 2 }, // Top Center
//			{WIDTH/2, HEIGHT - 1} //Bottom Center
	};

	static int[][] room2Gates = { { WIDTH - 1, HEIGHT / 2, 3 }, // Right Center
			{ WIDTH / 2, HEIGHT - 1, 1 } // Bottom Center
	};

	static int[][] room3Gates = { { 0, HEIGHT / 2, 2 }, // Left Center
			{ WIDTH / 2, HEIGHT - 1, 4 } // Bottom Center
	};
	static int[][] room4Gates = { { 0, HEIGHT / 2, 1 }, // Left Center
			{ WIDTH / 2, 0, 3 }, // Top Center
			{ WIDTH / 2, HEIGHT - 1, 5 } // Bottom Center
	};

	static int[][] room5Gates = { { WIDTH / 2, 0, 4 } // Top Center
	};

	static int[][] currentRoomGates = room1Gates;

	static Map<Integer, int[][]> roomGates = new HashMap<>();

	/*
	 * The format for these is x, y, room, floor (left blank if it's on all floors)
	 */
	static int[] swordLocation = { 5, 1, 2 };
	static int[] stairLocation = { 9, 1, 4 };
	static int[] stoneLocation = { 1, 5, 4, 2 };

	static int floorNumber = 1;
	static int roomNumber = 1;

	private static boolean containsGate(int x, int y) {
		for (int i = 0; i < currentRoomGates.length; i++) {
			if (currentRoomGates[i][0] == x && currentRoomGates[i][1] == y) {
				return true;
			}
		}
		return false;
	}

	private static void draw() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
					// If there isn't a room door it prints a wall
					if (!containsGate(j, i)) {
						System.out.print("#");
					} else { // If there is a door then it leaves an empty space
						System.out.print(" ");
					}
				} else if (i == playerCoordinates[1] && j == playerCoordinates[0]) {
					System.out.print("\u263A");
				} else if (i == swordLocation[1] && j == swordLocation[0] && roomNumber == swordLocation[2]) {
					System.out.print("S");
				} else if (i == stairLocation[1] && j == stairLocation[0] && roomNumber == stairLocation[2]) {
					System.out.print("/");
				} else if (i == stoneLocation[1] && j == stoneLocation[0] && roomNumber == stoneLocation[2]
						&& floorNumber == stoneLocation[3]) {
					System.out.print("\u2605");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	private static Objects collidingObject() {
		if (playerCoordinates[0] == swordLocation[0] && playerCoordinates[1] == swordLocation[1]
				&& roomNumber == swordLocation[2]) {
			return Objects.SWORD;
		}
		if (playerCoordinates[0] == stairLocation[0] && playerCoordinates[1] == stairLocation[1]
				&& roomNumber == stairLocation[2]) {
			return Objects.STAIRCASE;
		}
		if (playerCoordinates[0] == stoneLocation[0] && playerCoordinates[1] == stoneLocation[1]
				&& roomNumber == stoneLocation[2] && floorNumber == stoneLocation[3]) {
			return Objects.MAGIC_STONE;
		}

		return Objects.NULL;
	}

	/*
	 * This sets the current room to whatever room the gate entered leads to It then
	 * sets the gates for that room as well
	 */
	private static void goToNextRoom(int playerXCoordinate, int playerYCoordinate, int currentRoom) {
		roomGatesLoop: for (int i = 0; i < currentRoomGates.length; i++) {
			if (playerXCoordinate == currentRoomGates[i][0] && playerYCoordinate == currentRoomGates[i][1]) {
				switch (currentRoomGates[i][2]) {
				case 1:
					roomNumber = 1;
					playerCoordinates = new int[] { (WIDTH - 1) - playerXCoordinate, (HEIGHT - 1) - playerYCoordinate };
					currentRoomGates = roomGates.get(1);
					break roomGatesLoop;
				case 2:
					roomNumber = 2;
					playerCoordinates = new int[] { (WIDTH - 1) - playerXCoordinate, (HEIGHT - 1) - playerYCoordinate };
					currentRoomGates = roomGates.get(2);
					break roomGatesLoop;
				case 3:
					roomNumber = 3;
					playerCoordinates = new int[] { (WIDTH - 1) - playerXCoordinate, (HEIGHT - 1) - playerYCoordinate };
					currentRoomGates = roomGates.get(3);
					break roomGatesLoop;
				case 4:
					roomNumber = 4;
					playerCoordinates = new int[] { (WIDTH - 1) - playerXCoordinate, (HEIGHT - 1) - playerYCoordinate };
					currentRoomGates = roomGates.get(4);
					break roomGatesLoop;
				case 5:
					roomNumber = 5;
					playerCoordinates = new int[] { (WIDTH - 1) - playerXCoordinate, (HEIGHT - 1) - playerYCoordinate };
					currentRoomGates = roomGates.get(5);
					break roomGatesLoop;
				default:
					break;
				}
			}
		}
	}

	/*
	 * This performs the actions for each command declared in commands
	 */
	private static void handleCommand(Commands command) {
		switch (command) {
		case LEFT:
			// If the player isn't at the edge it moves the player to the left
			if (!(playerCoordinates[0] - 1 <= 0)) {
				playerCoordinates[0]--;
			} else if (containsGate(playerCoordinates[0] - 1, playerCoordinates[1])) {
				// If it goes through a door, it goes into whichever room that door goes to
				goToNextRoom(playerCoordinates[0] - 1, playerCoordinates[1], roomNumber);
				// It then corrects the player coordinate so it isn't in the door
				playerCoordinates[0]--;
			} else {
				System.out.println("\nPlayer is at the edge and cannot move Left");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case RIGHT:
			// If the player isn't at the edge it moves the player to the right
			if (!(playerCoordinates[0] + 1 >= WIDTH - 1)) {
				playerCoordinates[0]++;
			} else if (containsGate(playerCoordinates[0] + 1, playerCoordinates[1])) {
				// If it goes through a door, it goes into whichever room that door goes to
				goToNextRoom(playerCoordinates[0] + 1, playerCoordinates[1], roomNumber);
				// It then corrects the player coordinate so it isn't in the door
				playerCoordinates[0]++;
			} else {
				System.out.println("\nPlayer is at the edge and cannot move Right");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case UP:
			// If the player isn't at the top edge, it moves the player up 1
			if (!(playerCoordinates[1] - 1 <= 0)) {
				playerCoordinates[1]--;
			} else if (containsGate(playerCoordinates[0], playerCoordinates[1] - 1)) {
				// If it goes through a door, it goes into whichever room that door goes to
				goToNextRoom(playerCoordinates[0], playerCoordinates[1] - 1, roomNumber);
				// It then corrects the player coordinate so it isn't in the door
				playerCoordinates[1]--;
			} else {
				System.out.println("\nPlayer is at the edge and cannot move Up");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case DOWN:
			if (!(playerCoordinates[1] + 1 >= HEIGHT - 1)) {
				playerCoordinates[1]++;
			} else if (containsGate(playerCoordinates[0], playerCoordinates[1] + 1)) {
				// If it goes through a door, it goes into whichever room that door goes to
				goToNextRoom(playerCoordinates[0], playerCoordinates[1] + 1, roomNumber);
				// It then corrects the player coordinate so it isn't in the door
				playerCoordinates[1]++;
			} else {
				System.out.println("\nPlayer is at the edge and cannot move Down");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case INVENTORY:
			System.out.println(Arrays.toString(inventory.toArray()));
			break;
		case HELP:
			System.out.println("Commands: ");
			System.out.println("\'left\': Moves the player Left");
			System.out.println("\'right\': Moves the player Left");
			System.out.println("\'up\': Moves the player up");
			System.out.println("\'down\': Moves the player down");
			System.out.println("\'grab\': Puts the item you're standing on inside your inventory");
			System.out.println("\'inventory\': Shows the items you have in your inventory");
			System.out.println("\nPress enter to continue");
			scn.nextLine();
			break;
		default:
			break;
		}
	}

	private static void waitForCommand() {
		System.out.print("\nEnter Command: ");
		String currentCommand = scn.nextLine();
		try {
			command = Commands.valueOf(currentCommand.toUpperCase());
		} catch (Exception e) {
			System.out.println(
					"\'" + currentCommand + "\'" + " is not a valid command;\nType \'help\' for a list of commands.");
			command = Commands.NULL;
		}
	}

	private static boolean getYesNo(String[] optionsYes, String[] optionsNo, String preText) {
		System.out.println(preText);
		String result = scn.nextLine();
		if (Arrays.asList(optionsYes).contains(result.toUpperCase())) {
			return true;
		} else if (Arrays.asList(optionsNo).contains(result.toUpperCase())) {
			return false;
		} else {
			System.out.println("\nPlease enter a valid option\n");
			return getYesNo(optionsYes, optionsNo, preText);
		}
	}

	private static void playGame() {
		waitForCommand();
		while (command == Commands.NULL) {
			waitForCommand();
		}
		handleCommand(command);

		// Checks if there's a colliding object,
		switch (collidingObject()) {
		case SWORD:
			inventory.add(collidingObject());
			swordLocation = new int[3]; // Clears the sword coordinates so it can't be picked up again
			break;
		case STAIRCASE:
			while (true) {
				if (getYesNo(new String[] { "YES", "Y" }, new String[] { "NO", "N" },
						"Would you like to climb up the stairs? (Yes or No)")) {
					if (getYesNo(new String[] { "UP" }, new String[] { "DOWN" },
							"\nWhich direction would you like to go? (Up or Down)")) { // Up is true, down is false
						if (floorNumber < 3) {
							floorNumber++;
							System.out.println("\nYou are now on Floor " + floorNumber);
						} else {
							System.out.println("You are at the highest floor");
						}
					} else {
						if (floorNumber > 1) {
							floorNumber--;
							System.out.println("\nYou are now on Floor " + floorNumber);
						} else {
							System.out.println("You are at the lowest floor");
						}
					}
					System.out.println();
				} else {
					break;
				}
			}
			break;
		case MAGIC_STONE:
			System.out.println("You found a Magic Stone!");
			inventory.add(collidingObject());
			break;
		default:
			break;
		}
	}

	private static void initGame() {
		roomGates.put(1, room1Gates);
		roomGates.put(2, room2Gates);
		roomGates.put(3, room3Gates);
		roomGates.put(4, room4Gates);
		roomGates.put(5, room5Gates);
	}

	public static void main(String[] args) {
		initGame();
		while (inGame) {
			System.out.println("\nFLOOR " + floorNumber + ", ROOM " + roomNumber + "\n");
			draw();
			playGame();
		}
	}
}
