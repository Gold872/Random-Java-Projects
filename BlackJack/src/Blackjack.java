
import java.util.Scanner;

public class Blackjack {

	private static int playerValue = (int) (Math.random() * 11) + 1;
	private static int opponentValue = (int) (Math.random() * 11) + 1;
	
	static Scanner scanner = new Scanner(System.in);
	
	private static void playBlackJack() {
		while(playerValue < 21 && opponentValue < 21) {
			System.out.println("Your current value is " + playerValue + "\n");
			System.out.println("Do you want to hit or stand? [hit] [stand]");
			String answer = scanner.nextLine();
			if(answer.equals("hit")) {
				doPlayerMove();
				System.out.println("Your value is now " + playerValue + "\n");
				if(playerValue >= 21) {
					System.out.println("You are busted, you lose");
					break;
				} else if(playerValue == 21) {
					System.out.println("You Win");
					break;
				}			
			}
			
			doOpponentMove();
			if(opponentValue >= 21) {
				System.out.println("Your opponent is busted, you win");
				break;
			} else if(opponentValue == 21) {
				System.out.println("Your oppopnent wins, you lose");
				break;
			}
		}
	}
	
	private static void doPlayerMove() {
		if(playerValue + 11 > 21) {
			playerValue += Math.random() * 10 + 1;
		} else {
			playerValue += Math.random() * 11 + 1;
		}
	}
	
	private static void doOpponentMove() {
		int moveChoice = (int) (Math.random() * 2);
		if(moveChoice == 0) {
			System.out.println("Your opponent chose to hit \n");
			if(opponentValue + 11 > 21) {
				opponentValue += Math.random() * 10 + 1;
			} else {
				opponentValue += Math.random() * 11 + 1;
			}
		}
		if(moveChoice == 1) {
			System.out.println("Your opponent chose to stand");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		playBlackJack();
	}
	
}
