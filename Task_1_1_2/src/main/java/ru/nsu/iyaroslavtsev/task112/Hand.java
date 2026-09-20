package ru.nsu.iyaroslavtsev.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand class for Dealer and Player.
 * List of received cards, username, score and amount of aces.
 * takeCard() function for getting a card and calculating a score depending on points and aces.
 * If there are any taken aces and score is > 21 aces' values collapse until score is <= 21.
 * displayCards() displays cards.
 * dealerCards() do the same, but second card is hidden.
 */
public class Hand {
    private List<Card> cards;
    private final String name;
    private int score;
    private int aces;

    public Hand(String user) {
        this.cards = new ArrayList<>();
        this.name = user;
        this.score = 0;
        this.aces = 0;
    }

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

    public int getScore() {
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getAces() {
        return aces;
    }


    public void dispplayCards() {
        System.out.printf("\n%s's cards: [ %d ]\n", name, score);
        for (Card c : cards) {
            System.out.printf("%s  ", c.showPseudoPic());
        }
    }

    public void dealerCards() {
        System.out.printf("\n%s's cards: [ %d + ? ]\n", name, cards.get(0).getVal());
        System.out.printf("%s  [hidden]", cards.get(0).showPseudoPic());
    }
}