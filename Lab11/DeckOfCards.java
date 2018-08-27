/**
 * 	@author Prashant Rizal
 *	CS121-003
 *	Encapsulation is used in the program by using the private modifiers. That ensures that those instance variables are only accessed in just this DeckOfCards class.
 *	The most difficult part was to set the toString method properly.
 */
import java.util.Random;

public class DeckOfCards implements DeckOfCardsInterface {

	private Card[] cards;
	private  int DECKSIZE = 52;
	private int nextCardIndex = 0;
	private int shuffles = 0;

	public DeckOfCards(){
		cards = new Card[DECKSIZE];
		int i= 0;
		   for(Suit s : Suit.values()) {
			   for(FaceValue v : FaceValue.values()) {
			      //Create new card and add it to your deck.
				   
					   cards[i] = new Card(s, v);
					   i++;
				   
			   }
			 }
	   }

	@Override
	public void shuffle() {
		Random generator = new Random();

		// Attempt to swap each card with a random card in the deck.
		// This isn't a perfect random shuffle but it is a simple one.
		// A better shuffle requires a more complex algorithm.

		for (int i = 0; i < cards.length; i++) {
			int j = generator.nextInt(cards.length);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}

		// Reset instance variable that keeps track of dealt and remaining
		// cards.
		nextCardIndex = 0;
		shuffles++;
		// TODO Auto-generated method stub

	}

	@Override
	public Card draw() {
		// TODO Auto-generated method stub
		
		if (nextCardIndex== DECKSIZE)
		{
			return null;
		}
		else
		{
			return cards[nextCardIndex++];
		}
	}

	@Override
	public void restoreDeck() {
		int i= 0;
		   for(Suit s : Suit.values()) {
			   for(FaceValue v : FaceValue.values()) {
			      //Create new card and add it to your deck.
				   
					   cards[i] = new Card(s, v);
					   i++;
				   
			   }
			 }
		// TODO Auto-generated method stub

	}

	@Override
	public int numCardsRemaining() {
		int leftOver= DECKSIZE;
		return leftOver;
		
	}

	@Override
	public int numCardsDealt() {
		return nextCardIndex;
	}

	@Override
	public Card[] dealtCards() {
		Card[] dealtCard = new Card[nextCardIndex];
		return dealtCard;
	}

	@Override
	public Card[] remainingCards() {
		Card[] remainCard = new Card[DECKSIZE - nextCardIndex];
		return null;
	}

	public String toString() {
		String format = " ";
		
		for (int i = 0; i < cards.length; i++) {
			format += cards[i].toString() + "    ";
			if ((i+1) % 4==0){
				format += "\n";
			}
		}
		return format;
	}
	
}
