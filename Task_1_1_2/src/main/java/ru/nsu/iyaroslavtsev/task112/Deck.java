package ru.nsu.iyaroslavtsev.task112;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck class to deal with deck.
 * It has private static list of cards and methods.
 * "Deck();" constructor builds up a new deck with 2 "for" by going through all Ranks and Suits.
 * throwCard() function always check if deck is empty and if so it automatically rebuild a deck.
 * Deck should stay random so there is a .shuffle() (taken from "java.util.Collections").
 * getCards() function just returns a list of cards.
 */
public class Deck {
    private static List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card card = new Card(s, r);
                cards.add(card);
            }
        }
    }

    public Card throwCard() {
        if (cards.isEmpty()) {
            System.out.println("\nDeck is empty - creating new one!");
            Deck cards = new Deck();
            System.out.println("Shuffling...");
            cards.shuffle();
            System.out.println("Done.");
        }
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}