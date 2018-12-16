
// Hailey Hurd
// Child class of card: the only difference is that in the printCard() method, the card prints the letter rather than the number (i.e. J instead of 10)
// Used for A, J, Q, and K cards.

public class RoyalCard extends Card {
	public RoyalCard(int number, int suit_number){
		super(number, suit_number);
	}

	// Printing method that replaces numbers with the corresponding letters:
	public void printCard(){
		if (super.getNumber() == 1) {
			System.out.print(super.getSuit() + "A");
		} else if (super.getNumber() == 11) {
			System.out.print(super.getSuit() + "J");
		}
		else if (super.getNumber() == 12) {
			System.out.print(super.getSuit() + "Q");
		}
		else if (super.getNumber() == 13) {
			System.out.print(super.getSuit() + "K");
		} else {
			throw new IllegalArgumentException();
		}
	}

}