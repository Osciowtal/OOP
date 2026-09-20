package ru.nsu.iyaroslavtsev.task112;

/**
 * Card from Rank and Suit enums.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Card.
     *
     * @param suit - suit
     * @param rank - rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns a rank's value.
     *
     * @return value.
     */
    public int getVal() {
        return rank.getVal();
    }

    /**
     * showCard() function that returns a full name.
     *
     * @return Name + pic like "Queen of Spades [Q ♠]".
     */
    public String showCard() {
        return String.format("%s of %s [%s %s]",
                rank.getName(),
                suit.getName(),
                rank.getSymb(),
                suit.getSymb()
        );
    }

    /**
     * And another one that do only pseudo pic part "[Q ♠]".
     *
     * @return little char pic.
     */
    public String showPseudoPic() {
        return String.format("[%s %s]",
                rank.getSymb(),
                suit.getSymb()
        );
    }
}