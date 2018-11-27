package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Player {
  private String userName;
  private Piece piece;
  private Set<Card> cardsInHand = new HashSet<>();
  private UUID uuid = UUID.randomUUID();

  public Player(String userName, Piece p) {
    this.userName = userName;
    this.piece = p;
  }

  public void dealCards(Set<Card> cards) {
    this.cardsInHand = cards;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public String getUsername() {
    return this.userName;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public boolean hasCard(Card card) {
    return cardsInHand.contains(card);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    final Player p = (Player) obj;

    if (!p.getUuid().equals(this.uuid) && !p.getUsername().equals(this.userName) || !p.getPiece().equals(this.piece)) {
      return false;
    }

    return true;
  }
}
