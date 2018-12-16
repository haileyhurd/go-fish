
// Hailey Hurd

// Each player has a hand. The hand contains each of their cards, and is added to / subtracted from throughout the game by other classes.

import java.util.*;
public class Hand {
	public ArrayList<Card> hand;

	// Constructor:
	public Hand(Lake lake){

		// Creates an empty hand:
		this.hand = new ArrayList<Card>();

		int count = 0;

		// Randomly draws 5 cards from the lake and adds them to the hand:
		while (count < 5) {
			int number = (int) (Math.random()*lake.getLength());
			Card random_card = lake.takeCard(number);
			hand.add(random_card);
		count++;
		}
	}

	// Draws one random card from the lake:
	public void fish(Lake lake){
		int number = (int) (Math.random()*lake.getLength());
		Card random_card = lake.takeCard(number);
		hand.add(random_card);
	}

	// Sorts the hand by number (groups aces together, twos together, etc.):
	public void sort(){
		Collections.sort(this.hand);
	}

	// Prints out the cards in the hand:
	public void printHand(){
		for (Card c : this.hand) {
			// error checking - checks for null slots
			if (c == null) {
				System.out.print(" null");
			} else {
				System.out.print(" ");
				c.printCard();
			}
		}
	}

	// Returns the value of the highest value three-of-a-kind in the hand. If there are no three-of-a-kinds, it returns 0.
	public int threeOfAKind() {
		// Sorts the hand:
		sort();

		// Base value for the return:
		int three = 0;

		// Goes through hand to check if the card two ahead is the same number as the current card:
		for(int i = 0; i < this.hand.size() - 2; i++){
			if(this.hand.get(i).getNumber()==this.hand.get(i+2).getNumber()) {

					// Sets the return to the value of the three-of-a-kind:
					three = this.hand.get(i).getNumber();
			}
		}
		return three;
	}

	// Same as threeOfAKind(), but checks for the highest value two-of-a-kind:
	public int twoOfAKind() {
		// Sort:
		sort();

		// Base value for the return:
		int two = 0;

		// Goes through hand and checks if the next card is the same number as the current card:
		for(int i = 0; i < this.hand.size() - 1; i++){
			if(this.hand.get(i).getNumber()==this.hand.get(i+1).getNumber()) {

					// Sets the return to the value of the two-of-a-kind:
					two = this.hand.get(i).getNumber();
			}
		}
		return two;
	}

	// Returns the number of the highest value card in the hand:
	public int highestCard() {

		// Sorts the hand by number:
		sort();

		// Returns the number of the last card in the hand:
		return this.hand.get(this.hand.size() - 1).getNumber();
	}

	// Returns the number of the second highest value card in the hand:
	public int secondHighestCard() {

		// Gets the highest card in the hand:
		int highest = highestCard();

		// Sorts the hand by number:
		sort();

		// Goes backwards through the hand and returns the number of the first card that has a different value from the highest card:
		for(int i = this.hand.size() - 2; i >= 0; i--){
			if(this.hand.get(i).getNumber() != highest) {
				return this.hand.get(i).getNumber();
			} 
		}
		// If the only number is the highest number, it returns 1 as a default value:
		return 1;
	}
}