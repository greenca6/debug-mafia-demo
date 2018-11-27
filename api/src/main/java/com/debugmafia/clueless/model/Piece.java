package com.debugmafia.clueless.model;

import java.util.UUID;

public class Piece {
  private String name;
  private UUID uuid = UUID.randomUUID();

  public Piece(String name) {
    this.name = name;
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

    final Piece p = (Piece) obj;

    if (!p.getName().equals(this.name) || !p.getUuid().equals(this.uuid)) {
      return false;
    }

    return true;
  }
}
