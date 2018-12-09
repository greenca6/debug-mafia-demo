package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Player {
  private String username;
  private Piece piece;
  private Set<Card> cards = new HashSet<>();
  private UUID uuid = UUID.randomUUID();

  // Constructors used only for Jackson serialization
  public Player() { }
  public Player(String username, Piece piece, Set<Card> cards, UUID uuid) {
    this.username = username;
    this.piece = piece;
    this.cards = cards;
    this.uuid = uuid;
  }

  public Player(String username, Piece p) {
    this.username = username;
    this.piece = p;
  }

  public Set<Card> getCards() {
    return this.cards;
  }

  public void dealCards(Set<Card> cards) {
    this.cards = cards;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public String getUsername() {
    return this.username;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public boolean hasCard(Card card) {
    System.out.println(this.username + " card check for card: " + card.getName() + ", " + card.getUuid());
    return cards.contains(card);
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public void setCardsInHand(Set<Card> cardsInHand) {
    this.cards = cardsInHand;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    final Player p = (Player) obj;

    if (!p.getUuid().equals(this.uuid)) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    String s = "Player Instance:\n";

    s += "  username: " + this.username + "\n";
    s += "  piece: " + this.piece.toString() + "\n";
    s += "  cardsInHand:\n";

    for (Card c: this.cards) {
      s += "    card: " + c.toString() + "\n";
    }

    return s;
  }
}
