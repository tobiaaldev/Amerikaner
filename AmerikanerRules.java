import java.util.ArrayList;
import java.util.List;

public class AmerikanerRules implements Rules {
    
    private final Card.Suit trump;

    public AmerikanerRules( Card.Suit trump ) {
        this.trump = trump;
    }

    @Override
    public List<Move> legalMoves( GameState state, Player player ) {
        List<Move> moves = new ArrayList<>();

        /* If a card has already been played */
        if ( !state.trick.isEmpty() ) {
            Card.Suit leadSuit = state.trick.get(0).getCard().getSuit();
            boolean hasSuit = player.getHand().getCards().stream()
                    .anyMatch( c -> c.getSuit() == leadSuit );

            for ( Card c : player.getHand().getCards() ) {
                /**If I don't have the suit 
                 * OR if I do and this card's suit is the lead suit 
                 * Then I can play this card */
                if ( !hasSuit || c.getSuit() == leadSuit ) {
                    moves.add(new CardMove(c));
                }
            }
        } else {
            // this means I won last trick and start this one
            // so I can play any card
            for ( Card c : player.getHand().getCards() ) {
                moves.add(new CardMove(c));
            }
        }
        return moves;
    }

    @Override
    public void applyMove( GameState state, Move move ) {
        CardMove cm = (CardMove) move;
        Player p = state.getCurrentPlayer();

        p.getHand().remove(cm.card); // assumption: already determined its a legal move
        TrickPlay tp = new TrickPlay(p, cm.card);
        state.trick.add(tp);

        state.nextPlayer();

        /* End of trick */
        if ( state.trick.size() == state.getPlayers().size() ) { 
            Player winner = determineTrickWinner(state);
            winner.tricksWon++;
            
            state.setNextPlayer(state.getPlayers().indexOf(winner));
            state.trick.clear();
            state.lastTrickWinner = winner;
        }
    }

    /** Determine winning player of the trick */
    public Player determineTrickWinner( GameState state ) {
        TrickPlay best = state.trick.get(0);
        for ( int i = 1; i < state.getPlayers().size(); i++ ) {
            TrickPlay tp = state.trick.get(i);

            if ( beats(tp.getCard(), best.getCard()) ) {
                best = tp;
            }
        }
        return best.getPlayer();
    }

    /** Returns true if card a beats card b
     * Returns false if card b beats card a
     */
    private boolean beats( Card a, Card b ) {
        if ( a.getSuit() == b.getSuit() ) {
            return a.getRank().ordinal() > b.getRank().ordinal();
        }

        if ( a.getSuit() == trump && b.getSuit() != trump ) return true;

        return false;
    }

    @Override
    public boolean isGameOver( GameState state ) {
        return state.getCurrentPlayer().getHand().getCards().isEmpty();
    }

    @Override
    public int evaluateScore( GameState state, Player player ) {
        return player.tricksWon;
    }
}
