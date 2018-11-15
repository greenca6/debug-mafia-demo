package com.debugmafia.clueless.model;

import java.util.UUID;

public class Card {
  private CardType type;
  private String name;
  private UUID uuid;

  public Card(CardType type, String name) {
    this.uuid = UUID.randomUUID();
    this.type = type;
    this.name = name;
  }

  public CardType getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }

}
