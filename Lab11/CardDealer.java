
public class CardDealer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeckOfCards card = new DeckOfCards();
		System.out.println("Unshuffled: ");
		System.out.println(card.toString());
		
		card.shuffle();
		System.out.println("Shuffled: ");
		System.out.println(card.toString());
		
		while (card.numCardsDealt()<52){
			Card p1 = card.draw();
			Card p2 = card.draw();
			System.out.println("Drawing cards....");
			System.out.println("Player 1 : "+ p1);
			System.out.println("Player 2 : "+ p2);
			
			if (p1.compareTo(p2)>0){
				System.out.println("Player 1 wins!!");
			}
			else if (p2.compareTo(p1)>0){
				System.out.println("Player 2 wins!!");
			}
			else
			{
				System.out.println("Everyone wins!!");
			}
		}
		card.restoreDeck();
		System.out.println("Restored Deck!!");
		System.out.println(card.toString());
	}
		
 
}
