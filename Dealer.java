import java.util.List;

public class Dealer {
    private Deck deck;
    
    public Dealer( ) {
        deck = new Deck();
        deck.shuffleDeck();
    }

    /** Deals numOfCards cards to each player 
     * Returns true on success
     * Returns false if there's not enough cards in deck
    */
    public boolean dealCards( List<Player> players, int numOfCards ) {
        for ( int i = 0 ; i < numOfCards; i++ ) {
            for ( Player player : players ) {
                Card card = deck.deal();
                if ( card == null ) {
                    System.out.println("\n\n [*ERROR*] - in dealCards() \n\n\n");
                    return false; // insufficient amount of cards
                }
                player.getHand().add(card);
            }
        }
        return true;
    }


}
