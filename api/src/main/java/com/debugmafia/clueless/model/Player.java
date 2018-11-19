package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;

public class Player {
  private String userName;
  private Piece piece;
  private Set<Card> cardsInHand = new HashSet<>();

  public Player(String userName, Piece p) {
    this.userName = userName;
    this.piece = p;
  }

  public String getUsername() {
    return this.userName;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public boolean hasCard(Card c) {
    return cardsInHand.contains(c);
  }

  public void dealCards(Set<Card> cards) {
    this.cardsInHand = cards;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    final Player p = (Player) obj;

    if (!p.getUsername().equals(this.userName) || !p.getPiece().equals(this.piece)) {
      return false;
    }

    return true;
  }
}
