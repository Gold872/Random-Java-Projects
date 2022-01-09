import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class slotMachine {

	private static int tokens = 100;
	private static ArrayList<Integer> slot = new ArrayList<Integer>();
	private static ArrayList<Integer> total = new ArrayList<Integer>();
	private static Scanner scanner = new Scanner(System.in);

	private static void playSlotMachine() {
		while (tokens > 0) {
			System.out.println("Do you want to continue at the price of one token? [Y] or [N]");
			String answer = scanner.next();
			if (answer.equals("Y")) {
				total.add(1);
				for (int i = 0; i < 3; i++) {
					slot.add((int) (Math.random() * 7) + 1);
				}
				System.out.println("You got the numbers: " + Arrays.toString(slot.toArray()));
				int count = 0;
				
				for(int i : slot) {
					if(i == 1) {
						count++;
						if(count == 3) {
							total.add(4);
						} else if(count == 2) {
							total.add(2);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 1) {
						count++;
						if(count == 3) {
							total.add(4);
						} else if(count == 2) {
							total.add(2);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 2) {
						count++;
						if(count == 3) {
							total.add(8);
						} else if(count == 2) {
							total.add(4);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 3) {
						count++;
						if(count == 3) {
							total.add(12);
						} else if(count == 2) {
							total.add(6);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 4) {
						count++;
						if(count == 3) {
							total.add(16);
						} else if(count == 2) {
							total.add(8);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 5) {
						count++;
						if(count == 3) {
							total.add(16);
						} else if(count == 2) {
							total.add(8);
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 6) {
						count++;
						if(count == 3) {
							total.add(-30);
							System.out.println("You are quite unlucky my friend");
						}
						continue;
					}
				}
				
				count = 0;
				for(int i : slot) {
					if(i == 7) {
						count++;
						if(count == 3) {
							total.add(16);
						} else if(count == 2) {
							total.add(8);
						}
						continue;
					}
				}
				
				int profit = 0;
				for (int i = 0; i < total.size(); i++) {
					profit += total.get(i);
				}
				
				if(profit <= 1) {
					for (int i = 0; i < slot.size(); i++) {
						profit += (slot.get(i) * -1);
					}
				}
				
				slot.clear();
				total.clear();
				if (profit > 0) {
					System.out.println("You have gained " + profit + " tokens");
				} else if (profit < 0) {
					System.out.println("You have lost " + profit * -1 + " tokens");
				}
				tokens += profit;
				System.out.println("You have " + tokens + " tokens");
				continue;
			}

			if (answer.equals("N")) {
				System.out.println("You have " + tokens + " left in total");
				break;
			}
		}
		if (tokens <= 0) {
			System.out.println("You ran out of tokens");
		}
	}

	public static void main(String[] args) {
		playSlotMachine();
	}
}
