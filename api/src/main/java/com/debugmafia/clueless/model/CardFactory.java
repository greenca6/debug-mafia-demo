package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;

public class CardFactory {
  public static Set<Card> createDeck() {
    Set<Card> deck = new HashSet<>();

    // Piece Cards
    deck.add(new Card(CardType.PIECE, "miss scarlet"));
    deck.add(new Card(CardType.PIECE, "mrs. peacock"));
    deck.add(new Card(CardType.PIECE, "colonel mustard"));
    deck.add(new Card(CardType.PIECE, "mrs. white"));
    deck.add(new Card(CardType.PIECE, "mr. green"));
    deck.add(new Card(CardType.PIECE, "professor plum"));

    // Weapon Cards
    deck.add(new Card(CardType.WEAPON, "rope"));
    deck.add(new Card(CardType.WEAPON, "lead pipe"));
    deck.add(new Card(CardType.WEAPON, "knife"));
    deck.add(new Card(CardType.WEAPON, "wrench"));
    deck.add(new Card(CardType.WEAPON, "candlestick"));
    deck.add(new Card(CardType.WEAPON, "pistol"));

    // Room Cards
    deck.add(new Card(CardType.ROOM, "study"));
    deck.add(new Card(CardType.ROOM, "hall"));
    deck.add(new Card(CardType.ROOM, "lounge"));
    deck.add(new Card(CardType.ROOM, "library"));
    deck.add(new Card(CardType.ROOM, "billiard room"));
    deck.add(new Card(CardType.ROOM, "dining room"));
    deck.add(new Card(CardType.ROOM, "conservatory"));
    deck.add(new Card(CardType.ROOM, "ball room"));
    deck.add(new Card(CardType.ROOM, "kitchen"));

    return deck;
  }
}
