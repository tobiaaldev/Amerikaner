public class TrickPlay {
    private final Player player;
    private final Card card;

    public TrickPlay( Player player, Card card ) {
        this.player = player;
        this.card = card;
    }

    public Player getPlayer( ) {
        return player;
    }

    public Card getCard( ) {
        return card;
    }
}
