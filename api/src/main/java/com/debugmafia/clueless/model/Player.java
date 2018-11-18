package com.debugmafia.clueless.model;

import java.util.Set;

public class Player {

  private String userName;
  private int playerNum;
  private Piece character;
  private Set<Card> cardsInHand;

  public Player() {
  }

  public Player(String userName, int playerNum, Piece p) {
    this.userName = userName;
    this.playerNum = playerNum;
    this.character = p;
  }

  public String getUsername() {
    return this.userName;
  }

  public int getPlayernum() {
    return this.playerNum;
  }

  public Piece getPiece() {
    return this.character;
  }

  public boolean hasCard(Card c)
  {
    return cardsInHand.contains(c);
  }

  public void dealCards(Set<Card> cards)
  {
    this.cardsInHand = cards;
  }

}
