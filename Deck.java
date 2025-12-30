import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();
    
    public Deck( ) {
        for ( Card.Suit suit : Card.Suit.values() ) {
            for ( Card.Rank rank : Card.Rank.values() ) {
                cards.add( new Card(suit, rank));
            }
        }
    }

    public void shuffleDeck( ) {
        Collections.shuffle(cards);
    }

    public Card deal( ) {
        return cards.remove(cards.size() - 1);
    }

}
