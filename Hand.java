import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
    private ArrayList<Card> cards;

    public Hand( ) {
        cards = new ArrayList<>();
    }
    /* Copy constructor */
    public Hand( Hand original ) {
        this.cards = new ArrayList<>(original.cards);
    }

    public void add( Card card ) {
        cards.add(card);
    }

    public void remove( Card card ) {
        cards.remove(card);
    }

    public boolean contains( Card card ) {
        return cards.contains(card);
    }

    public List<Card> getCards( ) {
        sort();
        return cards;
    }

    private void sort( ) {
        cards.sort(Comparator.comparing(Card::getSuit).thenComparing(Card::getRank));
    }

}
