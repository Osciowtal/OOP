package ru.nsu.iyaroslavtsev.task112;

/**
 * Card from Rank and Suit enums.
 * Has function that returns a full name like "Queen of Spades [Q ♠]".
 * And another one that do only pseudo pic part "[Q ♠]".
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getVal() {
        return rank.getVal();
    }

    public String showCard() {
        return String.format("%s of %s [%s %s]",
                rank.getName(),
                suit.getName(),
                rank.getSymb(),
                suit.getSymb()
        );
    }

    public String showPseudoPic() {
        return String.format("[%s %s]",
                rank.getSymb(),
                suit.getSymb()
        );
    }
}