
// Hailey Hurd

// This is the player class. It can be used for both the player and the ai, which are told apart by the turn methods in the GoFish class.
// This class contains everything a player has and all the actions they can take.

import java.util.*;
public class Player{
	private int points;
	public Hand hand;
	public Lake lake;
	private String message_beginning;
	private ArrayList<Integer> hasAsked;
	private int askedLastTurn;

	// Constructor:
	public Player(Lake lake, String message_beginning){
		this.lake = lake;
		this.hand = new Hand(lake);
		this.points = 0;
		this.message_beginning = message_beginning;
		this.hasAsked = new ArrayList<Integer>();
		this.askedLastTurn = 0;
	}

	// Getters:
	public int getPoints(){
		return this.points;
	}

	public String getMessage(){
		return this.message_beginning;
	}

	public ArrayList<Card> getHand(){
		return this.hand.hand;
	}

	public ArrayList<Integer> getHasAsked(){
		return this.hasAsked;
	}

	// Method to add a number of points to the player's total:
	public void addPoints(int points) {
		this.points += points;
	}

	// Method for an exchange of cards between players:
	public void giveCards(Player other_player, int card_number){
		
		// Can't add from within a loop, so the program creates an array of cards being exchanged:
		Card[] cards_to_add = new Card[4];
		int index = 0;

		// Goes through the giver's hand and adds each card matching the specified number to the array:
		for (Card c : this.hand.hand) { 
			if(c.getNumber() == card_number) {
				cards_to_add[index] = c;
				index++;
			}
		}

		// Goes through the array of cards being exchanged, adds them to the reciever's hand, and removes them from the giver's hand:
		for (Card x : cards_to_add) {
			if (x != null){
				other_player.hand.hand.add(x);
				this.hand.hand.remove(x);
			}
		}

		// Sorts the recievers hand with their new cards:
		other_player.hand.sort();
	}

	// Method to ask another player for a card:
	public void askForCard(Player other_player, int card_number, Lake lake){
		// Checks the number of cards in the ask-ee's hand:
		int their_size = other_player.hand.hand.size();

		// Goes through the giveCards() function:
		other_player.giveCards(this, card_number);

		// If the ask-ee still has the same number of cards, they did not have any cards matching the specified number. 
		// Therefore, the asker has to go fish:
		if (their_size == other_player.hand.hand.size()) {
			System.out.print("\n");
			System.out.println("GO FISH!");
			this.hand.fish(this.lake);
		}

		// Sorts asker's hand with their new card(s):
		this.hand.sort();

		// Adds the specified number to the ask-ee's list of what their opposing player has asked:
		other_player.hasAsked.add(card_number);

		// Sets the asker's askedLastTurn to the specified number:
		this.askedLastTurn = card_number;
	}

	// Checks if the player now has any of the cards that their opposing player asked for in the past 
	// (goes in order, so automatically returns the highest value):
	public int hasTheirAsk(){
		int number = 0;
		this.hand.sort();

		// Goes through the cards in their hand and sees if that card's number is on their asAsked list:
		for(Card c : this.hand.hand){
			if(hasAsked.contains(c.getNumber())){
				number = c.getNumber();
			}
		}
		return number;
	}

	// This is the ai decision-making method. It goes through its options and chooses the highest-ranked choice, using a heuristic board evaluation:
	public int decision(){
		// Arrays are {number to ask, value}
		// Rankings:
		// +100+card_value: ask for what you have 3 of, which you did not ask last turn
		// +100+card_value: ask for what they asked for and you have
		// +11+card_value: ask for what you have 2 of, which you did not ask last turn
		// +card_value: ask for your highest card

		// The program never asks for the same card it asked for last turn.

		// Base decision to compare to (the rest of the method only changes this or a future decision if the ranking is higher):
		int[] max = {1, 0};

		// Ask for the highest number that it already has 3 of:
		if((this.hand.threeOfAKind() != this.askedLastTurn) && (this.hand.threeOfAKind() != 0)){
			max[0] = this.hand.threeOfAKind();
			max[1] = this.hand.threeOfAKind()+100;
		}

		// Ask for the highest number that they know their opposing player asked for, and they now have:
		if((hasTheirAsk() != 0) && ((hasTheirAsk() + 100) > max[1])){
			max[0] = hasTheirAsk();
			max[1] = hasTheirAsk()+100;
		}

		// Ask for the highest number that it already has 2 of:
		if((this.hand.twoOfAKind() != this.askedLastTurn) && (this.hand.twoOfAKind() != 0) && ((this.hand.twoOfAKind() + 11) > max[1])){
			max[0] = this.hand.twoOfAKind();
			max[1] = this.hand.twoOfAKind()+11;
		}

		// Ask for their highest card:
		if((max[1] == 0) && (this.hand.highestCard() != this.askedLastTurn)){
			max[0] = this.hand.highestCard();
			max[1] = this.hand.highestCard();
		}

		// Ask for their second-highest card (if the highest card is what was asked for last turn):
		if(max[1] == 0){
			max[0] = this.hand.secondHighestCard();
			max[1] = this.hand.secondHighestCard();
		}

		// Returns the number the program has decided to ask for:
		return max[0];
	}
}