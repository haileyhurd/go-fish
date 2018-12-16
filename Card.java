
// Hailey Hurd
// Class for cards from 2-10. Each card has a suit and a number. 
// Suits are special symbols that print as the suit symbols.
// Spades: u2660, suit number 1
// Hearts: u2665, suit number 2
// Diamonds: u2666, suit number 3
// Clubs: u2663, suit number 4

public class Card implements Comparable<Card>{
	private int number;
	private String suit;

	// Constructor:
	public Card(int number, int suit_number){
		
		this.number = number;

		// suit_number corresponds to a suit
		// translating suit_number to suit:
		if (suit_number == 1) {
			this.suit = "\u2660";
		} else if (suit_number == 2) {
			this.suit = "\u2665";
		} else if (suit_number == 3) {
			this.suit = "\u2666";
		} else if (suit_number == 4) {
			this.suit = "\u2663";
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Accessors:

	public int getNumber(){
		return this.number;
	}

	public String getSuit(){
		return this.suit;
	}

	// No mutators, because the card's fields shouldn't be changed after they are initialized.

	// Printer:
	public void printCard(){
		System.out.print(this.suit + "" + this.number);
	}

	// Ability to sort cards by number:
	public int compareTo(Card other){
		if(this.number > other.getNumber()) {
			return 1;
		} else if(this.number < other.getNumber()) {
			return -1;
		} else {
			return 0;
		}
	}
}