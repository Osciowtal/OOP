package ru.nsu.iyaroslavtsev.task112;

/**
 * enum that contains card Ranks' data.
 * int value: how many points a card of this rank costs.
 * String name: card name to display in console.
 * String symb: short part to display as a card sign (like "Q" for Queen and "2" for Two).
 */
public enum Rank {
    TWO(2, "Two", "2"),
    THREE(3, "Three", "3"),
    FOUR(4, "Four", "4"),
    FIVE(5, "Five", "5"),
    SIX(6, "Six", "6"),
    SEVEN(7, "Seven", "7"),
    EIGHT(8, "Eight", "8"),
    NINE(9, "Nine", "9"),
    TEN(10, "Ten", "10"),
    JACK(10, "Jack", "J"),
    QUEEN(10, "Queen", "Q"),
    KING(10, "King", "K"),
    ACE(11, "Ace", "A");

    /**
     * value.
     */
    private final int value;
    /**
     * name.
     */
    private final String name;
    /**
     * symb.
     */
    private final String symb;

    /**
     * Rank constructor.
     *
     * @param value value.
     * @param name name.
     * @param symb symbol.
     */
    private Rank(int value, String name, String symb) {
        this.value = value;
        this.name = name;
        this.symb = symb;
    }

    /**
     * Returns value.
     *
     * @return value.
     */
    public int getVal() {
        return value;
    }

    /**
     * Returns name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns symbol.
     *
     * @return symbol.
     */
    public String getSymb() {
        return symb;
    }

}