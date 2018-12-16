
// Hailey Hurd

// The main running class for GoFish. This class has a method for the ai's turn and the player's turn, and rotates between them until the 
// lake is empty. This class is responsible for interacting with the player by printing and taking inputs. The class is also responsible for checking
// whether a player has made a book at the end of their turn. From now on, the human player will the referred to as "player" and the ai will be
// refered to as "ai."

import java.util.*;
public class GoFish {

	// Main running method:
	public static void main(String[] args) {
		// Constructing the lake, the ai, and the player:
		Lake lake = new Lake();
		Player player = new Player(lake, "You have");
		player.hand.sort();
		Player ai = new Player(lake, "The AI has");
		ai.hand.sort();

		// Constructs the scanner:
		Scanner console = new Scanner(System.in);

		// Prints the header section of the game and the instructions:
		System.out.print("\n");
		System.out.println("..........................................................................");
		System.out.print("\n");
		System.out.println("Welcome to Go Fish!");
		System.out.print("\n");
		System.out.println("The object of this game is to create as many books, or groups of four cards with the same number, as possible. The values of the books are weighted by the numbers on the cards, so try to create high value books.");
		System.out.print("\n");
		System.out.println("During your turn, you may ask the AI for any card. If they do not have it, you will have to go fishing in the lake! The game will end when the lake is empty.");
		
		// Rotates between the player's turn and the ai's turn until the lake is empty:
		while(lake.getLength()>1){
			System.out.print("\n");
			System.out.println("..........................................................................");
			System.out.print("\n");
			System.out.println("PLAYER'S TURN");
			playerTurn(player, ai, lake, console);
			System.out.print("\n");
			System.out.println("..........................................................................");
			System.out.print("\n");
			System.out.println("AI'S TURN");
			aiTurn(player, ai, lake, console);
		}

		// When the game has ended, the program checks who has the most points and prints a message depending on who won:
		System.out.print("\n");
		if(player.getPoints()>ai.getPoints()) {
			System.out.println("YOU WIN!");
		} else if(player.getPoints()<ai.getPoints()) {
			System.out.println("YOU LOSE!");
		} else {
			System.out.println("IT'S A TIE!");
		}
	}

	// Method to check whether the specified player (player or ai) has made any books yet:
	public static void checkBooks(Player player) {
		
		// Sorts hand:
		player.hand.sort();

		// Loops through hand, checking if each card has the same number as the one 3 ahead:
		for (int i = 0; i < player.hand.hand.size() - 3; i++){
			if(player.hand.hand.get(i).getNumber()==player.hand.hand.get(i+3).getNumber()){

				// If so, it creates a book of that number:
				Book new_book = new Book(player.hand.hand.get(i).getNumber(), player);
			}
		}
	}

	// Method for the player's turn:
	public static void playerTurn(Player player, Player ai, Lake lake, Scanner console){
		// If the player has no cards, they automatically fish before their turn:
		if(player.hand.hand.size()==0){
			player.hand.fish(lake);
		}

		// Prints the player's hand at the beginning of their turn:
		System.out.print("\n");
		System.out.println("Here is your hand: ");
		player.hand.printHand();
		System.out.print("\n");

		// Asks for input on what number to ask for:
		System.out.println("Please type the number (not letter) of card you would like to ask for: ");
		int to_ask = console.nextInt();

		// Runs the askForCard() method from the Player class:
		player.askForCard(ai, to_ask, lake);
		System.out.print("\n");

		// Prints for the player's new hand:
		System.out.println("Here is your new hand: ");
		player.hand.printHand();

		// Checks whether the player has made any books:
		checkBooks(player);

		// Prints the player's point total:
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("Your point total is " + player.getPoints() + ".");
		System.out.print("\n");
	}

	public static void aiTurn(Player player, Player ai, Lake lake, Scanner console){
		// If the ai has no cards, they automatically fish before their turn:
		if(ai.hand.hand.size()==0){
			ai.hand.fish(lake);
		}

		// Prints what the ai asks for:
		System.out.print("\n");
		System.out.println("AI: Do you have any ");
		printNumber(ai.decision());
		System.out.print("s?");

		// Runs the askForCard() method from the player class, using the ai's decision() method as the number.
		ai.askForCard(player, ai.decision(), lake);
		System.out.print("\n");

		// Checks whether the ai has made any books:
		checkBooks(ai);

		// Prints the ai's point total:
		System.out.println("The AI's point total is " + ai.getPoints() + ".");
	}

	// Method that prints the card number (or letter in the case of royal cards):
	// Used by the aiTurn to print the number that the ai has asked for, and by checkBooks() to print the value of the book that has been made:
	public static void printNumber(int number){
		if (number == 1) {
			System.out.print("A");
		} else if (number == 11) {
			System.out.print("J");
		}
		else if (number == 12) {
			System.out.print("Q");
		}
		else if (number == 13) {
			System.out.print("K");
		} else {
			System.out.print(number);
		}
	}
}
