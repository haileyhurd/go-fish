// Hailey Hurd

// This is the class for created books. Constructing a book automatically removes the cards from the hand of the player that created it 
// and adds the value of the book to the player's point total.
public class Book{
	private int value;

	// Constructor:

	// Takes in the value of the book and the player who made it:
	public Book(int value, Player player){
		this.value = value;

		// Objects can't be removed from an ArrayList when it is being looped through, so instead the loop adds to 
		// an array of cards to remove after the loop is completed.

		// Creates array:
		Card[] cards_to_remove = new Card[4];
		int index = 0;

		// Loops through hand and adds all cards matching the book value to the array:
		for (Card c : player.getHand()){
			if(c.getNumber() == value){
				cards_to_remove[index] = c;
				index++;
			}
		}

		// Loops through the array of cards to remove and removes them each from the player's hand:
		for (Card c : cards_to_remove){
			player.hand.hand.remove(c);
		}

		// Adds the book value to the player's point total:
		player.addPoints(this.value);

		// Prints a message that the specified player has made a book of the specified value:
		System.out.print("\n");
		System.out.println(player.getMessage() + " made a book of ");
		GoFish.printNumber(this.value);
		System.out.print("s!");
		System.out.print("\n");
	}

	// Returns value of the book:
	public int getValue(){
		return this.value;
	}
}