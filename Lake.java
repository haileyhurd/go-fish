
// Hailey Hurd
// The lake contains a full deck of cards at the start of the game, and then 5 random cards are dealt to each player using the Hand class.
// The game ends when the lake is empty.

import java.util.*;
public class Lake {
	private ArrayList<Card> deck;
	
	// Constructor:
	public Lake(){

	// Constructing deck:
	this.deck = new ArrayList<Card>();

		// Going through each suit:
		for (int s = 1; s < 5; s++){
			
			// Adding ace to deck
			Card ace = new RoyalCard(1, s);
			this.deck.add(ace);

			// Adding normal cards to deck (2-10)
			for (int n = 2; n < 11; n++) {
				Card card = new Card(n, s);
				this.deck.add(card);
			}

			// Adding jack, queen, and king to deck
			for (int n = 11; n < 14; n++) {
				Card royal_card = new RoyalCard(n, s);
				this.deck.add(royal_card);
			}
		}
	}

	// Printer (used for initial testing and debugging):
	public void printLake() {
		for (Card c : this.deck) {

			if (c == null) {

			// error checking - checks for null slots
				System.out.print(" null");

			} else {

			// Print each card in the lake:
				System.out.print(" ");
				c.printCard();
			}
		}
	}

	// Returns the current number of cards in the lake:
	public int getLength(){
		return this.deck.size();
	}

	// Returns the specified card and removes it from the lake:
	public Card takeCard(int index){
		Card to_return = deck.get(index);
		deck.remove(index);
		return to_return;
	}
}