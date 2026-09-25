package ru.nsu.iyaroslavtsev.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand class for Dealer and Player.
 * List of received cards, username, score and amount of aces.
 */
public class Hand {
    private List<Card> cards;
    public final String name;
    private int score;
    private int aces;

    /**
     * Initializes a Hand.
     *
     * @param user username.
     */
    public Hand(String user) {
        this.cards = new ArrayList<>();
        this.name = user;
        this.score = 0;
        this.aces = 0;
    }

    /**
     * takeCard() function for getting a card and calculating a score depending on points and aces.
     * If there are any taken aces and score is more than 21,
     * aces' values collapse until score is less or eq to 21.
     *
     * @param card is a card.
     */
    public void takeCard(Card card) {
        if (card.getVal() == 11) {
            aces++;
        }
        cards.add(card);
        score += card.getVal();
        while ((score > 21) && (aces > 0)) {
            score -= 10;
            aces -= 1;
            System.out.printf("\n[%s]: 1 ace value collapsed into 1 point", name);
        }
    }

    /**
     * Returns score.
     *
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns list of cards.
     *
     * @return list of cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns amount of aces.
     *
     * @return amount of aces.
     */
    public int getAces() {
        return aces;
    }


    /**
     * displayCards() displays cards.
     */
    public String dispplayCards() {
        String res = String.format("\n%s's cards: [ %d ]\n", name, score);
        for (Card c : cards) {
            res += String.format("%s  ", c.pseudoPic());
        }
        return res;
    }

    /**
     * dealerCards() does the same as displayCards(), but second card is hidden.
     */
    public String dealerCards() {
        String res = String.format("\n%s's cards: [ %d + ? ]\n", name, cards.get(0).getVal());
        res += String.format("%s  [hidden]", cards.get(0).pseudoPic());
        return res;
    }
}