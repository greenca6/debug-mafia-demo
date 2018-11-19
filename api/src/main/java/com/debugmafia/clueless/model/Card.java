package com.debugmafia.clueless.model;

import java.util.UUID;

public class Card {
  private CardType type;
  private String name;
  private UUID uuid = UUID.randomUUID();

  public Card(CardType type, String name) {
    this.type = type;
    this.name = name;
  }

  public CardType getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    final Card c = (Card) obj;

    if (!c.getType().equals(this.type) || !c.getName().equals(this.name) || !c.getUuid().equals(this.uuid)) {
      return false;
    }

    return true;
  }
}
